package com.transtour.backend.models.builder;

import com.transtour.backend.models.entity.TipoIdentificacion;

import lombok.Getter;

@Getter
public class TipoIdentificacionBuilder {
	
	private Long id;
	private String nombre;
	
	public TipoIdentificacionBuilder id(Long id) {
		this.id = id;
		return this;
	}
	
	public TipoIdentificacionBuilder nombre(String nombre) {
		this.nombre = nombre;
		return this;
	}
	
	public TipoIdentificacion build() {
		return new TipoIdentificacion(this);
	}
}
