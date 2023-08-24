package com.transtour.backend.models.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.transtour.backend.models.dto.ItinerarioDTO;

public interface IItinerarioService {

	public ItinerarioDTO save(ItinerarioDTO itinerarioDTO);
	
	public ItinerarioDTO findById(Long id);
	
	public ItinerarioDTO findByIdAndEmpresaId(Long id, Long empresaId);
	
	public List<ItinerarioDTO> findByFechaAndDestino(LocalDate fechaEmbarque, String nombreDestino);
	
	public List<ItinerarioDTO> findAll();
	
	public List<ItinerarioDTO> findAllByEmpresaId(Long empresaId);
	
	public Page<ItinerarioDTO> findAllPage(Pageable page);
	
	public Page<ItinerarioDTO> findAllByEmpresaIdPage(Long empresaId, Pageable page);
	
	public void delete(Long id);
}