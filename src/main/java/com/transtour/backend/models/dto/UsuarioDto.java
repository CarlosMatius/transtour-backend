package com.transtour.backend.models.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioDTO implements Serializable{

	private Long id;
	
	@NotEmpty(message = "no puede ser vacio")
	@Size(min = 3, message = "el nombre debe ser mayor a 3 caracteres")
	private String nombre;
	
	@NotEmpty(message = "no puede ser vacio")
	@Size(min = 5, message = "el apellido debe ser mayor a 5 caracteres")
	private String apellido;
	
	@NotNull(message = "no puede ser vacio")
	private TipoIdentificacionDTO tipoIdentificacion;
	
	@NotEmpty(message = "no puede ser vacio")
	@Size(min = 5, message = "el numero debe ser mayor a 5 caracteres")
	private String identificacion;
	
	@NotEmpty(message = "no puede ser vacio")
	@Size(min = 5, message = "el usuario debe ser mayor a 5 caracteres")
	private String user;
	
	@NotEmpty(message = "no puede ser vacio")
	private String clave;
	private boolean enabled;
	
	@NotNull(message = "no puede ser vacio")
	private EmpresaDTO empresa;
	
	private List<RolDTO> roles;
	
	private static final long serialVersionUID = 1L;
}
