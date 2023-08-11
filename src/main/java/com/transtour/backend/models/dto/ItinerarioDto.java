package com.transtour.backend.models.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItinerarioDTO implements Serializable{

	private Long id;
	
	@NotNull(message = "no puede ser vacio")
	private LocalDate fechaEmbarque;
	
	@NotNull(message = "no puede ser vacio")
	private LocalTime horaSalidad;
	
	@NotNull(message = "no puede ser vacio")
	private LocalTime horaRegreso;
	
	@NotNull(message = "no puede ser vacio")
	private int cupos;
	
	@NotNull(message = "no puede ser vacio")
	private BigDecimal precio;
	
	@NotNull(message = "no puede ser vacio")
	private EmbarcacionDTO embarcacion;
	
	@NotNull(message = "no puede ser vacio")
	private DestinoDTO destino;
	
	@NotNull(message = "no puede ser vacio")
	private MuelleDTO muelle;
	
	private static final long serialVersionUID = 1L;
}
