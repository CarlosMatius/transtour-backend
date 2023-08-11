package com.transtour.backend.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.transtour.backend.models.dto.EmpresaDTO;

public interface IEmpresaService {

	public EmpresaDTO save(EmpresaDTO empresaDto);
	
	public EmpresaDTO findById(Long id);
	
	public EmpresaDTO findByNit(String nit);
	
	public List<EmpresaDTO> findAll();
	
	public Page<EmpresaDTO> findAll(Pageable page);
	
	public void delete(Long id);
}
