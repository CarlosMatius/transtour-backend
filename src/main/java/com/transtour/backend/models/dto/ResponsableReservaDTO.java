package com.transtour.backend.models.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponsableReservaDTO implements Serializable{

	private Long id;
	
	@NotEmpty(message = "no puede ser vacio")
	@Size(min = 3, message = "el nombre debe ser mayor a 3 caracteres")
	private String nombre;
	
	@NotEmpty(message = "no puede ser vacio")
	@Size(min = 3, message = "el codigo debe ser mayor a 3 caracteres")
	private String apellido;
	
	@NotEmpty(message = "no puede ser vacio")
	@Email(message = "formato de email no valido")
	private String email;
	
	@NotEmpty(message = "no puede ser vacio")
	@Size(min = 7, message = "el codigo debe ser mayor a 7 caracteres")
	private String telefono;
	
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private ReservaDTO reserva;
	
	private static final long serialVersionUID = 1L;
}