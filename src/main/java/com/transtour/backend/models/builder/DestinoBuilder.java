package com.transtour.backend.models.builder;

import com.transtour.backend.models.entity.Destino;

import lombok.Getter;

@Getter
public class DestinoBuilder {

	private Long id;
	private String nombre;
	private boolean enabled;
	
	/*
	 * Methods
	 */
	
	public DestinoBuilder id(Long id) {
		this.id = id;
		return this;
	}
	
	public DestinoBuilder nombre(String nombre) {
		this.nombre = nombre;
		return this;
	}
	
	public DestinoBuilder enabled(boolean enabled) {
		this.enabled = enabled;
		return this;
	}
	
	public Destino build() {
		return new Destino(this);
	}
}
