package com.transtour.backend.models.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.dao.ITipoIdentificacionDao;
import com.transtour.backend.models.dto.response.TipoIdentificacionResponse;
import com.transtour.backend.models.entity.TipoIdentificacion;
import com.transtour.backend.models.services.ITipoIdentificacionService;

@Service
public class TipoIdentificacionServiceImpl implements ITipoIdentificacionService{

	@Autowired
	private ITipoIdentificacionDao tipoIdentificacionDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	@Transactional
	public TipoIdentificacionResponse save(TipoIdentificacionResponse tipoIdentificacionDto) {
		TipoIdentificacion tipoIdentificacion = modelMapper.map(tipoIdentificacionDto, TipoIdentificacion.class);
		tipoIdentificacion = tipoIdentificacionDao.save(tipoIdentificacion);
		return modelMapper.map(tipoIdentificacion, TipoIdentificacionResponse.class);
	}

	@Override
	@Transactional(readOnly = true)
	public TipoIdentificacionResponse findById(Long id) {
		return modelMapper.map(tipoIdentificacionDao.findById(id), TipoIdentificacionResponse.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoIdentificacionResponse> findAll() {
		List<TipoIdentificacionResponse> dtoList = new ArrayList<>();
		Iterable<TipoIdentificacion> tipos = tipoIdentificacionDao.findAll();
		for(TipoIdentificacion tipoIdentificacion : tipos) {
			TipoIdentificacionResponse tipoIdentificacionDto = modelMapper.map(tipoIdentificacion, TipoIdentificacionResponse.class);
			dtoList.add(tipoIdentificacionDto);	
		}
		return dtoList;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		tipoIdentificacionDao.deleteById(id);
	}

}
