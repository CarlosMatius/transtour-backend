package com.transtour.backend.models.builder;

import com.transtour.backend.models.entity.ResponsableReserva;

import lombok.Getter;

@Getter
public class ResponsableReservaBuilder {

	private Long id;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	
	public ResponsableReservaBuilder id(Long id) {
		this.id = id;
		return this;
	}
	
	public ResponsableReservaBuilder nombre(String nombre) {
		this.nombre = nombre;
		return this;
	}
	
	public ResponsableReservaBuilder apellido(String apellido) {
		this.apellido = apellido;
		return this;
	}
	
	public ResponsableReservaBuilder email(String email) {
		this.email = email;
		return this;
	}
	
	public ResponsableReservaBuilder telefono(String telefono) {
		this.telefono = telefono;
		return this;
	}
	
	
	public ResponsableReserva build() {
		return new ResponsableReserva(this);
	}
}
