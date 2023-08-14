package com.transtour.backend.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
	
	private static final String SUPERADMINISTRADOR = "SUPERADMINISTRADOR";

	@Override
	public void configure(HttpSecurity http) throws Exception {
        http
        	.authorizeRequests(authorizeRequests -> 
        		authorizeRequests
        			.antMatchers(HttpMethod.GET, "/v1/destinos", "/v1/destinos/page/**").permitAll()
        			.antMatchers(HttpMethod.POST, "/v1/destinos").hasRole(SUPERADMINISTRADOR)
        			.antMatchers(HttpMethod.PUT, "/v1/destinos/{id}").hasRole(SUPERADMINISTRADOR)
        			.antMatchers(HttpMethod.DELETE, "/v1/destinos/{id}").hasRole(SUPERADMINISTRADOR)
        			.antMatchers("/v1/destinos/**").hasRole(SUPERADMINISTRADOR)
        			.anyRequest().authenticated()
			);
	}
}
 