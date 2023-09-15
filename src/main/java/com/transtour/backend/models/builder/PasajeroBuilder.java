package com.transtour.backend.models.builder;

import com.transtour.backend.models.entity.Pasajero;
import com.transtour.backend.models.entity.TipoIdentificacion;

import lombok.Getter;

@Getter
public class PasajeroBuilder {

	private Long id;
	private String nombre;
	private String apellido;
	private TipoIdentificacion tipoIdentificacion;
	private String identificacion;
	
	public PasajeroBuilder id(Long id) {
		this.id = id;
		return this;
	}
	
	public PasajeroBuilder nombre(String nombre) {
		this.nombre = nombre;
		return this;
	}
	
	public PasajeroBuilder apellido(String apellido) {
		this.apellido = apellido;
		return this;
	}
	
	public PasajeroBuilder tipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
		return this;
	}
	
	public PasajeroBuilder identificacion(String identificacion) {
		this.identificacion = identificacion;
		return this;
	}
	
	public Pasajero build() {
		return new Pasajero(this);
	}
}
