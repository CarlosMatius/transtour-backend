package com.transtour.backend.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
        			.antMatchers(HttpMethod.GET, "/v1/empresas", "/v1/empresas/{id}", "/v1/usuarios", "/v1/usuarios/{id}").permitAll()
        			
        			.antMatchers(HttpMethod.GET,
        					"/v1/usuarios",
        					"/v1/usuarios/{id}",
        					"/v1/usuarios/by/{identificacion}",
        					"/v1/usuarios/page/**"
        					).hasAnyRole(SUPERADMINISTRADOR, ADMINISTRADOR)
        			
        			.antMatchers(HttpMethod.POST, 
        					"/v1/usuarios"
        					).hasAnyRole(SUPERADMINISTRADOR, ADMINISTRADOR)
        			
        			.antMatchers(HttpMethod.PUT, 
        					"/v1/usuarios/{id}"
        					).hasAnyRole(SUPERADMINISTRADOR, ADMINISTRADOR)
        			
        			.antMatchers(HttpMethod.DELETE, 
        					"/v1/usuarios/{id}"
        					).hasAnyRole(SUPERADMINISTRADOR, ADMINISTRADOR)
        			
        			
        			
        			
        			.antMatchers(HttpMethod.POST, "/v1/destinos").permitAll()
        			.antMatchers(HttpMethod.PUT, "/v1/empresas/{id}").permitAll()
        			.antMatchers(HttpMethod.DELETE, "/v1/empresas/{id}").permitAll()
        			.anyRequest().authenticated()
			);
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowCredentials(true);
		config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}

	@Bean
	FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(
				new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
}