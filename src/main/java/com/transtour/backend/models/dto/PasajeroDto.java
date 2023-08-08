package com.transtour.backend.models.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PasajeroDto {

	private Long id;
	private String nombre;
	private String apellido;
	private TipoIdentificacionDto tipoIdentificacion;
	private String identificacion;
	private ReservaDto reserva;
}
