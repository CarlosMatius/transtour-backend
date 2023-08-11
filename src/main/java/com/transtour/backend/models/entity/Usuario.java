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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.transtour.backend.models.builder.UsuarioBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor @Getter @Setter
public class Usuario implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 30)
	private String nombre;
	
	@Column(unique = true, nullable = false, length = 30)
	private String apellido;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_identificacion", nullable = false)
	private TipoIdentificacion tipoIdentificacion;
	
	@Column(unique = true, nullable = false, length = 15)
	private String identificacion;
	
	@Column(name = "usuario", unique = true, nullable = false, length = 25)
	private String user;
	
	@Column(nullable = false, length = 100)
	private String clave;
	private boolean enabled;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "empresa", nullable = false)
	private Empresa empresa;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "usuarios_roles", 
			joinColumns = @JoinColumn(name = "usuario"), 
			inverseJoinColumns = @JoinColumn(name = "rol"), 
			uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario", "rol"})}
	)
	private List<Rol> roles;
	
	/*
	 * Constructors
	 */
	
	public Usuario(UsuarioBuilder builder) {
		this.id = builder.getId();
		this.nombre = builder.getNombre();
		this.apellido = builder.getApellido();
		this.tipoIdentificacion = builder.getTipoIdentificacion();
		this.identificacion = builder.getIdentificacion();
		this.user = builder.getUser();
		this.clave = builder.getClave();
		this.enabled = builder.isEnabled();
		this.empresa = builder.getEmpresa();
		this.roles = builder.getRoles();
	}
	
	private static final long serialVersionUID = 1L;
}
