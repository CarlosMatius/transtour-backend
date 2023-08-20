package com.transtour.backend.models.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItinerarioRequest implements Serializable{

	@NotNull(message = "no puede ser vacio")
	private LocalDate fechaEmbarque;
	
	@NotNull(message = "no puede ser vacio")
	private LocalTime horaSalida;
	
	@NotNull(message = "no puede ser vacio")
	private LocalTime horaRegreso;
	
	@NotNull(message = "no puede ser vacio")
	private int cupos;
	
	@NotNull(message = "no puede ser vacio")
	private BigDecimal precio;
	
	@NotNull(message = "no puede ser vacio")
	private Long idEmbarcacion;
	
	@NotNull(message = "no puede ser vacio")
	private Long idDestino;
	
	@NotNull(message = "no puede ser vacio")
	private Long idMuelle;
	
	private static final long serialVersionUID = 1L;
}