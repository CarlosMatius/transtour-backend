package com.transtour.backend.models.dto.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmpresaResponse implements Serializable{

	private Long id;
	private String nombre;
	private String nit;
	private String email;
	private String telefono;
	private String imagen;
	private boolean enabled;
	
	@JsonIgnoreProperties(value = {"empresa", "hibernateLazyInitializer", "handler"}, allowSetters = true)
	private List<UsuarioResponse> usuarios;
	
	@JsonIgnoreProperties(value = {"empresa", "hibernateLazyInitializer", "handler"}, allowSetters = true)
	private List<EmbarcacionResponse> embarcaciones;
	
	private static final long serialVersionUID = 1L;
}