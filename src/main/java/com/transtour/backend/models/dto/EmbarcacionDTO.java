package com.transtour.backend.models.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmbarcacionDTO implements Serializable{

	private Long id;
	
	@NotEmpty(message = "no puede ser vacio")
	@Size(min = 5, message = "el nombre debe ser mayor a 5 caracteres")
	private String nombre;
	
	@NotNull(message = "no puede ser vacio")
	private int capacidad;
	
	private boolean enabled;
	
	@JsonIgnoreProperties(value = {"embarcaciones","usuarios", "hibernateLazyInitializer", "handler"}, allowSetters = true)
	private EmpresaDTO empresa;
	
	private static final long serialVersionUID = 1L;
}