package com.exemplo.sistema.crud.exemplosistema;

/**
 * Classe utilitária para mapear (converter) entre a entidade {@link Produto} e o DTO {@link ProdutoDTO}.
 * Isso ajuda a desacoplar a camada de persistência da camada de apresentação.
 */
public class ProdutoMapper {

    /**
     * Converte um objeto {@link ProdutoDTO} em uma entidade {@link Produto}.
     * @param dto O Data Transfer Object a ser convertido.
     * @return A entidade Produto correspondente.
     */
    public static Produto toEntity(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setId(dto.getId()); // Mapeia o ID para permitir atualizações
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setCategoria(dto.getCategoria());
        return produto;
    }

    /**
     * Converte uma entidade {@link Produto} em um objeto {@link ProdutoDTO}.
     * @param produto A entidade a ser convertida.
     * @return O Data Transfer Object correspondente.
     */
    public static ProdutoDTO toDTO(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setPreco(produto.getPreco());
        dto.setCategoria(produto.getCategoria());
        return dto;
    }
}

