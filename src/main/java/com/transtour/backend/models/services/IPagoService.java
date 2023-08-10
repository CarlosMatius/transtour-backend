package com.transtour.backend.models.services;

import java.util.List;

import com.transtour.backend.models.dto.PagoDto;

public interface IPagoService {

	public PagoDto save(PagoDto pagoDto);
	
	public PagoDto findByNombreComercio(String nombreComercio);
	
	public List<PagoDto> findAll();
}
