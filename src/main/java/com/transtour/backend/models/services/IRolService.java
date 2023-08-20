package com.transtour.backend.models.services;

import java.util.List;

import com.transtour.backend.models.dto.response.RolResponse;

public interface IRolService {

	public RolResponse save(RolResponse rolDto);
	
	public List<RolResponse> findAll();
}
