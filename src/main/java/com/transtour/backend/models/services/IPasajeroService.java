package com.transtour.backend.models.services;

import com.transtour.backend.models.dto.response.PasajeroResponse;

public interface IPasajeroService {

	public PasajeroResponse save(PasajeroResponse pasajeroDto);
	
	public PasajeroResponse findById(Long id);
}
