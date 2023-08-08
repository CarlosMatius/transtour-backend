package com.transtour.backend.models.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmpresaDto {

	private Long id;
	private String nombre;
	private String nit;
	private String email;
	private String telefono;
	private String imagen;
	private boolean enabled;
	private List<UsuarioDto> usuarios;
	private List<EmbarcacionDto> embarcaciones;
}
