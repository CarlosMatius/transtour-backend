package com.transtour.backend.models.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmbarcacionResponse implements Serializable{

	private Long id;
	private String nombre;
	private int capacidad;
	private boolean enabled;
	
	@JsonIgnoreProperties(value = {"embarcaciones","usuarios", "hibernateLazyInitializer", "handler"}, allowSetters = true)
	private EmpresaResponse empresa;
	
	private static final long serialVersionUID = 1L;
}