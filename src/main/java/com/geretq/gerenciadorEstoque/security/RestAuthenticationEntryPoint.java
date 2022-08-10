package com.geretq.gerenciadorEstoque.security;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint  {


	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,	AuthenticationException authException) throws IOException, ServletException {
		final Map<String, Object> mapBodyException = new HashMap<>();
		mapBodyException.put("cause", authException.getClass());
		mapBodyException.put("error", "Acesso não autorizado.");
		mapBodyException.put("message_erro", authException.getMessage());
		mapBodyException.put("message","Token inválido! Favor fazer login novamente.");
		mapBodyException.put("path", request.getServletPath());
		mapBodyException.put("timestamp", (new Date()).getTime());

		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

		final ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), mapBodyException);
		
	}


}