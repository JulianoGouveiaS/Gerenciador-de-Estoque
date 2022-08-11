package com.geretq.gerenciadorEstoque.service;

import java.security.NoSuchAlgorithmException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.geretq.gerenciadorEstoque.domain.Usuario;
import com.geretq.gerenciadorEstoque.repository.UsuarioRepository;
import com.geretq.gerenciadorEstoque.utils.Constants;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

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
    
    @Transactional
	public UsernamePasswordAuthenticationToken autenticar(String username, String password) throws UsernameNotFoundException, BadCredentialsException {
		if (!StringUtils.hasText(username)) {
			throw new UsernameNotFoundException(Constants.USUARIO_NAO_ENCONTRADO);
		}
		if (!StringUtils.hasText(password)) {
			throw new BadCredentialsException(Constants.USUARIO_SENHA_INVALIDOS);
		}
		Usuario usuario = usuarioRepository.findByLogin(username);
		if (usuario == null) {
			throw new UsernameNotFoundException(Constants.USUARIO_NAO_ENCONTRADO);
		}
		Boolean autenticado = Boolean.FALSE;
		autenticado = new BCryptPasswordEncoder().matches(password, usuario.getSenha());
		if (Boolean.TRUE.equals(autenticado)) {
			return new UsernamePasswordAuthenticationToken(username, password, Collections.singleton(new SimpleGrantedAuthority(usuario.getTipo().name())));
		} else {
			throw new BadCredentialsException(Constants.USUARIO_SENHA_INVALIDOS);
		}
	}

	public Usuario buscarPorId(Long id) {
		return usuarioRepository.findById(id).orElse(null);
	}
}