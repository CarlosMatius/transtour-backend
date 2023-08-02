package com.transtour.backend.models.builder;

import com.transtour.backend.models.entity.Contacto;
import com.transtour.backend.models.entity.Reserva;

import lombok.Getter;

@Getter
public class ContactoBuilder {

	private Long id;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private Reserva reserva;
	
	public ContactoBuilder id(Long id) {
		this.id = id;
		return this;
	}
	
	public ContactoBuilder nombre(String nombre) {
		this.nombre = nombre;
		return this;
	}
	
	public ContactoBuilder apellido(String apellido) {
		this.apellido = apellido;
		return this;
	}
	
	public ContactoBuilder email(String email) {
		this.email = email;
		return this;
	}
	
	public ContactoBuilder telefono(String telefono) {
		this.telefono = telefono;
		return this;
	}
	
	public ContactoBuilder reserva(Reserva reserva) {
		this.reserva = reserva;
		return this;
	}
	
	public Contacto build() {
		return new Contacto(this);
	}
}
