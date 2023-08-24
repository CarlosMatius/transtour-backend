package com.transtour.backend.models.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter@ Setter
public class UsuarioResponse implements Serializable{

	private Long id;
	
	private String nombre;
	
	private String apellido;
	
	private TipoIdentificacionDTO tipoIdentificacion;
	
	private String identificacion;
	
	private String username;
	
	private boolean enabled;
	
	@JsonIgnoreProperties(value = {"usuarios", "embarcaciones","hibernateLazyInitializer", "handler"}, allowSetters = true)
	private EmpresaDTO empresa;
	
	private List<RolDTO> roles;
	
	private static final long serialVersionUID = 1L;
}