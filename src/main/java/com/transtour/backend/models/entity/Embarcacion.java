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

import com.transtour.backend.models.builder.EmbarcacionBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "embarcaciones")
@NoArgsConstructor @Getter @Setter
public class Embarcacion implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 45)
	private String nombre;
	
	@Column(nullable = false)
	private int capacidad;
	
	private boolean enabled;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "empresa", nullable = false)
	private Empresa empresa;
	
	/*
	 * Constructors
	 */
	
	public Embarcacion(EmbarcacionBuilder builder) {
		this.id = builder.getId();
		this.nombre = builder.getNombre();
		this.capacidad = builder.getCapacidad();
		this.enabled = builder.isEnabled();
		this.empresa = builder.getEmpresa();
	}
	
	private static final long serialVersionUID = 1L;
}