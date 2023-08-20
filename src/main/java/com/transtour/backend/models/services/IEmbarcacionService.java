package com.transtour.backend.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.transtour.backend.models.dto.response.EmbarcacionResponse;

public interface IEmbarcacionService {

	public EmbarcacionResponse save(EmbarcacionResponse embarcacionDto);
	
	public EmbarcacionResponse findById(Long id);
	
	public List<EmbarcacionResponse> findAll();
	
	public Page<EmbarcacionResponse> findAll(Pageable page);
	
	public void delete(Long id);
}
