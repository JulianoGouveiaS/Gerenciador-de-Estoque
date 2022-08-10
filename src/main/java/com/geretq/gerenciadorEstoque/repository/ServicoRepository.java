package com.geretq.gerenciadorEstoque.repository;

import com.geretq.gerenciadorEstoque.domain.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

}