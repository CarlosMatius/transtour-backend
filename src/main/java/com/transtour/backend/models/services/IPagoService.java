package com.transtour.backend.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.transtour.backend.models.dto.PagoDTO;

public interface IPagoService {

	public PagoDTO save(PagoDTO pagoDTO);
	
	public PagoDTO findByNumeroRecibo(String numeroRecibo);
	
	public PagoDTO findByNumeroReciboAndEmpresaId(String numeroRecibo, Long id);
	
	public List<PagoDTO> findAllByEmpresaId(Long empresaId);
	
	public List<PagoDTO> findAll();
	
	public Page<PagoDTO> findAllPage(Pageable page);
	
	public Page<PagoDTO> findAllByEmpresaIdPage(Long empresaId, Pageable page);
}
