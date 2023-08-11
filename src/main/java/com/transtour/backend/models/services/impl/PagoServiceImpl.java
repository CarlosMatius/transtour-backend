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
import com.transtour.backend.models.dto.PagoDTO;
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
	public PagoDTO save(PagoDTO pagoDto) {
		Pago pago = modelMapper.map(pagoDto, Pago.class);
		pago = pagooDao.save(pago);
		return modelMapper.map(pago, PagoDTO.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public PagoDTO findByNombreComercio(String nombreComercio) {
		return modelMapper.map(pagooDao.findByNombreComercio(nombreComercio).orElse(null), PagoDTO.class);
	}

	@Override
	public List<PagoDTO> findAll() {
		List<PagoDTO> dtoList = new ArrayList<>();
		Iterable<Pago> pagos = pagooDao.findAll();
		for(Pago pago : pagos) {
			PagoDTO pagoDto = modelMapper.map(pago, PagoDTO.class);
			dtoList.add(pagoDto);	
		}
		return dtoList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<PagoDTO> findAll(Pageable page) {
		Page<Pago> paginaPagos = pagooDao.findAll(page);
		return paginaPagos.map(pago -> modelMapper.map(pago, PagoDTO.class));
	}
}
