package com.transtour.backend.models.builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.transtour.backend.models.entity.Contacto;
import com.transtour.backend.models.entity.Itinerario;
import com.transtour.backend.models.entity.Pago;
import com.transtour.backend.models.entity.Pasajero;
import com.transtour.backend.models.entity.Reserva;

import lombok.Getter;

@Getter
public class ReservaBuilder {

	private Long id;
	private String codigoReserva;
	private LocalDateTime createdAt;
	private BigDecimal total;
	private Itinerario itinerario;
	private Contacto contacto;
	private Pago pago;
	private List<Pasajero> pasajeros;
	
	public ReservaBuilder id(Long id) {
		this.id = id;
		return this;
	}
	
	public ReservaBuilder codigoReserva(String codigoReserva) {
		this.codigoReserva = codigoReserva;
		return this;
	}
	
	public ReservaBuilder createdAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	
	public ReservaBuilder total(BigDecimal total) {
		this.total = total;
		return this;
	}
	
	public ReservaBuilder itinerario(Itinerario itinerario) {
		this.itinerario = itinerario;
		return this;
	}
	
	public ReservaBuilder contacto(Contacto contacto) {
		this.contacto = contacto;
		return this;
	}
	
	public ReservaBuilder pago(Pago pago) {
		this.pago = pago;
		return this;
	}
	
	public ReservaBuilder pasajeros(List<Pasajero> pasajeros) {
		this.pasajeros = pasajeros;
		return this;
	}
	
	public Reserva build() {
		return new Reserva(this);
	}
}
