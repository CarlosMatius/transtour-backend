package com.transtour.backend.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.transtour.backend.models.dto.response.DestinoResponse;

public interface IDestinoService {
	
	public DestinoResponse save(DestinoResponse destinoDto);
	
	public DestinoResponse findById(Long id);
	
	public List<DestinoResponse> findAll();
	
	public Page<DestinoResponse> findAll(Pageable page);
	
	public void delete(Long id);
	
}
