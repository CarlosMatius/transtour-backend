package com.transtour.backend.models.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReservaDto {

	private Long id;
	private String codigoReserva;
	private LocalDateTime createdAt;
	private BigDecimal total;
	private ItinerarioDto itinerario;
	private ResponsableReservaDto responsableReserva;
	private PagoDto pago;
	private List<PasajeroDto> pasajeros;
}
