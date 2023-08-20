package com.transtour.backend.models.dto.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReservaResponse implements Serializable{

	private Long id;
	private String codigoReserva;
	private LocalDateTime createdAt;
	private BigDecimal total;
	
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private ItinerarioResponse itinerario;
	
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private ResponsableReservaResponse responsableReserva;
	
	@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"}, allowSetters = true)
	private PagoResponse pago;
	
	@JsonIgnoreProperties(value = {"reserva", "hibernateLazyInitializer", "handler"}, allowSetters = true)
	private List<PasajeroResponse> pasajeros;
	
	private static final long serialVersionUID = 1L;
}