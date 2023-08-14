package com.transtour.backend.models.builder;

import java.util.List;

import com.transtour.backend.models.entity.Empresa;
import com.transtour.backend.models.entity.Rol;
import com.transtour.backend.models.entity.TipoIdentificacion;
import com.transtour.backend.models.entity.Usuario;

import lombok.Getter;

@Getter
public class UsuarioBuilder {

	private Long id;
	private String nombre;
	private String apellido;
	private TipoIdentificacion tipoIdentificacion;
	private String identificacion;
	private String user;
	private String clave;
	private Boolean enabled;
	private Empresa empresa;
	private List<Rol> roles;
	
	/*
	 * Methods
	 */
	
	public UsuarioBuilder id(Long id) {
		this.id = id;
		return this;
	}
	
	public UsuarioBuilder nombre(String nombre) {
		this.nombre = nombre;
		return this;
	}
	
	public UsuarioBuilder apellido(String apellido) {
		this.apellido = apellido;
		return this;
	}
	
	public UsuarioBuilder tipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
		this.tipoIdentificacion = tipoIdentificacion;
		return this;
	}
	
	public UsuarioBuilder identificacion(String identificacion) {
		this.identificacion = identificacion;
		return this;
	}
	
	public UsuarioBuilder user(String user) {
		this.user = user;
		return this;
	}
	
	public UsuarioBuilder clave(String clave) {
		this.clave = clave;
		return this;
	}
	
	public UsuarioBuilder enabled(Boolean enabled) {
		this.enabled = enabled;
		return this;
	}
	
	public UsuarioBuilder empresa(Empresa empresa) {
		this.empresa = empresa;
		return this;
	}
	
	public UsuarioBuilder roles(List<Rol> roles) {
		this.roles = roles;
		return this;
	}
	
	public Usuario build() {
		return new Usuario(this);
	}
}
