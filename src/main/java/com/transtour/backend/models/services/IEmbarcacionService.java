package com.transtour.backend.models.services;

import java.util.List;

import com.transtour.backend.models.dto.EmbarcacionDto;

public interface IEmbarcacionService {

	public EmbarcacionDto save(EmbarcacionDto embarcacionDto);
	
	public EmbarcacionDto findById(Long id);
	
	public List<EmbarcacionDto> findAll();
	
	public void delete(Long id);
}
