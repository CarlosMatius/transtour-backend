package com.transtour.backend.models.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.dao.IDestinoDao;
import com.transtour.backend.models.dto.response.DestinoResponse;
import com.transtour.backend.models.entity.Destino;
import com.transtour.backend.models.services.IDestinoService;

@Service
public class DestinoServiceImpl implements IDestinoService{

	@Autowired
	private IDestinoDao destinoDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public DestinoResponse save(DestinoResponse destinoDto) {
		Destino destino = modelMapper.map(destinoDto, Destino.class);
		destino = destinoDao.save(destino);
		return modelMapper.map(destino, DestinoResponse.class);
	}

	@Override
	@Transactional(readOnly = true)
	public DestinoResponse findById(Long id) {
		return modelMapper.map(destinoDao.findById(id), DestinoResponse.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<DestinoResponse> findAll() {
		List<DestinoResponse> dtoList = new ArrayList<>();
		Iterable<Destino> destinos = destinoDao.findAll();
		for(Destino destino : destinos) {
			DestinoResponse destinoDto = modelMapper.map(destino, DestinoResponse.class);
			dtoList.add(destinoDto);	
		}
		return dtoList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<DestinoResponse> findAll(Pageable page) {
		Page<Destino> paginaDestinos = destinoDao.findAll(page);
		return paginaDestinos.map(destino -> modelMapper.map(destino, DestinoResponse.class));
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		destinoDao.deleteById(id);
	}
}