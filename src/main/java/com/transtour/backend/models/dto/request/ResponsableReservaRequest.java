package com.transtour.backend.models.dto.request;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponsableReservaRequest implements Serializable{

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
	
	@NotNull(message = "no puede ser vacio")
	private Long idReserva;
	
	private static final long serialVersionUID = 1L;
}