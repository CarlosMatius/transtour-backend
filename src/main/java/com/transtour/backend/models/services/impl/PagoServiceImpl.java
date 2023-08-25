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
	private IPagoDao pagoDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public PagoDTO save(PagoDTO pagoDTO) {
		Pago pago = modelMapper.map(pagoDTO, Pago.class);
		pago = pagoDao.save(pago);
		return modelMapper.map(pago, PagoDTO.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public PagoDTO findByNumeroRecibo(String numeroRecibo) {
		return modelMapper.map(pagoDao.findByNumeroRecibo(numeroRecibo), PagoDTO.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public PagoDTO findByNumeroReciboAndEmpresaId(String numeroRecibo, Long empresaId) {
		return modelMapper.map(pagoDao.findByNumeroReciboAndEmpresaId(numeroRecibo, empresaId), PagoDTO.class);
	}

	@Override
	public List<PagoDTO> findAll() {
		List<PagoDTO> dtoList = new ArrayList<>();
		Iterable<Pago> pagos = pagoDao.findAll();
		for(Pago pago : pagos) {
			PagoDTO pagoDto = modelMapper.map(pago, PagoDTO.class);
			dtoList.add(pagoDto);	
		}
		return dtoList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<PagoDTO> findAllPage(Pageable page) {
		Page<Pago> paginaPagos = pagoDao.findAll(page);
		return paginaPagos.map(pago -> modelMapper.map(pago, PagoDTO.class));
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<PagoDTO> findAllByEmpresaId(Long empresaId) {
		List<PagoDTO> dtoList = new ArrayList<>();
		Iterable<Pago> pagos = pagoDao.findPagosByEmpresaId(empresaId);
		
		for(Pago pago : pagos) {
			PagoDTO pagoDTO = modelMapper.map(pago, PagoDTO.class);
			dtoList.add(pagoDTO);	
		}
		return dtoList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<PagoDTO> findAllByEmpresaIdPage(Long empresaId, Pageable page) {
		Page<Pago> paginaPago = pagoDao.findPagosByEmpresaId(empresaId, page);
		return paginaPago.map(pago -> modelMapper.map(pago, PagoDTO.class));
	}
}
