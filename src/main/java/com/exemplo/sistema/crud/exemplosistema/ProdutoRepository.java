package com.exemplo.sistema.crud.exemplosistema;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Novo método para buscar produtos por categoria
    List<Produto> findByCategoria(Categoria categoria);
}
