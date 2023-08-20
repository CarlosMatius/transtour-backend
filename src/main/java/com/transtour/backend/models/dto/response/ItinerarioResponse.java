package com.transtour.backend.models.dto.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItinerarioResponse implements Serializable{

	private Long id;
	private LocalDate fechaEmbarque;
	private LocalTime horaSalida;
	private LocalTime horaRegreso;
	private int cupos;
	private BigDecimal precio;
	
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private EmbarcacionResponse embarcacion;
	
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private DestinoResponse destino;
	
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private MuelleResponse muelle;
	
	private static final long serialVersionUID = 1L;
}