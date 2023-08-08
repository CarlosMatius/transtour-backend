package com.transtour.backend.models.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.dao.IDestinoDao;
import com.transtour.backend.models.dto.DestinoDto;
import com.transtour.backend.models.entity.Destino;
import com.transtour.backend.models.services.IDestinoService;

@Service
public class DestinoServiceImpl implements IDestinoService{

	@Autowired
	private IDestinoDao destinoDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional(readOnly = true)
	public List<DestinoDto> findAll() {
		List<DestinoDto> dtoList = new ArrayList<>();
		Iterable<Destino> destinos = destinoDao.findAll();
		for(Destino destino : destinos) {
			DestinoDto destinoDto = modelMapper.map(destino, DestinoDto.class);
			dtoList.add(destinoDto);	
		}
		return dtoList;
	}
}
