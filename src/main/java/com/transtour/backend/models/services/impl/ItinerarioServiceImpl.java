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
	public ItinerarioDTO save(ItinerarioDTO itinerarioDTO) {
		Itinerario itinerario = modelMapper.map(itinerarioDTO, Itinerario.class);
		itinerario = itinerarioDao.save(itinerario);
		return modelMapper.map(itinerario, ItinerarioDTO.class);
	}

	@Override
	@Transactional(readOnly = true)
	public ItinerarioDTO findById(Long id) {
		return modelMapper.map(itinerarioDao.findById(id), ItinerarioDTO.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public ItinerarioDTO findByIdAndEmpresaId(Long id, Long empresaId) {
		return modelMapper.map(itinerarioDao.findItinerarioByIdAndEmpresaId(id, empresaId), ItinerarioDTO.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public  List<ItinerarioDTO> findByFechaAndDestino(LocalDate fechaEmbarque, String nombreDestino) {
		List<ItinerarioDTO> dtoList = new ArrayList<>();
		Iterable<Itinerario> itinerarios = itinerarioDao.findItinerariosByFechaEmbarqueAndDestino(fechaEmbarque, nombreDestino);
		for(Itinerario itinerario : itinerarios) {
			ItinerarioDTO itinerarioDto = modelMapper.map(itinerario, ItinerarioDTO.class);
			dtoList.add(itinerarioDto);
		}
		return dtoList;
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
	public List<ItinerarioDTO> findAllByEmpresaId(Long empresaId) {
		List<ItinerarioDTO> dtoList = new ArrayList<>();
		Iterable<Itinerario> itinerarios = itinerarioDao.findItinerariosByEmpresaId(empresaId);
		
		for(Itinerario itinerario : itinerarios) {
			ItinerarioDTO itinerarioDTO = modelMapper.map(itinerario, ItinerarioDTO.class);
			dtoList.add(itinerarioDTO);	
		}
		return dtoList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<ItinerarioDTO> findAllPage(Pageable page) {
		Page<Itinerario> paginaItinerarios = itinerarioDao.findAll(page);
		return paginaItinerarios.map(itinerario -> modelMapper.map(itinerario, ItinerarioDTO.class));
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<ItinerarioDTO> findAllByEmpresaIdPage(Long empresaId, Pageable page) {
		Page<Itinerario> paginaItinerario = itinerarioDao.findItinerariosByEmpresaId(empresaId, page);
		return paginaItinerario.map(itinerario -> modelMapper.map(itinerario, ItinerarioDTO.class));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		itinerarioDao.deleteById(id);
	}

}
