package com.transtour.backend.models.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.dao.IEmbarcacionDao;
import com.transtour.backend.models.dto.response.EmbarcacionResponse;
import com.transtour.backend.models.entity.Embarcacion;
import com.transtour.backend.models.services.IEmbarcacionService;

@Service
public class EmbarcacionServiceImpl implements IEmbarcacionService{
	
	@Autowired
	private IEmbarcacionDao embarcacionDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public EmbarcacionResponse save(EmbarcacionResponse embarcacionDto) {
		Embarcacion embarcacion = modelMapper.map(embarcacionDto, Embarcacion.class);
		embarcacion = embarcacionDao.save(embarcacion);
		return modelMapper.map(embarcacion, EmbarcacionResponse.class);
	}

	@Override
	@Transactional(readOnly = true)
	public EmbarcacionResponse findById(Long id) {
		return modelMapper.map(embarcacionDao.findById(id), EmbarcacionResponse.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EmbarcacionResponse> findAll() {
		List<EmbarcacionResponse> dtoList = new ArrayList<>();
		Iterable<Embarcacion> embarcaciones = embarcacionDao.findAll();
		for(Embarcacion embarcacion : embarcaciones) {
			EmbarcacionResponse destinoDto = modelMapper.map(embarcacion, EmbarcacionResponse.class);
			dtoList.add(destinoDto);	
		}
		return dtoList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<EmbarcacionResponse> findAll(Pageable page) {
		Page<Embarcacion> paginaEmbarcaciones = embarcacionDao.findAll(page);
		return paginaEmbarcaciones.map(embarcacion -> modelMapper.map(embarcacion, EmbarcacionResponse.class));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		embarcacionDao.deleteById(id);
	}
}
