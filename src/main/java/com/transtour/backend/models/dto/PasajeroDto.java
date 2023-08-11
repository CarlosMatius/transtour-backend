package com.transtour.backend.models.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PasajeroDTO implements Serializable{

	private Long id;
	
	@NotEmpty(message = "no puede ser vacio")
	@Size(min = 3, message = "el nombre debe ser mayor a 3 caracteres")
	private String nombre;
	
	@NotEmpty(message = "no puede ser vacio")
	@Size(min = 3, message = "el nombre debe ser mayor a 3 caracteres")
	private String apellido;
	
	@NotNull(message = "no puede ser vacio")
	private TipoIdentificacionDTO tipoIdentificacion;
	
	@NotEmpty(message = "no puede ser vacio")
	@Size(min = 5, message = "el numero de documento debe ser mayor a 5 caracteres")
	private String identificacion;
	
	@NotNull(message = "no puede ser vacio")
	private ReservaDTO reserva;
	
	private static final long serialVersionUID = 1L;
}
