package com.transtour.backend.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.transtour.backend.models.builder.TipoIdentificacionBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tipos_identificaciones")
@NoArgsConstructor @Getter @Setter
public class TipoIdentificacion implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String nombre;
	
	/*
	 * Constructors
	 */
	
	public TipoIdentificacion(TipoIdentificacionBuilder builder) {
		this.id = builder.getId();
		this.nombre = builder.getNombre();
	}

	private static final long serialVersionUID = 1L;
}
