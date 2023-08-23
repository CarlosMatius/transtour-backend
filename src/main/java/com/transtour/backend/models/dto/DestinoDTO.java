package com.transtour.backend.models.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DestinoDTO implements Serializable{

	private Long id;
	
	@NotEmpty(message = "no puede ser vacio")
	@Size(min = 5, message = "el nombre debe ser mayor a 5 caracteres")
	private String nombre;
	
	private boolean enabled;
	
	private static final long serialVersionUID = 1L;
}