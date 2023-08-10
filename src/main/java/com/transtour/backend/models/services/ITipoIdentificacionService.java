package com.transtour.backend.models.services;

import java.util.List;

import com.transtour.backend.models.dto.TipoIdentificacionDto;

public interface ITipoIdentificacionService {

	public TipoIdentificacionDto save(TipoIdentificacionDto tipoIdentificacionDto);
	
	public TipoIdentificacionDto findById(Long id);
	
	public List<TipoIdentificacionDto> findAll();
	
	public void delete(Long id);
}
