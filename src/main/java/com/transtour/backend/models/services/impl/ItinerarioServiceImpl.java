package com.transtour.backend.models.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.dao.IItinerarioDao;
import com.transtour.backend.models.dto.ItinerarioDto;
import com.transtour.backend.models.entity.Itinerario;
import com.transtour.backend.models.services.IItinerarioService;

@Service
public class ItinerarioServiceImpl implements IItinerarioService{

	@Autowired
	private IItinerarioDao itinerarioDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	@Transactional
	public ItinerarioDto save(ItinerarioDto itinerarioDto) {
		Itinerario itinerario = modelMapper.map(itinerarioDto, Itinerario.class);
		itinerario = itinerarioDao.save(itinerario);
		return modelMapper.map(itinerario, ItinerarioDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public ItinerarioDto findById(Long id) {
		return modelMapper.map(itinerarioDao.findById(id).orElse(null), ItinerarioDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ItinerarioDto> findAll() {
		List<ItinerarioDto> dtoList = new ArrayList<>();
		Iterable<Itinerario> itinerarios = itinerarioDao.findAll();
		for(Itinerario itinerario : itinerarios) {
			ItinerarioDto destinoDto = modelMapper.map(itinerario, ItinerarioDto.class);
			dtoList.add(destinoDto);	
		}
		return dtoList;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		itinerarioDao.deleteById(id);
	}

}
