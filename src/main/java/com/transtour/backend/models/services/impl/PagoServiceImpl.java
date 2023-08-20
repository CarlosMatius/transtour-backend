package com.transtour.backend.models.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.dao.IPagoDao;
import com.transtour.backend.models.dto.response.PagoResponse;
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
	public PagoResponse save(PagoResponse pagoDto) {
		Pago pago = modelMapper.map(pagoDto, Pago.class);
		pago = pagooDao.save(pago);
		return modelMapper.map(pago, PagoResponse.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public PagoResponse findByNumeroRecibo(String numeroRecibo) {
		return modelMapper.map(pagooDao.findByNumeroRecibo(numeroRecibo), PagoResponse.class);
	}

	@Override
	public List<PagoResponse> findAll() {
		List<PagoResponse> dtoList = new ArrayList<>();
		Iterable<Pago> pagos = pagooDao.findAll();
		for(Pago pago : pagos) {
			PagoResponse pagoDto = modelMapper.map(pago, PagoResponse.class);
			dtoList.add(pagoDto);	
		}
		return dtoList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<PagoResponse> findAll(Pageable page) {
		Page<Pago> paginaPagos = pagooDao.findAll(page);
		return paginaPagos.map(pago -> modelMapper.map(pago, PagoResponse.class));
	}
}
