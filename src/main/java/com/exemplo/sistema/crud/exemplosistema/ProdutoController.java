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

/**
 * Controller responsável por gerenciar as requisições web para o CRUD de produtos.
 */
@Controller
public class ProdutoController {

    // Injeção de dependência do repositório de produtos
    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Mapeia a requisição GET para /cadastro.
     * Exibe o formulário de cadastro de um novo produto.
     * @param model O modelo para adicionar atributos que serão usados na view.
     * @return O nome da view (template) a ser renderizada ("index").
     */
    @GetMapping("/cadastro")
    public String formulario(Model model) {
        // Garante que o DTO e as categorias estejam no modelo para o formulário
        if (!model.containsAttribute("produtoDTO")) {
            model.addAttribute("produtoDTO", new ProdutoDTO());
        }
        model.addAttribute("categorias", Categoria.values());
        return "index";
    }

    /**
     * Mapeia a requisição POST para /cadastro.
     * Processa o envio do formulário, valida os dados e salva o novo produto.
     * @param produtoDTO O DTO com os dados do produto, validado com @Valid.
     * @param result O resultado da validação.
     * @param redirectAttributes Usado para passar atributos (como mensagens de sucesso/erro) após um redirecionamento.
     * @return Redireciona para /cadastro em caso de sucesso ou erro.
     */
    @PostMapping("/cadastro")
    public String salvarProduto(@ModelAttribute("produtoDTO") @Valid ProdutoDTO produtoDTO,
                                BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // Se houver erros, redireciona de volta ao formulário, passando os erros e os dados preenchidos.
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.produtoDTO", result);
            redirectAttributes.addFlashAttribute("produtoDTO", produtoDTO);
            return "redirect:/cadastro";
        }

        produtoRepository.save(ProdutoMapper.toEntity(produtoDTO));
        // Adiciona uma mensagem de sucesso para ser exibida na página de cadastro.
        redirectAttributes.addFlashAttribute("successMessage", "Produto cadastrado com sucesso!");
        return "redirect:/cadastro";
    }
    
    /**
     * Mapeia a requisição GET para /listar.
     * Exibe a lista de produtos, permitindo a filtragem opcional por categoria.
     * @param categoria A categoria para filtrar a lista (parâmetro opcional).
     * @param model O modelo para adicionar a lista de produtos e outras informações para a view.
     * @return O nome da view de listagem ("listar").
     */
    @GetMapping("/listar")
    public String listarProdutos(@RequestParam(name = "categoria", required = false) Categoria categoria, Model model) {
        List<Produto> produtos;

        if (categoria != null) {
            produtos = produtoRepository.findByCategoria(categoria);
        } else {
            produtos = produtoRepository.findAll();
        }

        List<ProdutoDTO> produtosDTO = produtos.stream()
            .map(ProdutoMapper::toDTO)
            .collect(Collectors.toList());

        model.addAttribute("produtos", produtosDTO);
        model.addAttribute("categorias", Categoria.values()); // Para o dropdown do filtro
        model.addAttribute("categoriaSelecionada", categoria); // Para manter o valor do filtro selecionado
        return "listar";
    }

    /**
     * Mapeia a requisição GET para /excluir/{id}.
     * Exclui um produto com base no seu ID.
     * @param id O ID do produto a ser excluído.
     * @return Redireciona para a página de listagem.
     */
    @GetMapping("/excluir/{id}")
    public String excluirProduto(@PathVariable("id") Long id) {
        produtoRepository.deleteById(id);
        return "redirect:/listar";
    }

    /**
     * Mapeia a requisição GET para /editar/{id}.
     * Exibe o formulário de edição preenchido com os dados do produto selecionado.
     * @param id O ID do produto a ser editado.
     * @param model O modelo para enviar o DTO do produto e as categorias para a view.
     * @return O nome da view de edição ("editar").
     */
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable("id") Long id, Model model) {
        Produto produto = produtoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID do produto inválido:" + id));
        
        model.addAttribute("produtoDTO", ProdutoMapper.toDTO(produto));
        model.addAttribute("categorias", Categoria.values());
        return "editar";
    }

    /**
     * Mapeia a requisição POST para /editar/{id}.
     * Processa a submissão do formulário de edição, valida e salva as alterações.
     * @param id O ID do produto que está sendo editado.
     * @param produtoDTO O DTO com os dados atualizados do produto.
     * @param result O resultado da validação.
     * @param model O modelo para reenviar as categorias em caso de erro.
     * @return Redireciona para a listagem em caso de sucesso, ou retorna para a view de edição em caso de erro.
     */
    @PostMapping("/editar/{id}")
    public String editarProduto(@PathVariable("id") Long id, 
                                @ModelAttribute("produtoDTO") @Valid ProdutoDTO produtoDTO, 
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            produtoDTO.setId(id);
            model.addAttribute("categorias", Categoria.values()); // Garante que as categorias estejam disponíveis no form
            return "editar";
        }

        Produto produto = ProdutoMapper.toEntity(produtoDTO);
        produtoRepository.save(produto);
        
        return "redirect:/listar";
    }
}

