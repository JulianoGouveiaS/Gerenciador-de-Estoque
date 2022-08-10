package com.geretq.gerenciadorEstoque.components;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.geretq.gerenciadorEstoque.domain.TipoUsuarioEnum;
import com.geretq.gerenciadorEstoque.domain.Usuario;
import com.geretq.gerenciadorEstoque.repository.UsuarioRepository;

@Component
public class InicializacaoBancoDados implements ApplicationListener<ContextRefreshedEvent> {
	
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        List<Usuario> usuarios = usuarioRepository.findAll();
        if (usuarios.isEmpty()) {
        	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        	if (usuarios.stream().noneMatch(usuario -> usuario.getLogin().equals("root"))) {
        		System.out.println("Criando usuário root...");
        		createUser("Administrador", "root", bCryptPasswordEncoder.encode("root"), TipoUsuarioEnum.ADMINISTRADOR);
        	}
        	if (usuarios.stream().noneMatch(usuario -> usuario.getLogin().equals("root"))) {
        		System.out.println("Criando usuário operador...");
        		createUser("Operador Teste", "operador", bCryptPasswordEncoder.encode("operador"), TipoUsuarioEnum.OPERADOR);
        	}
        }
    }

    public void createUser(String nome, String login, String senha, TipoUsuarioEnum tipo) {
    	Usuario usuario = new Usuario(null, nome, login, senha, tipo, true, new Date());
        usuarioRepository.save(usuario);
    }


}
