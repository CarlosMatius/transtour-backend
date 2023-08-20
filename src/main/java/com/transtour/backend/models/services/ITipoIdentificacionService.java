package com.transtour.backend.models.services;

import java.util.List;

import com.transtour.backend.models.dto.response.TipoIdentificacionResponse;

public interface ITipoIdentificacionService {

	public TipoIdentificacionResponse save(TipoIdentificacionResponse tipoIdentificacionDto);
	
	public TipoIdentificacionResponse findById(Long id);
	
	public List<TipoIdentificacionResponse> findAll();
	
	public void delete(Long id);
}
