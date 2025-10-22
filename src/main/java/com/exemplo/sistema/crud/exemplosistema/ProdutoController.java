package com.exemplo.sistema.crud.exemplosistema;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        return "redirect:/listagem"; // Redireciona para a lista após o cadastro
    }

    /** NOVO ENDPOINT: Exibe a lista de produtos cadastrados **/
    @GetMapping("/listagem")
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
        return "listagem";
    }
}
