package com.transtour.backend.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.transtour.backend.models.dto.PagoDTO;

public interface IPagoService {

	public PagoDTO save(PagoDTO pagoDto);
	
	public PagoDTO findByNumeroRecibo(String numeroRecibo);
	
	public List<PagoDTO> findAll();
	
	public Page<PagoDTO> findAll(Pageable page);
}
