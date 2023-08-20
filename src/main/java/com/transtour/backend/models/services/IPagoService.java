package com.transtour.backend.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.transtour.backend.models.dto.response.PagoResponse;

public interface IPagoService {

	public PagoResponse save(PagoResponse pagoDto);
	
	public PagoResponse findByNumeroRecibo(String numeroRecibo);
	
	public List<PagoResponse> findAll();
	
	public Page<PagoResponse> findAll(Pageable page);
}
