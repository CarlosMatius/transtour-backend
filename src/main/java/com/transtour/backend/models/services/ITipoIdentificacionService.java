package com.transtour.backend.models.services;

import java.util.List;

import com.transtour.backend.models.dto.TipoIdentificacionDTO;

public interface ITipoIdentificacionService {

	public TipoIdentificacionDTO save(TipoIdentificacionDTO tipoIdentificacionDto);
	
	public TipoIdentificacionDTO findById(Long id);
	
	public List<TipoIdentificacionDTO> findAll();
	
	public void delete(Long id);
}
