package com.transtour.backend.models.services;

import java.util.List;

import com.transtour.backend.models.dto.RolDto;

public interface IRolService {

	public RolDto save(RolDto rolDto);
	
	public List<RolDto> findAll();
}
