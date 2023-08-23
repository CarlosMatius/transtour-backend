package com.transtour.backend.models.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.dao.IReservaDao;
import com.transtour.backend.models.dto.ReservaDTO;
import com.transtour.backend.models.entity.Reserva;
import com.transtour.backend.models.services.IReservaService;

@Service
public class ReservaServiceImpl implements IReservaService{
	
	@Autowired
	private IReservaDao reservaDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public ReservaDTO save(ReservaDTO reservaDTO) {
		Reserva reserva = modelMapper.map(reservaDTO, Reserva.class);
		reserva = reservaDao.save(reserva);
		return modelMapper.map(reserva, ReservaDTO.class);
	}

	@Override
	@Transactional(readOnly = true)
	public ReservaDTO findById(Long id) {
		return modelMapper.map(reservaDao.findById(id), ReservaDTO.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public ReservaDTO findByCodigoReserva(String codigoReserva) {
		return modelMapper.map(reservaDao.findByCodigoReserva(codigoReserva), ReservaDTO.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ReservaDTO> findAll() {
		List<ReservaDTO> dtoList = new ArrayList<>();
		Iterable<Reserva> reservas = reservaDao.findAll();
		for(Reserva reserva : reservas) {
			ReservaDTO reservaDto = modelMapper.map(reserva, ReservaDTO.class);
			dtoList.add(reservaDto);	
		}
		return dtoList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<ReservaDTO> findAll(Pageable page) {
		Page<Reserva> paginaReservas = reservaDao.findAll(page);
		return paginaReservas.map(reserva -> modelMapper.map(reserva, ReservaDTO.class));
	}

	@Override
	@Transactional(readOnly = true)
	public void delete(Long id) {
		reservaDao.deleteById(id);
	}
}
