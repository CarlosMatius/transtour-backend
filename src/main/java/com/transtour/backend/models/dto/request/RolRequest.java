package com.transtour.backend.models.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RolRequest implements Serializable{

	@NotEmpty(message = "no puede ser vacio")
	@Size(min = 5, message = "el rol debe ser mayor a 5 caracteres")
	private String nombre;
	
	private static final long serialVersionUID = 1L;
}