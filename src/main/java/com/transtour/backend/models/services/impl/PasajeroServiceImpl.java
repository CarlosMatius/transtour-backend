package com.transtour.backend.models.services.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.dao.IPasajeroDao;
import com.transtour.backend.models.dto.PasajeroDTO;
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
	public PasajeroDTO save(PasajeroDTO pasajeroDTO) {
		Pasajero pasajero = modelMapper.map(pasajeroDTO, Pasajero.class);
		pasajero = pasajeroDao.save(pasajero);
		return modelMapper.map(pasajero, PasajeroDTO.class);
	}

	@Override
	@Transactional(readOnly = true)
	public PasajeroDTO findById(Long id) {
		return modelMapper.map(pasajeroDao.findById(id), PasajeroDTO.class);
	}
}