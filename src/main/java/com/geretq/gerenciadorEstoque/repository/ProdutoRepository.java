package com.geretq.gerenciadorEstoque.repository;

import com.geretq.gerenciadorEstoque.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}