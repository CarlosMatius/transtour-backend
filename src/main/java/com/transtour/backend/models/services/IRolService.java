package com.transtour.backend.models.services;

import java.util.List;

import com.transtour.backend.models.dto.RolDTO;

public interface IRolService {

	public RolDTO save(RolDTO rolDto);
	
	public RolDTO findById(Long id);
	
	public List<RolDTO> findAll();
}
