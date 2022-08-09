package com.geretq.gerenciadorEstoque.repository;

import com.geretq.gerenciadorEstoque.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByLogin(String login);

}