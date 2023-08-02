package com.transtour.backend.models.builder;

import java.time.LocalDateTime;

import com.transtour.backend.models.entity.Pago;
import com.transtour.backend.models.entity.Reserva;

import lombok.Getter;

@Getter
public class PagoBuilder {

	private Long id;
	private String nombreComercio;
	private String descripcion;
	private String numeroRecibo;
	private String estado;
	private LocalDateTime createdAt;
	private Reserva reserva;
	
	public PagoBuilder id(Long id) {
		this.id = id;
		return this;
	}
	
	public PagoBuilder nombreComercio(String nombreComercio) {
		this.nombreComercio = nombreComercio;
		return this;
	}
	
	public PagoBuilder descripcion(String descripcion) {
		this.descripcion = descripcion;
		return this;
	}
	
	public PagoBuilder numeroRecibo(String numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
		return this;
	}
	
	public PagoBuilder estado(String estado) {
		this.estado = estado;
		return this;
	}
	
	public PagoBuilder createdAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	
	public PagoBuilder reserva(Reserva reserva) {
		this.reserva = reserva;
		return this;
	}
	
	public Pago build() {
		return new Pago(this);
	}
}
