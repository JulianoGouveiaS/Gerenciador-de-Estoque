package com.geretq.gerenciadorEstoque.repository;

import com.geretq.gerenciadorEstoque.domain.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {

}
