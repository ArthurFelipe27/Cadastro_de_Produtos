package com.exemplo.sistema.crud.exemplosistema;

public enum Categoria {
    TECNOLOGIA("Tecnologia"),
    COSMETICOS("Cosméticos"),
    RELIGIOSOS("Religiosos"),
    DIGITAL("Digital"),
    ALIMENTICIO("Alimentício"),
    ROUPA("Roupa");

    private final String descricao;

    Categoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
