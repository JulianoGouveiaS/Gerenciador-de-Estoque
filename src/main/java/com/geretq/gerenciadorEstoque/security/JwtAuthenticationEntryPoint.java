package com.geretq.gerenciadorEstoque.security;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = 5805525777201092871L;
   
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		String message = "Unauthorized";
		
		if (authException.getCause() instanceof InvalidTokenException)
			message = "Token inválido";
		else if (authException instanceof BadCredentialsException)
			message = "Usuário e/ou senha inválido";
		else if (authException instanceof CredentialsExpiredException)
		    message = "Usuário não autorizado para autenticação";
		else if (authException instanceof LockedException)
		    message = "Usuário está bloqueado";
		else if (authException instanceof DisabledException)
		    message = "Usuário está desabilitado";
		else if (authException instanceof InsufficientAuthenticationException)
			message = "Você não está autenticado";

        response.sendError( HttpServletResponse.SC_UNAUTHORIZED, message );
	}
}