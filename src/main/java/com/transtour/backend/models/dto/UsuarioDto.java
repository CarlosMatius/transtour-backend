package com.transtour.backend.models.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioDto {

	private Long id;
	private String nombre;
	private String apellido;
	private TipoIdentificacionDto tipoIdentificacion;
	private String identificacion;
	private String user;
	private String clave;
	private boolean enabled;
	private EmpresaDto empresa;
	private List<RolDto> roles;
}
