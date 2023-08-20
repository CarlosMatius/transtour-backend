package com.transtour.backend.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.transtour.backend.models.builder.PagoBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pagos")
@NoArgsConstructor @Getter @Setter
public class Pago implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre_comercio", nullable = false, length = 45)
	private String nombreComercio;
	
	@Column(nullable = false, length = 45)
	private String descripcion;
	
	@Column(name = "numero_recibo", nullable = false, length = 45)
	private String numeroRecibo;
	
	@Column(nullable = false, length = 15)
	private String estado;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "reserva")
	private Reserva reserva;
	
	public Pago(PagoBuilder builder) {
		this.id = builder.getId();
		this.nombreComercio = builder.getNombreComercio();
		this.descripcion = builder.getDescripcion();
		this.numeroRecibo = builder.getNumeroRecibo();
		this.estado = builder.getEstado();
		this.createdAt = builder.getCreatedAt();
		this.reserva = builder.getReserva();
	}
	
	private static final long serialVersionUID = 1L;
}