package com.transtour.backend.models.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItinerarioDto {

	private Long id;
	private LocalDate fechaEmbarque;
	private LocalTime horaSalidad;
	private LocalTime horaRegreso;
	private int cupos;
	private BigDecimal precio;
	private EmbarcacionDto embarcacion;
	private DestinoDto destino;
	private MuelleDto muelle;
}
