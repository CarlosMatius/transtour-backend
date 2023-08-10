package com.transtour.backend.models.services;

import java.util.List;

import com.transtour.backend.models.dto.EmpresaDto;

public interface IEmpresaService {

	public EmpresaDto save(EmpresaDto empresaDto);
	
	public EmpresaDto findById(Long id);
	
	public EmpresaDto findByNit(String nit);
	
	public List<EmpresaDto> findAll();
	
	public void delete(Long id);
}
