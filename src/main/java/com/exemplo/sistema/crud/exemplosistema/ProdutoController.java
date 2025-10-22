package com.exemplo.sistema.crud.exemplosistema;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/cadastro")
    public String formulario(Model model) {
        // Garante que o DTO e as categorias estejam no modelo
        if (!model.containsAttribute("produtoDTO")) {
            model.addAttribute("produtoDTO", new ProdutoDTO());
        }
        model.addAttribute("categorias", Categoria.values());
        return "index";
    }

    @PostMapping("/cadastro")
    public String salvarProduto(@ModelAttribute("produtoDTO") @Valid ProdutoDTO produtoDTO,
                                BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // Se houver erros, adiciona os atributos de volta para renderizar o formulário
            // corretamente, incluindo as categorias.
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.produtoDTO", result);
            redirectAttributes.addFlashAttribute("produtoDTO", produtoDTO);
            return "redirect:/cadastro";
        }

        produtoRepository.save(ProdutoMapper.toEntity(produtoDTO));
        // Adiciona uma mensagem de sucesso para o alert dinâmico
        redirectAttributes.addFlashAttribute("successMessage", "Produto cadastrado com sucesso!");
        return "redirect:/cadastro";
    }
    
    @GetMapping("/listar")
    public String listarProdutos(@RequestParam(name = "categoria", required = false) Categoria categoria, Model model) {
        List<Produto> produtos;

        // Se uma categoria foi fornecida no filtro, busca por ela
        if (categoria != null) {
            produtos = produtoRepository.findByCategoria(categoria);
        } else {
            // Caso contrário, busca todos os produtos
            produtos = produtoRepository.findAll();
        }

        List<ProdutoDTO> produtosDTO = produtos.stream()
            .map(ProdutoMapper::toDTO)
            .collect(Collectors.toList());

        model.addAttribute("produtos", produtosDTO);
        model.addAttribute("categorias", Categoria.values()); // Para o dropdown do filtro
        model.addAttribute("categoriaSelecionada", categoria); // Para manter o valor do filtro
        return "listar";
    }

    @GetMapping("/excluir/{id}")
    public String excluirProduto(@PathVariable("id") Long id) {
        produtoRepository.deleteById(id);
        return "redirect:/listar";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable("id") Long id, Model model) {
        Produto produto = produtoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID do produto inválido:" + id));
        
        model.addAttribute("produtoDTO", ProdutoMapper.toDTO(produto));
        model.addAttribute("categorias", Categoria.values()); // Adiciona categorias para o dropdown
        return "editar";
    }

    @PostMapping("/editar/{id}")
    public String editarProduto(@PathVariable("id") Long id, 
                                @ModelAttribute("produtoDTO") @Valid ProdutoDTO produtoDTO, 
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            produtoDTO.setId(id);
            model.addAttribute("categorias", Categoria.values()); // Reenvia categorias em caso de erro
            return "editar";
        }

        Produto produto = ProdutoMapper.toEntity(produtoDTO);
        produtoRepository.save(produto);
        
        return "redirect:/listar";
    }
}

