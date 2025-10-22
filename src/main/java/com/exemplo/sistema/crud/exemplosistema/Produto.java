package com.exemplo.sistema.crud.exemplosistema;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidade JPA que representa um Produto no banco de dados.
 * Esta classe é mapeada para a tabela "tb_produto".
 */
@Entity
@Table(name="tb_produto")
public class Produto {
    
    /**
     * Chave primária da tabela, com geração automática de valor.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome do produto.
     */
    private String nome;

    /**
     * Descrição detalhada do produto.
     */
    private String descricao;

    /**
     * Preço do produto.
     */
    private double preco;

    /**
     * Categoria do produto, mapeada como uma String no banco de dados.
     */
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    // --- Getters e Setters ---
    // Métodos de acesso para os atributos da entidade.

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

