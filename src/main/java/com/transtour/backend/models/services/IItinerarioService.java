package com.transtour.backend.models.services;

import java.util.List;

import com.transtour.backend.models.dto.ItinerarioDto;

public interface IItinerarioService {

	public ItinerarioDto save(ItinerarioDto itinerarioDto);
	
	public ItinerarioDto findById(Long id);
	
	public List<ItinerarioDto> findAll();
	
	public void delete(Long id);
}
