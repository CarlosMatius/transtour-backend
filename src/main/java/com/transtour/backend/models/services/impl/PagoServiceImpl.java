package com.transtour.backend.models.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.dao.IPagoDao;
import com.transtour.backend.models.dto.PagoDto;
import com.transtour.backend.models.entity.Pago;
import com.transtour.backend.models.services.IPagoService;

@Service
public class PagoServiceImpl implements IPagoService{

	@Autowired
	private IPagoDao pagooDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public PagoDto save(PagoDto pagoDto) {
		Pago pago = modelMapper.map(pagoDto, Pago.class);
		pago = pagooDao.save(pago);
		return modelMapper.map(pago, PagoDto.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public PagoDto findByNombreComercio(String nombreComercio) {
		return modelMapper.map(pagooDao.findByNombreComercio(nombreComercio).orElse(null), PagoDto.class);
	}

	@Override
	public List<PagoDto> findAll() {
		List<PagoDto> dtoList = new ArrayList<>();
		Iterable<Pago> pagos = pagooDao.findAll();
		for(Pago pago : pagos) {
			PagoDto pagoDto = modelMapper.map(pago, PagoDto.class);
			dtoList.add(pagoDto);	
		}
		return dtoList;
	}
}
