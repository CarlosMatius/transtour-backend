package com.transtour.backend.models.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReservaDTO implements Serializable{

	private Long id;
	
	@NotEmpty(message = "no puede ser vacio")
	@Size(min = 5, message = "el codigo debe ser mayor a 5 caracteres")
	private String codigoReserva;
	
	private LocalDateTime createdAt;
	
	@NotNull(message = "no puede ser vacio")
	private BigDecimal total;
	
	@NotNull(message = "no puede ser vacio")
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private ItinerarioDTO itinerario;
	
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private ResponsableReservaDTO responsableReserva;
	
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private PagoDTO pago;
	
	@JsonIgnoreProperties(value = {"reserva", "hibernateLazyInitializer", "handler"}, allowSetters = true)
	private List<PasajeroDTO> pasajeros;
	
	private static final long serialVersionUID = 1L;
}