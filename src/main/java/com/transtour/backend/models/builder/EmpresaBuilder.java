package com.transtour.backend.models.builder;

import java.util.List;

import com.transtour.backend.models.entity.Embarcacion;
import com.transtour.backend.models.entity.Empresa;
import com.transtour.backend.models.entity.Usuario;

import lombok.Getter;

@Getter
public class EmpresaBuilder {

	private Long id;
	private String nombre;
	private String nit;
	private String email;
	private String telefono;
	private String imagen;
	private boolean enabled;
	private List<Usuario> usuarios;
	private List<Embarcacion> embarcaciones;
	
	/*
	 * Methods
	 */
	
	public EmpresaBuilder id(Long id) {
		this.id = id;
		return this;
	}
	
	public EmpresaBuilder nombre(String nombre) {
		this.nombre = nombre;
		return this;
	}
	
	public EmpresaBuilder nit(String nit) {
		this.nit = nit;
		return this;
	}
	
	public EmpresaBuilder email(String email) {
		this.email = email;
		return this;
	}
	
	public EmpresaBuilder telefono(String telefono) {
		this.telefono = telefono;
		return this;
	}
	
	public EmpresaBuilder imagen(String imagen) {
		this.imagen = imagen;
		return this;
	}
	
	public EmpresaBuilder enabled(boolean enabled) {
		this.enabled = enabled;
		return this;
	}
	
	public EmpresaBuilder usuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
		return this;
	}
	
	public EmpresaBuilder embarcaciones(List<Embarcacion> embarcaciones) {
		this.embarcaciones = embarcaciones;
		return this;
	}
	
	public Empresa build() {
		return new Empresa(this);
	}	
}