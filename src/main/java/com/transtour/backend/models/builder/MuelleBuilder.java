package com.transtour.backend.models.builder;

import com.transtour.backend.models.entity.Muelle;

import lombok.Getter;

@Getter
public class MuelleBuilder {

	private Long id;
	private String nombre;
	
	/*
	 * Methods
	 */
	
	public MuelleBuilder id(Long id) {
		this.id = id;
		return this;
	}
	
	public MuelleBuilder nombre(String nombre) {
		this.nombre = nombre;
		return this;
	}
	
	public Muelle build() {
		return new Muelle(this);
	}
}
