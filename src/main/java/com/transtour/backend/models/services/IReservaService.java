package com.transtour.backend.models.services;

import java.util.List;

import com.transtour.backend.models.dto.ReservaDto;

public interface IReservaService {

	public ReservaDto save(ReservaDto reservaDto);
	
	public ReservaDto findById(Long id);
	
	public ReservaDto findByCodigoReserva(String codigoReserva);
	
	public List<ReservaDto> findAll();
	
	public void delete(Long id);
}
