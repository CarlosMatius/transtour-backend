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
import com.transtour.backend.models.dto.DestinoDTO;
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
	public DestinoDTO save(DestinoDTO destinoDto) {
		Destino destino = modelMapper.map(destinoDto, Destino.class);
		destino = destinoDao.save(destino);
		return modelMapper.map(destino, DestinoDTO.class);
	}

	@Override
	@Transactional(readOnly = true)
	public DestinoDTO findById(Long id) {
		return modelMapper.map(destinoDao.findById(id), DestinoDTO.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<DestinoDTO> findAll() {
		List<DestinoDTO> dtoList = new ArrayList<>();
		Iterable<Destino> destinos = destinoDao.findAll();
		for(Destino destino : destinos) {
			DestinoDTO destinoDto = modelMapper.map(destino, DestinoDTO.class);
			dtoList.add(destinoDto);	
		}
		return dtoList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<DestinoDTO> findAll(Pageable page) {
		Page<Destino> paginaDestinos = destinoDao.findAll(page);
		return paginaDestinos.map(destino -> modelMapper.map(destino, DestinoDTO.class));
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		destinoDao.deleteById(id);
	}
}