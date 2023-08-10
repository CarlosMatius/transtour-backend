package com.transtour.backend.models.services;

import com.transtour.backend.models.dto.PasajeroDto;

public interface IPasajeroService {

	public PasajeroDto save(PasajeroDto pasajeroDto);
	
	public PasajeroDto findById(Long id);
}
