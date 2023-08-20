package com.transtour.backend.models.dto.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReservaRequest implements Serializable{

	@NotEmpty(message = "no puede ser vacio")
	@Size(min = 5, message = "el codigo debe ser mayor a 5 caracteres")
	private String codigoReserva;
	
	private LocalDateTime createdAt;
	
	@NotNull(message = "no puede ser vacio")
	private BigDecimal total;
	
	private static final long serialVersionUID = 1L;
}