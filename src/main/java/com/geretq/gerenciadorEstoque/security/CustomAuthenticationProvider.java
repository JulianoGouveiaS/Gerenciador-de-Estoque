package com.geretq.gerenciadorEstoque.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.geretq.gerenciadorEstoque.service.UsuarioService;
import com.geretq.gerenciadorEstoque.utils.Constants;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider  {

	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public Authentication authenticate(Authentication authentication) {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		if (!StringUtils.hasText(username)) {
			throw new UsernameNotFoundException(Constants.USUARIO_NAO_ENCONTRADO);
		}
		if (!StringUtils.hasText(password)) {
			throw new BadCredentialsException(Constants.USUARIO_SENHA_INVALIDOS);
		}
		
		return usuarioService.autenticar(username, password);
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return aClass.equals(UsernamePasswordAuthenticationToken.class);
	}
}