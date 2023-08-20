package com.transtour.backend.models.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.transtour.backend.models.dto.request.ReservaRequest;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponsableReservaResponse implements Serializable{

	private Long id;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private ReservaRequest reserva;
	
	private static final long serialVersionUID = 1L;
}