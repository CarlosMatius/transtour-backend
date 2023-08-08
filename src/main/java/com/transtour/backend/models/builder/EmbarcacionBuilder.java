package com.transtour.backend.models.builder;

import com.transtour.backend.models.entity.Embarcacion;
import com.transtour.backend.models.entity.Empresa;

import lombok.Getter;

@Getter
public class EmbarcacionBuilder {

	private Long id;
	private String nombre;
	private int capacidad;
	private boolean enabled;
	private Empresa empresa;
	
	/*
	 * Methods
	 */
	
	public EmbarcacionBuilder id(Long id) {
		this.id = id;
		return this;
	}
	
	public EmbarcacionBuilder nombre(String nombre) {
		this.nombre = nombre;
		return this;
	}
	
	public EmbarcacionBuilder capacidad(int capacidad) {
		this.capacidad = capacidad;
		return this;
	}
	
	public EmbarcacionBuilder enabled(boolean enabled) {
		this.enabled = enabled;
		return this;
	}
	
	public EmbarcacionBuilder empresa(Empresa empresa) {
		this.empresa = empresa;
		return this;
	}
	
	public Embarcacion build() {
		return new Embarcacion(this);
	}
}
