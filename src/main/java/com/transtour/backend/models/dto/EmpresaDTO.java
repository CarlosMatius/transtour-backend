package com.transtour.backend.models.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmpresaDTO implements Serializable{

	private Long id;
	
	@NotEmpty(message = "no puede ser vacio")
	@Size(min = 5, message = "debe ser mayor a 5 caracteres")
	private String nombre;
	
	@NotEmpty(message = "no puede ser vacio")
	@Size(min = 9, message = "debe ser mayor a 5 caracteres")
	private String nit;
	
	@NotEmpty(message = "no puede ser vacio")
	@Email(message = "formato de email no valido")
	private String email;
	
	@NotEmpty(message = "no puede ser vacio")
	@Size(min = 7, message = "debe ser mayor a 7 caracteres")
	private String telefono;
	
	private String imagen;
	
	private boolean enabled;
	
	@JsonIgnoreProperties(value = {"empresa", "hibernateLazyInitializer", "handler"}, allowSetters = true)
	private List<UsuarioDTO> usuarios;
	
	@JsonIgnoreProperties(value = {"empresa", "hibernateLazyInitializer", "handler"}, allowSetters = true)
	private List<EmbarcacionDTO> embarcaciones;
	
	private static final long serialVersionUID = 1L;
}