package com.transtour.backend.models.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.transtour.backend.models.builder.ReservaBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reservas")
@NoArgsConstructor @Getter @Setter
public class Reserva implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "codigo_reserva", nullable = false, length = 10)
	private String codigoReserva;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
	@Column(precision = 10, scale = 2, length = 10, nullable = false)
    private BigDecimal total;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "itinerario", nullable = false)
	private Itinerario itinerario;
	
	@OneToOne(mappedBy = "reserva")
	private ResponsableReserva responsableReserva;
	
	@OneToOne(mappedBy = "reserva")
	private Pago pago;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Pasajero> pasajeros;
	
	/*
	 * Constructors
	 */
	
	public Reserva(ReservaBuilder builder) {
		this.id = builder.getId();
		this.codigoReserva = builder.getCodigoReserva();
		this.createdAt = builder.getCreatedAt();
		this.total = builder.getTotal();
		this.itinerario = builder.getItinerario();
		this.responsableReserva = builder.getResponsableReserva();
		this.pago = builder.getPago();
		this.pasajeros = builder.getPasajeros();
	}
	
	@PrePersist
    public void prePersist() {
		this.createdAt = LocalDateTime.now();
    }
	
	private static final long serialVersionUID = 1L;
}