package com.exemplo.sistema.crud.exemplosistema;

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
        return "redirect:/cadastro";
    }
}