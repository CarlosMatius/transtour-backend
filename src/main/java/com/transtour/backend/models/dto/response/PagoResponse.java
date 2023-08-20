package com.transtour.backend.models.dto.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PagoResponse implements Serializable{

	private Long id;
	private String nombreComercio;
	private String descripcion;
	private String numeroRecibo;
	private String estado;
	private LocalDateTime createdAt;
	
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private ReservaResponse reserva;
	
	private static final long serialVersionUID = 1L;
}