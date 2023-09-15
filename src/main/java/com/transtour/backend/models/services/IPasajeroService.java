package com.transtour.backend.models.services;

import com.transtour.backend.models.dto.PasajeroDTO;

public interface IPasajeroService {

	public PasajeroDTO save(PasajeroDTO pasajeroDTO);
	
	public PasajeroDTO findById(Long id);
}