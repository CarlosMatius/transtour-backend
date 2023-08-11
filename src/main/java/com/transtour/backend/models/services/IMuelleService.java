package com.transtour.backend.models.services;

import java.util.List;

import com.transtour.backend.models.dto.MuelleDTO;

public interface IMuelleService {

	public MuelleDTO save(MuelleDTO muelleDto);
	
	public MuelleDTO findById(Long id);
	
	public List<MuelleDTO> findAll();
	
	public void delete(Long id);
}
