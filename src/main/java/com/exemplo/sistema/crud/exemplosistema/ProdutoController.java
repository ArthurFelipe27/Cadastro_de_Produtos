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

import jakarta.validation.Valid;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/cadastro")
    public String formulario(Model model) {
        model.addAttribute("produtoDTO", new ProdutoDTO());
        return "index";
    }

    @PostMapping("/cadastro")
    public String salvarProduto(@ModelAttribute("produtoDTO") @Valid ProdutoDTO produtoDTO,
                                BindingResult result) {
        if (result.hasErrors()) {
            return "index";
        }

        produtoRepository.save(ProdutoMapper.toEntity(produtoDTO));
        return "redirect:/listar"; // Redireciona para a lista após o cadastro
    }

    /** ENDPOINT ATUALIZADO: Exibe a lista de produtos cadastrados **/
    @GetMapping("/listar")
    public String listarProdutos(Model model) {
        // Busca todas as entidades Produto
        List<Produto> produtos = produtoRepository.findAll();

        // Converte a lista de entidades (Produto) para DTOs (ProdutoDTO)
        List<ProdutoDTO> produtosDTO = produtos.stream()
            .map(ProdutoMapper::toDTO)
            .collect(Collectors.toList());

        // Adiciona a lista de DTOs ao modelo (atributo "produtos")
        model.addAttribute("produtos", produtosDTO);
        
        // Retorna o nome do template Thymeleaf
        return "listar";
    }

    /** NOVO ENDPOINT: Exclui um produto pelo ID **/
    @GetMapping("/excluir/{id}")
    public String excluirProduto(@PathVariable("id") Long id) {
        produtoRepository.deleteById(id);
        return "redirect:/listar";
    }

    /** NOVO ENDPOINT: Mostra o formulário de edição com os dados do produto **/
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable("id") Long id, Model model) {
        // Procura o produto pelo ID ou lança uma exceção se não encontrar
        Produto produto = produtoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID do produto inválido:" + id));
        
        // Converte a entidade para DTO e a envia para o modelo
        model.addAttribute("produtoDTO", ProdutoMapper.toDTO(produto));
        return "editar"; // Nome do novo template de edição
    }

    /** NOVO ENDPOINT: Processa a atualização do produto **/
    @PostMapping("/editar/{id}")
    public String editarProduto(@PathVariable("id") Long id, 
                                @ModelAttribute("produtoDTO") @Valid ProdutoDTO produtoDTO, 
                                BindingResult result) {
        if (result.hasErrors()) {
            // Mantém o ID no DTO para que o formulário possa ser reenviado corretamente
            produtoDTO.setId(id);
            return "editar";
        }

        // Converte o DTO para entidade, define o ID e salva as alterações
        Produto produto = ProdutoMapper.toEntity(produtoDTO);
        produto.setId(id); 
        produtoRepository.save(produto);
        
        return "redirect:/listar";
    }
}
