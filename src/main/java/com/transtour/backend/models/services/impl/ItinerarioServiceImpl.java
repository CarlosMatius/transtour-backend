package com.transtour.backend.models.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.dao.IItinerarioDao;
import com.transtour.backend.models.dto.response.ItinerarioResponse;
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
	public ItinerarioResponse save(ItinerarioResponse itinerarioDTO) {
		Itinerario itinerario = modelMapper.map(itinerarioDTO, Itinerario.class);
		itinerario = itinerarioDao.save(itinerario);
		return modelMapper.map(itinerario, ItinerarioResponse.class);
	}

	@Override
	@Transactional(readOnly = true)
	public ItinerarioResponse findById(Long id) {
		return modelMapper.map(itinerarioDao.findById(id), ItinerarioResponse.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public  List<ItinerarioResponse> findByFechaAndDestino(LocalDate fechaEmbarque, String nombreDestino) {
		List<ItinerarioResponse> dtoList = new ArrayList<>();
		Iterable<Itinerario> itinerarios = itinerarioDao.findByFechaAndDestino(fechaEmbarque, nombreDestino);
		for(Itinerario itinerario : itinerarios) {
			ItinerarioResponse destinoDto = modelMapper.map(itinerario, ItinerarioResponse.class);
			dtoList.add(destinoDto);
		}
		return dtoList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ItinerarioResponse> findAll() {
		List<ItinerarioResponse> dtoList = new ArrayList<>();
		Iterable<Itinerario> itinerarios = itinerarioDao.findAll();
		for(Itinerario itinerario : itinerarios) {
			ItinerarioResponse destinoDto = modelMapper.map(itinerario, ItinerarioResponse.class);
			dtoList.add(destinoDto);	
		}
		return dtoList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<ItinerarioResponse> findAll(Pageable page) {
		Page<Itinerario> paginaItinerarios = itinerarioDao.findAll(page);
		return paginaItinerarios.map(itinerario -> modelMapper.map(itinerario, ItinerarioResponse.class));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		itinerarioDao.deleteById(id);
	}

}
