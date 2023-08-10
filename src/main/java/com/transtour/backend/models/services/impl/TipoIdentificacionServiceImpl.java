package com.transtour.backend.models.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.dao.ITipoIdentificacionDao;
import com.transtour.backend.models.dto.TipoIdentificacionDto;
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
	public TipoIdentificacionDto save(TipoIdentificacionDto tipoIdentificacionDto) {
		TipoIdentificacion tipoIdentificacion = modelMapper.map(tipoIdentificacionDto, TipoIdentificacion.class);
		tipoIdentificacion = tipoIdentificacionDao.save(tipoIdentificacion);
		return modelMapper.map(tipoIdentificacion, TipoIdentificacionDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public TipoIdentificacionDto findById(Long id) {
		return modelMapper.map(tipoIdentificacionDao.findById(id).orElse(null), TipoIdentificacionDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoIdentificacionDto> findAll() {
		List<TipoIdentificacionDto> dtoList = new ArrayList<>();
		Iterable<TipoIdentificacion> tipos = tipoIdentificacionDao.findAll();
		for(TipoIdentificacion tipoIdentificacion : tipos) {
			TipoIdentificacionDto tipoIdentificacionDto = modelMapper.map(tipoIdentificacion, TipoIdentificacionDto.class);
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
