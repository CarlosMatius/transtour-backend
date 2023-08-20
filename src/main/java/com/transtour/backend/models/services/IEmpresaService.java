package com.transtour.backend.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.transtour.backend.models.dto.response.EmpresaResponse;

public interface IEmpresaService {

	public EmpresaResponse save(EmpresaResponse empresaDto);
	
	public EmpresaResponse findById(Long id);
	
	public EmpresaResponse findByNit(String nit);
	
	public List<EmpresaResponse> findAll();
	
	public Page<EmpresaResponse> findAll(Pageable page);
	
	public void delete(Long id);
}
