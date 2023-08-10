package com.transtour.backend.models.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.dao.IEmbarcacionDao;
import com.transtour.backend.models.dto.EmbarcacionDto;
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
	public EmbarcacionDto save(EmbarcacionDto embarcacionDto) {
		Embarcacion embarcacion = modelMapper.map(embarcacionDto, Embarcacion.class);
		embarcacion = embarcacionDao.save(embarcacion);
		return modelMapper.map(embarcacion, EmbarcacionDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public EmbarcacionDto findById(Long id) {
		return modelMapper.map(embarcacionDao.findById(id).orElse(null), EmbarcacionDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EmbarcacionDto> findAll() {
		List<EmbarcacionDto> dtoList = new ArrayList<>();
		Iterable<Embarcacion> embarcaciones = embarcacionDao.findAll();
		for(Embarcacion embarcacion : embarcaciones) {
			EmbarcacionDto destinoDto = modelMapper.map(embarcacion, EmbarcacionDto.class);
			dtoList.add(destinoDto);	
		}
		return dtoList;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		embarcacionDao.deleteById(id);
	}
}
