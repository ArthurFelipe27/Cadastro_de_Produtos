package com.exemplo.sistema.crud.exemplosistema;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface do repositório Spring Data JPA para a entidade {@link Produto}.
 * Fornece métodos CRUD (Create, Read, Update, Delete) prontos para uso,
 * além de permitir a definição de consultas personalizadas.
 */
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    /**
     * Busca uma lista de produtos que pertencem a uma categoria específica.
     * O Spring Data JPA implementa este método automaticamente com base em sua assinatura.
     * * @param categoria A categoria pela qual os produtos serão filtrados.
     * @return Uma lista de produtos da categoria especificada.
     */
    List<Produto> findByCategoria(Categoria categoria);
}

