package com.transtour.backend.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmbarcacionDto {

	private Long id;
	private String nombre;
	private int capacidad;
	private boolean enabled;
	private EmpresaDto empresa;
}
