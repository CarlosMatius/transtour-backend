package com.transtour.backend.models.builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import com.transtour.backend.models.entity.Destino;
import com.transtour.backend.models.entity.Embarcacion;
import com.transtour.backend.models.entity.Itinerario;
import com.transtour.backend.models.entity.Muelle;

import lombok.Getter;

@Getter
public class ItinerarioBuilder {

	private Long id;
	private LocalDate fechaEmbarque;
	private LocalTime horaSalida;
	private LocalTime horaRegreso;
	private int cupos;
	private BigDecimal precio;
	private Embarcacion embarcacion;
	private Destino destino;
	private Muelle muelle;
	
	public ItinerarioBuilder id(Long id) {
		this.id = id;
		return this;
	}
	
	public ItinerarioBuilder fechaEmbarque(LocalDate fechaEmbarque) {
		this.fechaEmbarque = fechaEmbarque;
		return this;
	}
	
	public ItinerarioBuilder horaSalidad(LocalTime horaSalida) {
		this.horaSalida = horaSalida;
		return this;
	}
	
	public ItinerarioBuilder horaRegreso(LocalTime horaRegreso) {
		this.horaRegreso = horaRegreso;
		return this;
	}
	
	public ItinerarioBuilder cupos(int cupos) {
		this.cupos = cupos;
		return this;
	}
	
	public ItinerarioBuilder precio(BigDecimal precio) {
		this.precio = precio;
		return this;
	}
	
	public ItinerarioBuilder embarcacion(Embarcacion embarcacion) {
		this.embarcacion = embarcacion;
		return this;
	}
	
	public ItinerarioBuilder destino(Destino destino) {
		this.destino = destino;
		return this;
	}
	
	public ItinerarioBuilder muelle(Muelle muelle) {
		this.muelle = muelle;
		return this;
	}
	
	public Itinerario build() {
		return new Itinerario(this);
	}
}
