package com.transtour.backend.models.builder;

import com.transtour.backend.models.entity.Rol;

import lombok.Getter;

@Getter
public class RolBuilder {

	private Long id;
	private String nombre;
	
	/*
	 * Methods
	 */
	public RolBuilder id(Long id) {
		this.id = id;
		return this;
	}
	
	public RolBuilder nombre(String nombre) {
		this.nombre = nombre;
		return this;
	}
	
	public Rol build() {
		return new Rol(this);
	}
}
