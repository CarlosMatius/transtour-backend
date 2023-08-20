package com.transtour.backend.models.services;

import java.util.List;

import com.transtour.backend.models.dto.response.MuelleResponse;

public interface IMuelleService {

	public MuelleResponse save(MuelleResponse muelleDto);
	
	public MuelleResponse findById(Long id);
	
	public List<MuelleResponse> findAll();
	
	public void delete(Long id);
}
