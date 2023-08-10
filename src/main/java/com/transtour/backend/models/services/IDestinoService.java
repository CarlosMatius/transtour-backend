package com.transtour.backend.models.services;

import java.util.List;

import com.transtour.backend.models.dto.DestinoDto;

public interface IDestinoService {
	
	public DestinoDto save(DestinoDto destinoDto);
	
	public DestinoDto findById(Long id);
	
	public List<DestinoDto> findAll();
	
	public void delete(Long id);
	
}
