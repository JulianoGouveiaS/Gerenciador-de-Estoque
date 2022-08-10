package com.geretq.gerenciadorEstoque.service;

import com.geretq.gerenciadorEstoque.domain.Usuario;
import com.geretq.gerenciadorEstoque.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final Random random = SecureRandom.getInstanceStrong();

    public UsuarioService() throws NoSuchAlgorithmException {
        super();
    }

    public Usuario criarUsuario(Usuario usuario) throws Exception {

        verificaSeOLoginEstaPresente(usuario.getLogin());

        if(!StringUtils.hasText(usuario.getSenha())){
            throw new Exception("O campo senha é obrigatório");
        }

        usuario.setSenha(usuario.getSenha());
        return usuarioRepository.save(usuario);
    }

    private void verificaSeOLoginEstaPresente(String login) throws Exception {
        if(!StringUtils.hasText(login)){
            throw new Exception("O login é obrigatório");
        }
    }
}