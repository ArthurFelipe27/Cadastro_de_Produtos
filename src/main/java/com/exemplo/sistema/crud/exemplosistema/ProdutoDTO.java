package com.exemplo.sistema.crud.exemplosistema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * DTO (Data Transfer Object) para a entidade Produto.
 * Utilizado para transferir dados entre o controller e a view,
 * e para aplicar validações nos dados de entrada do formulário.
 */
public class ProdutoDTO {

    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @Positive(message = "Preço deve ser maior que zero")
    private double preco;

    @NotNull(message = "Categoria é obrigatória")
    private Categoria categoria;

    // --- Getters e Setters ---
    // Métodos de acesso para os atributos do DTO.

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}

