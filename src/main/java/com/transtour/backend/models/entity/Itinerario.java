package com.transtour.backend.models.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.transtour.backend.models.builder.ItinerarioBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "itinerarios")
@NoArgsConstructor @Getter @Setter
public class Itinerario implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fecha_embarque")
	private LocalDate fechaEmbarque;
	
	@Column(name = "hora_salidad")
	private LocalTime horaSalidad;
	
	@Column(name = "hora_regreso")
	private LocalTime horaRegreso;
	
	private int cupos;
	
	@Column(precision = 10, scale = 2, length = 10)
    private BigDecimal precio;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "embarcacion")
	private Embarcacion embarcacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "destino")
	private Destino destino;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "muelle")
	private Muelle muelle;
	
	/*
	 * Constructors
	 */
	public Itinerario(ItinerarioBuilder builder) {
		this.id = builder.getId();
		this.fechaEmbarque = builder.getFechaEmbarque();
		this.horaSalidad = builder.getHoraSalidad();
		this.horaRegreso = builder.getHoraRegreso();
		this.cupos = builder.getCupos();
		this.precio = builder.getPrecio();
		this.embarcacion = builder.getEmbarcacion();
		this.destino = builder.getDestino();
		this.muelle = builder.getMuelle();
	}
	
	private static final long serialVersionUID = 1L;
}
