package com.transtour.backend.models.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.dao.IReservaDao;
import com.transtour.backend.models.dto.ReservaDto;
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
	public ReservaDto save(ReservaDto reservaDto) {
		Reserva reserva = modelMapper.map(reservaDto, Reserva.class);
		reserva = reservaDao.save(reserva);
		return modelMapper.map(reserva, ReservaDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public ReservaDto findById(Long id) {
		return modelMapper.map(reservaDao.findById(id).orElse(null), ReservaDto.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public ReservaDto findByCodigoReserva(String codigoReserva) {
		return modelMapper.map(reservaDao.findByCodigoReserva(codigoReserva).orElse(null), ReservaDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ReservaDto> findAll() {
		List<ReservaDto> dtoList = new ArrayList<>();
		Iterable<Reserva> reservas = reservaDao.findAll();
		for(Reserva reserva : reservas) {
			ReservaDto reservaDto = modelMapper.map(reserva, ReservaDto.class);
			dtoList.add(reservaDto);	
		}
		return dtoList;
	}

	@Override
	@Transactional(readOnly = true)
	public void delete(Long id) {
		reservaDao.deleteById(id);
	}

}
