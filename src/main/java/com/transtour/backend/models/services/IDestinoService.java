package com.transtour.backend.models.services;

import java.util.List;

import com.transtour.backend.models.dto.DestinoDto;

public interface IDestinoService {

	public List<DestinoDto> findAll();
}
