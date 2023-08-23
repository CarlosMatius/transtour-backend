package com.transtour.backend.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.transtour.backend.models.dto.EmbarcacionDTO;

public interface IEmbarcacionService {

	public EmbarcacionDTO save(EmbarcacionDTO embarcacionDTO);
	
	public EmbarcacionDTO findById(Long id);
	
	public List<EmbarcacionDTO> findAll();
	
	public Page<EmbarcacionDTO> findAll(Pageable page);
	
	public void delete(Long id);
}