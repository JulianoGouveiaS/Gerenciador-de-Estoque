package com.geretq.gerenciadorEstoque.security;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.geretq.gerenciadorEstoque.utils.JwtUtils;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    private AuthenticationConfiguration authenticationConfiguration;
    
    private static final int DAYS_TO_EXPIRE = 2;
    
    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;
    
    @Value("${security.oauth2.client.client-id}")
	private String clientId;	
    
    @Value("${security.oauth2.client.client-secret}")
	private String clientSecret;	

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(clientId)
                .scopes("read","write")
                .authorizedGrantTypes("password","refresh_token")
                .secret(new BCryptPasswordEncoder().encode(clientSecret))
                .accessTokenValiditySeconds(120)
                .autoApprove(true);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(this.authenticationConfiguration.getAuthenticationManager())
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST).tokenStore(tokenStore())
                .accessTokenConverter(accessTokenConverter());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
     	security.accessDeniedHandler(customAccessDeniedHandler);
        security.authenticationEntryPoint(jwtAuthenticationEntryPoint);
        security.allowFormAuthenticationForClients();
        
    }

    @Autowired
    public void setAuthenticationConfiguration(AuthenticationConfiguration authenticationConfiguration) {
        this.authenticationConfiguration = authenticationConfiguration;
    }

    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {

    	 final JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter() {
 			@Override
 		    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication ) {
 				final Map<String, Object> additionalInfo = new HashMap<>();
 		        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
 		        ((DefaultOAuth2AccessToken) accessToken).setExpiration(getExpirationDate());
 		        return super.enhance(accessToken, authentication );
 		    }
 		};
 		accessTokenConverter.setSigningKey(JwtUtils.SECRET_KEY);
 		return accessTokenConverter;

    }

    @Bean
    @Primary
    public DefaultTokenServices tokenService() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }
    
	public static Date getExpirationDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, DAYS_TO_EXPIRE);
		return calendar.getTime();
	}

	
}