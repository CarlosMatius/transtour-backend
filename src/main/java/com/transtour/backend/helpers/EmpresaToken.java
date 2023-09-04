package com.transtour.backend.helpers;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmpresaToken implements Serializable{

	private Long id;
	private String nombre;
	
	private static final long serialVersionUID = 1L;
}