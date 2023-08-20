package com.transtour.backend.models.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PasajeroResponse implements Serializable{

	private Long id;
	private String nombre;
	private String apellido;
	
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private TipoIdentificacionResponse tipoIdentificacion;
	
	private String identificacion;
	
	@JsonIgnoreProperties(value = {"pasajeros", "hibernateLazyInitializer", "handler"}, allowSetters = true)
	private ReservaResponse reserva;
	
	private static final long serialVersionUID = 1L;
}