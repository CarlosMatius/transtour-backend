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
        			.antMatchers(HttpMethod.GET, 
        					"/v1/roles",
        					"/v1/tipos-identificaciones",
        					"/v1/itinerarios/{fechaEmbarque}/{nombreDestino}",
        					"/v1/reservas/{codigoReserva}"
        					).permitAll()
        			
        			.antMatchers(HttpMethod.POST,
        					"/v1/reservas",
        					"/v1/responsables",
        					"/v1/pasajeros",
        					"/v1/pagos"
        					).permitAll()
        			
        			.antMatchers(HttpMethod.GET, 
        					"/v1/empresas",
        					"/v1/empresas/page/**",
        					"/v1/empresas/by/{nit}",
        					"/v1/empresas/{id}",
        					"/v1/empresas/uploads/img/{nombreFoto:.+}"
        					).hasRole(SUPERADMINISTRADOR)
        			
        			.antMatchers(HttpMethod.POST, 
        					"/tipos-identificaciones",
        					"/v1/empresas",
        					"/v1/empresas/upload"
        					).hasRole(SUPERADMINISTRADOR)
        			
        			.antMatchers(HttpMethod.PUT, 
        					"/v1/tipos-identificaciones/{id}",
        					"/v1/empresas/{id}"
        					).hasRole(SUPERADMINISTRADOR)
        			
        			.antMatchers(HttpMethod.DELETE, 
        					"/v1/tipos-identificaciones/{id}",
        					"/v1/empresas/{id}"
        					).hasRole(SUPERADMINISTRADOR)
        			
        			.antMatchers(HttpMethod.GET,
        					"/v1/usuarios",
        					"/v1/usuarios/{id}",
        					"/v1/usuarios/by/{identificacion}",
        					"/v1/usuarios/page/**",
        					"/v1/embarcaciones",
        					"/v1/embarcaciones/page/**",
        					"/v1/embarcaciones/{id}",
        					"/v1/itinerarios",
        					"/v1/itinerarios/page/**",
        					"/v1/itinerarios/{id}",
        					"/v1/reservas/{id}",
        					"/v1/pagos",
        					"/v1/pagos/page/**",
        					"/v1/pagos/{numeroRecibo}"
        					).hasAnyRole(SUPERADMINISTRADOR, ADMINISTRADOR)
        			
        			.antMatchers(HttpMethod.POST, 
        					"/v1/usuarios",
        					"/v1/embarcaciones",
        					"/v1/itinerarios",
        					"/v1/pagos"
        					).hasAnyRole(SUPERADMINISTRADOR, ADMINISTRADOR)
        			
        			.antMatchers(HttpMethod.PUT, 
        					"/v1/usuarios/{id}",
        					"/v1/embarcaciones/{id}",
        					"/v1/itinerarios/{id}",
        					"/v1/pasajeros/{id}"
        					).hasAnyRole(SUPERADMINISTRADOR, ADMINISTRADOR)
        			
        			.antMatchers(HttpMethod.DELETE, 
        					"/v1/usuarios/{id}",
        					"/v1/embarcaciones/{id}",
        					"/v1/itinerarios/{id}",
        					"/v1/reservas/{id}"
        					).hasAnyRole(SUPERADMINISTRADOR, ADMINISTRADOR)
        			
        			.antMatchers(HttpMethod.GET,
        					"/v1/reservas",
        					"/v1/reservas/page/**"
        					).hasAnyRole(SUPERADMINISTRADOR, ADMINISTRADOR, ASESOR)
        			
        			.antMatchers(HttpMethod.GET, "/v1/empresas", "/v1/empresas/{id}", "/v1/usuarios", "/v1/usuarios/{id}").permitAll()
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