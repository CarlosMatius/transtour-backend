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
import com.transtour.backend.models.dto.response.ReservaResponse;
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
	public ReservaResponse save(ReservaResponse reservaDto) {
		Reserva reserva = modelMapper.map(reservaDto, Reserva.class);
		reserva = reservaDao.save(reserva);
		return modelMapper.map(reserva, ReservaResponse.class);
	}

	@Override
	@Transactional(readOnly = true)
	public ReservaResponse findById(Long id) {
		return modelMapper.map(reservaDao.findById(id), ReservaResponse.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public ReservaResponse findByCodigoReserva(String codigoReserva) {
		return modelMapper.map(reservaDao.findByCodigoReserva(codigoReserva), ReservaResponse.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ReservaResponse> findAll() {
		List<ReservaResponse> dtoList = new ArrayList<>();
		Iterable<Reserva> reservas = reservaDao.findAll();
		for(Reserva reserva : reservas) {
			ReservaResponse reservaDto = modelMapper.map(reserva, ReservaResponse.class);
			dtoList.add(reservaDto);	
		}
		return dtoList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<ReservaResponse> findAll(Pageable page) {
		Page<Reserva> paginaReservas = reservaDao.findAll(page);
		return paginaReservas.map(reserva -> modelMapper.map(reserva, ReservaResponse.class));
	}

	@Override
	@Transactional(readOnly = true)
	public void delete(Long id) {
		reservaDao.deleteById(id);
	}

}
