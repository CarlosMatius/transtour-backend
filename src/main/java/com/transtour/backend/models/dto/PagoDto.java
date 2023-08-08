package com.transtour.backend.models.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PagoDto {

	private Long id;
	private String nombreComercio;
	private String descripcion;
	private String numeroRecibo;
	private String estado;
	private LocalDateTime createdAt;
	private ReservaDto reserva;
}
