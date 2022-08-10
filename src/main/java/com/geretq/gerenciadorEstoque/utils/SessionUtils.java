package com.geretq.gerenciadorEstoque.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.JwtException;

@Component
public class SessionUtils {

	private SessionUtils() {}

	@Autowired
	HttpServletRequest httpServletRequest;
	
	/**
	 * Recupera da sessão atual o token que veio no header Authorization
	 * @param withBearer
	 * @return token
	 */
	public String getToken(boolean withBearer) {
		try {
			HttpSession session = httpServletRequest.getSession();
			String token = (String) session.getAttribute("token");
			if (!withBearer) {
				token = token.replace("Bearer ", "");
			}
			return token;
		} catch (Exception e) {
			return null;
		}
	}
	
	public String getUsernameFromToken() {
		String mensagemErro = "Não foi possível localizar o identificador do usuário logado no token";
		try {
			String username = (String) JwtUtils.getClaims(getToken(false)).get(JwtUtils.USERNAME_CLAIM);
			if (!StringUtils.hasText(username)) {
				throw new JwtException(mensagemErro);
			}
			return username;
		} catch (Exception e) {
			throw new JwtException(mensagemErro);
		}
	}
}
