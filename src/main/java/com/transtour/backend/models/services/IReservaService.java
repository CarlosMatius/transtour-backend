package com.transtour.backend.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.transtour.backend.models.dto.ReservaDTO;

public interface IReservaService {

	public ReservaDTO save(ReservaDTO reservaDTO);
	
	public ReservaDTO findById(Long id);
	
	public ReservaDTO findByCodigoReserva(String codigoReserva);
	
	public List<ReservaDTO> findAll();
	
	public Page<ReservaDTO> findAll(Pageable page);
	
	public void delete(Long id);
}
