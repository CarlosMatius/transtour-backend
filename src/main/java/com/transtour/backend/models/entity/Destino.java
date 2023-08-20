package com.transtour.backend.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.transtour.backend.models.builder.DestinoBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "destinos")
@NoArgsConstructor @Getter @Setter
public class Destino implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false, length = 45)
	private String nombre;
	
	private boolean enabled;
	
	/*
	 * Constructors
	 */

	public Destino(DestinoBuilder builder) {
		this.id = builder.getId();
		this.nombre = builder.getNombre();
		this.enabled = builder.isEnabled();
	}
	
	private static final long serialVersionUID = 1L;
}