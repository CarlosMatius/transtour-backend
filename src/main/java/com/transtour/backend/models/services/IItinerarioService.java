package com.transtour.backend.models.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.transtour.backend.models.dto.ItinerarioDTO;

public interface IItinerarioService {

	public ItinerarioDTO save(ItinerarioDTO itinerarioDTO);
	
	public ItinerarioDTO findById(Long id);
	
	public List<ItinerarioDTO> findByFechaAndDestino(LocalDate fechaEmbarque, String nombreDestino);
	
	public List<ItinerarioDTO> findAll();
	
	public Page<ItinerarioDTO> findAll(Pageable page);
	
	public void delete(Long id);
}
