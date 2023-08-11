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
import com.transtour.backend.models.dto.EmbarcacionDTO;
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
	public EmbarcacionDTO save(EmbarcacionDTO embarcacionDto) {
		Embarcacion embarcacion = modelMapper.map(embarcacionDto, Embarcacion.class);
		embarcacion = embarcacionDao.save(embarcacion);
		return modelMapper.map(embarcacion, EmbarcacionDTO.class);
	}

	@Override
	@Transactional(readOnly = true)
	public EmbarcacionDTO findById(Long id) {
		return modelMapper.map(embarcacionDao.findById(id).orElse(null), EmbarcacionDTO.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EmbarcacionDTO> findAll() {
		List<EmbarcacionDTO> dtoList = new ArrayList<>();
		Iterable<Embarcacion> embarcaciones = embarcacionDao.findAll();
		for(Embarcacion embarcacion : embarcaciones) {
			EmbarcacionDTO destinoDto = modelMapper.map(embarcacion, EmbarcacionDTO.class);
			dtoList.add(destinoDto);	
		}
		return dtoList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<EmbarcacionDTO> findAll(Pageable page) {
		Page<Embarcacion> paginaEmbarcaciones = embarcacionDao.findAll(page);
		return paginaEmbarcaciones.map(embarcacion -> modelMapper.map(embarcacion, EmbarcacionDTO.class));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		embarcacionDao.deleteById(id);
	}
}
