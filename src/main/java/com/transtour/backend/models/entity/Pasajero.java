package com.transtour.backend.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
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
@NoArgsConstructor @Getter @Setter
public class Pasajero implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 30)
	private String nombre;
	
	@Column(nullable = false, length = 30)
	private String apellido;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_identificacion", nullable = false)
	private TipoIdentificacion tipoIdentificacion;
	
	@Column(nullable = false, length = 15)
	private String identificacion;
	
	public Pasajero(PasajeroBuilder builder) {
		this.id = builder.getId();
		this.nombre = builder.getNombre();
		this.apellido = builder.getApellido();
		this.tipoIdentificacion = builder.getTipoIdentificacion();
		this.identificacion = builder.getIdentificacion();
	}
	
	private static final long serialVersionUID = 1L;
}