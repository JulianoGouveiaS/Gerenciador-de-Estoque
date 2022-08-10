package com.geretq.gerenciadorEstoque.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class CommonSecurityConfig extends ResourceServerConfigurerAdapter {

	@Autowired private CustomAccessDeniedHandler customAccessDeniedHandler;
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
 		resources.accessDeniedHandler(customAccessDeniedHandler);
 		resources.authenticationEntryPoint(jwtAuthenticationEntryPoint);
		super.configure(resources);
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		super.configure(http);
		http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler).authenticationEntryPoint(jwtAuthenticationEntryPoint);
	}
}
