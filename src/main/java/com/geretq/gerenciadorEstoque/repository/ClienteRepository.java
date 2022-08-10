package com.geretq.gerenciadorEstoque.repository;

import com.geretq.gerenciadorEstoque.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}