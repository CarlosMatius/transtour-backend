package com.transtour.backend.models.services.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.dao.IPasajeroDao;
import com.transtour.backend.models.dto.PasajeroDto;
import com.transtour.backend.models.entity.Pasajero;
import com.transtour.backend.models.services.IPasajeroService;

@Service
public class PasajeroServiceImpl implements IPasajeroService{

	@Autowired
	private IPasajeroDao pasajeroDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public PasajeroDto save(PasajeroDto pasajeroDto) {
		Pasajero pasajero = modelMapper.map(pasajeroDto, Pasajero.class);
		pasajero = pasajeroDao.save(pasajero);
		return modelMapper.map(pasajero, PasajeroDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public PasajeroDto findById(Long id) {
		return modelMapper.map(pasajeroDao.findById(id).orElse(null), PasajeroDto.class);
	}
}