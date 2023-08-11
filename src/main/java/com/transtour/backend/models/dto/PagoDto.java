package com.transtour.backend.models.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PagoDTO implements Serializable{

	private Long id;
	
	@NotEmpty(message = "no puede ser vacio")
	private String nombreComercio;
	
	@NotEmpty(message = "no puede ser vacio")
	@Size(min = 5, message = "la descripcion debe ser mayor a 5 caracteres")
	private String descripcion;
	
	@NotEmpty(message = "no puede ser vacio")
	private String numeroRecibo;
	
	@NotEmpty(message = "no puede ser vacio")
	private String estado;
	
	@NotNull(message = "no puede ser vacio")
	private LocalDateTime createdAt;
	
	@NotNull(message = "no puede ser vacio")
	private ReservaDTO reserva;
	
	private static final long serialVersionUID = 1L;
}
