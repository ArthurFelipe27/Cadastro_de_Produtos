package com.exemplo.sistema.crud.exemplosistema;

/**
 * Enumeração que representa as categorias de produtos disponíveis no sistema.
 * Cada categoria possui uma descrição amigável para exibição na interface do usuário.
 */
public enum Categoria {
    // Definição das constantes do enum com suas respectivas descrições
    TECNOLOGIA("Tecnologia"),
    COSMETICOS("Cosméticos"),
    RELIGIOSOS("Religiosos"),
    DIGITAL("Digital"),
    ALIMENTICIO("Alimentício"),
    ROUPA("Roupa");

    // Atributo para armazenar a descrição da categoria
    private final String descricao;

    /**
     * Construtor do enum, que associa uma descrição a cada constante.
     * @param descricao A descrição textual da categoria.
     */
    Categoria(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Retorna a descrição amigável da categoria.
     * @return A string contendo a descrição da categoria.
     */
    public String getDescricao() {
        return descricao;
    }
}

