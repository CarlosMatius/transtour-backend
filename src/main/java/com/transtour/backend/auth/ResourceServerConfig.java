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
	private static final String ADMINISTRADOR = "ADMINISTRADOR";
	private static final String ASESOR = "ASESOR";

	@Override
	public void configure(HttpSecurity http) throws Exception {
        http
        	.authorizeRequests(authorizeRequests -> 
        		authorizeRequests
        			.antMatchers(HttpMethod.GET, 
        					"/v1/destinos", 
        					"/v1/itinerarios/{fechaEmbarque}/{nombreDestino}").permitAll()
        			.antMatchers(HttpMethod.GET, "/v1/destinos/page/**").hasRole(SUPERADMINISTRADOR)
        			.antMatchers(HttpMethod.POST, "/v1/destinos").hasRole(SUPERADMINISTRADOR)
        			.antMatchers(HttpMethod.PUT, "/v1/destinos/{id}").hasRole(SUPERADMINISTRADOR)
        			.antMatchers(HttpMethod.DELETE, "/v1/destinos/{id}").hasRole(SUPERADMINISTRADOR)
        			.antMatchers(HttpMethod.GET, "/v1/embarcaciones").hasAnyRole(SUPERADMINISTRADOR, ADMINISTRADOR)
        			.antMatchers(HttpMethod.GET, "/v1/embarcaciones/page/**").hasAnyRole(SUPERADMINISTRADOR, ADMINISTRADOR)
        			.antMatchers(HttpMethod.POST, "/v1/embarcaciones").hasAnyRole(SUPERADMINISTRADOR, ADMINISTRADOR)
        			.antMatchers(HttpMethod.PUT, "/v1/embarcaciones/{id}").hasAnyRole(SUPERADMINISTRADOR, ADMINISTRADOR)
        			.antMatchers(HttpMethod.DELETE, "/v1/embarcaciones/{id}").hasAnyRole(SUPERADMINISTRADOR, ADMINISTRADOR)
        			.antMatchers(HttpMethod.GET, "/v1/empresas/{nit}").hasRole(SUPERADMINISTRADOR)
        			.antMatchers(HttpMethod.GET, "/v1/empresas/page/**").hasRole(SUPERADMINISTRADOR)
        			.antMatchers(HttpMethod.POST, "/v1/empresas").hasRole(SUPERADMINISTRADOR)
        			.antMatchers(HttpMethod.PUT, "/v1/empresas/{id}").hasAnyRole(SUPERADMINISTRADOR, ADMINISTRADOR)
        			.antMatchers(HttpMethod.DELETE, "/v1/empresas/{id}").hasRole(SUPERADMINISTRADOR)
        			.antMatchers(HttpMethod.POST, "/v1/empresas/upload").hasAnyRole(SUPERADMINISTRADOR, ADMINISTRADOR)
        			.antMatchers(HttpMethod.GET, "/v1//empresas/uploads/img/{nombreFoto:.+}").hasAnyRole(SUPERADMINISTRADOR, ADMINISTRADOR)
        			.antMatchers(HttpMethod.GET, "/v1/itinerarios").hasAnyRole(SUPERADMINISTRADOR, ADMINISTRADOR)
        			.antMatchers(HttpMethod.GET, "/v1/itinerarios/page/**").hasAnyRole(SUPERADMINISTRADOR, ADMINISTRADOR)
        			.antMatchers(HttpMethod.POST, "/v1/itinerarios").hasAnyRole(SUPERADMINISTRADOR, ADMINISTRADOR)
        			.antMatchers(HttpMethod.PUT, "/v1/itinerarios/{id}").hasAnyRole(SUPERADMINISTRADOR, ADMINISTRADOR)
        			.antMatchers(HttpMethod.DELETE, "/v1/itinerarios/{id}").hasAnyRole(SUPERADMINISTRADOR, ADMINISTRADOR)
        			.anyRequest().authenticated()
			);
	}
}