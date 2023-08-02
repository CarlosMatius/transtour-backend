package com.transtour.backend.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.transtour.backend.models.builder.PasajeroBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pasajeros")
@NoArgsConstructor
@Getter
@Setter
public class Pasajero implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String apellido;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_identificacion")
	private TipoIdentificacion tipoIdentificacion;
	private String identificacion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reservacion")
	private Reserva reserva;
	
	public Pasajero(PasajeroBuilder builder) {
		this.id = builder.getId();
		this.nombre = builder.getNombre();
		this.apellido = builder.getApellido();
		this.tipoIdentificacion = builder.getTipoIdentificacion();
		this.identificacion = builder.getIdentificacion();
		this.reserva = builder.getReserva();
	}
	
	private static final long serialVersionUID = 1L;
}
