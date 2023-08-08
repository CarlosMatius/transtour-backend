package com.transtour.backend.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponsableReservaDto {

	private Long id;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private ReservaDto reserva;
}
