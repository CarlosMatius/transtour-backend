package com.transtour.backend.models.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.dao.IItinerarioDao;
import com.transtour.backend.models.dto.ItinerarioDTO;
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
	public ItinerarioDTO save(ItinerarioDTO itinerarioDto) {
		Itinerario itinerario = modelMapper.map(itinerarioDto, Itinerario.class);
		itinerario = itinerarioDao.save(itinerario);
		return modelMapper.map(itinerario, ItinerarioDTO.class);
	}

	@Override
	@Transactional(readOnly = true)
	public ItinerarioDTO findById(Long id) {
		return modelMapper.map(itinerarioDao.findById(id).orElse(null), ItinerarioDTO.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ItinerarioDTO> findAll() {
		List<ItinerarioDTO> dtoList = new ArrayList<>();
		Iterable<Itinerario> itinerarios = itinerarioDao.findAll();
		for(Itinerario itinerario : itinerarios) {
			ItinerarioDTO destinoDto = modelMapper.map(itinerario, ItinerarioDTO.class);
			dtoList.add(destinoDto);	
		}
		return dtoList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<ItinerarioDTO> findAll(Pageable page) {
		Page<Itinerario> paginaItinerarios = itinerarioDao.findAll(page);
		return paginaItinerarios.map(itinerario -> modelMapper.map(itinerario, ItinerarioDTO.class));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		itinerarioDao.deleteById(id);
	}

}
