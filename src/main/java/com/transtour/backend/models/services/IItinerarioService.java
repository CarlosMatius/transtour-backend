package com.transtour.backend.models.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.transtour.backend.models.dto.response.ItinerarioResponse;

public interface IItinerarioService {

	public ItinerarioResponse save(ItinerarioResponse itinerarioDTO);
	
	public ItinerarioResponse findById(Long id);
	
	public List<ItinerarioResponse> findByFechaAndDestino(LocalDate fechaEmbarque, String nombreDestino);
	
	public List<ItinerarioResponse> findAll();
	
	public Page<ItinerarioResponse> findAll(Pageable page);
	
	public void delete(Long id);
}
