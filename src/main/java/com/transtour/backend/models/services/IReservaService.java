package com.transtour.backend.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.transtour.backend.models.dto.response.ReservaResponse;

public interface IReservaService {

	public ReservaResponse save(ReservaResponse reservaDto);
	
	public ReservaResponse findById(Long id);
	
	public ReservaResponse findByCodigoReserva(String codigoReserva);
	
	public List<ReservaResponse> findAll();
	
	public Page<ReservaResponse> findAll(Pageable page);
	
	public void delete(Long id);
}
