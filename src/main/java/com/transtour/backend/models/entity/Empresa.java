package com.transtour.backend.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.transtour.backend.models.builder.EmpresaBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "empresas")
@NoArgsConstructor @Getter @Setter
public class Empresa implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false, length = 45)
	private String nombre;
	
	@Column(unique = true, nullable = false, length = 20)
	private String nit;
	
	@Column(unique = true, nullable = false, length = 45)
	private String email;
	
	@Column(unique = true, nullable = false, length = 15)
	private String telefono;
	
	@Column(nullable = true)
	private String imagen;
	
	private boolean enabled;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Usuario> usuarios;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Embarcacion> embarcaciones;
	
	/*
	 * Constructors
	 */
	
	public Empresa(EmpresaBuilder builder) {
		this.id = builder.getId();
		this.nombre = builder.getNombre();
		this.nit = builder.getNit();
		this.email = builder.getEmail();
		this.telefono = builder.getTelefono();
		this.imagen = builder.getImagen();
		this.enabled = builder.isEnabled();
		this.usuarios = builder.getUsuarios();
		this.embarcaciones = builder.getEmbarcaciones();
	}

	private static final long serialVersionUID = 1L;
}