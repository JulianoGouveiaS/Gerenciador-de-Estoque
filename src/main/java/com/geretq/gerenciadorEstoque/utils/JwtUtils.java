package com.geretq.gerenciadorEstoque.utils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtils {

	private JwtUtils() {}
	
	public static final String USERNAME_CLAIM = "user_name";
	public static final String SECRET_KEY = "_@HRL&L3tF?Z7ccj4z&L5!nU2B!Rjs3_";
	public static final byte[] BYTES_OF_SECRET_KEY = SECRET_KEY.getBytes();
	
	public static String criarToken(String identificador, Date dataExpiracao) {
		return Jwts.builder()
			.claim(USERNAME_CLAIM, identificador)
			.claim("client_id", "acme")
			.claim("scope", new String[] { "read", "write" })
			.setId(UUID.randomUUID().toString())
			.setExpiration(dataExpiracao)
			.signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes())
			.compact();
	}

	//get permissoes [adm ou operador]
	public static Claims getClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJws(token).getBody();
		} catch (ExpiredJwtException e) {
			return e.getClaims();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Date getDataExpiracao(String token) {
		Date dataExpiracao = null;
		try {
			Claims claims = getClaims(token);
			if (claims != null) {
				dataExpiracao = claims.getExpiration();
			}
			return dataExpiracao;
		} catch (Exception e) {
			return dataExpiracao;
		}
	}

	public static boolean isExpirado(String token) {
		try {
			Date dataExpiracao = getDataExpiracao(token);
			return dataExpiracao == null || LocalDateTime.now().isAfter(DateTimeUtils.toLocalDateTime(dataExpiracao));
		} catch (Exception e) {
			return true;
		}
	}
	
}
