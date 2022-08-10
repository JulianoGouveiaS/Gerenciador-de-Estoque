package com.geretq.gerenciadorEstoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geretq.gerenciadorEstoque.domain.Movimentacao;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

}
