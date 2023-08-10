package com.transtour.backend.models.services;

import java.util.List;

import com.transtour.backend.models.dto.MuelleDto;

public interface IMuelleService {

	public MuelleDto save(MuelleDto muelleDto);
	
	public MuelleDto findById(Long id);
	
	public List<MuelleDto> findAll();
	
	public void delete(Long id);
}
