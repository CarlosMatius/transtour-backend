package com.transtour.backend.models.dto.response;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MuelleResponse implements Serializable{

	private Long id;
	private String nombre;
	
	private static final long serialVersionUID = 1L;
}