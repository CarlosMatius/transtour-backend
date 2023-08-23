package com.transtour.backend.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.transtour.backend.models.dto.DestinoDTO;

public interface IDestinoService {
	
	public DestinoDTO save(DestinoDTO destinoDTO);
	
	public DestinoDTO findById(Long id);
	
	public List<DestinoDTO> findAll();
	
	public Page<DestinoDTO> findAll(Pageable page);
	
	public void delete(Long id);	
}