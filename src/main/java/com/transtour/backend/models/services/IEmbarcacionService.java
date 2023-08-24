package com.transtour.backend.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.transtour.backend.models.dto.EmbarcacionDTO;
import com.transtour.backend.models.entity.Empresa;

public interface IEmbarcacionService {

	public EmbarcacionDTO save(EmbarcacionDTO embarcacionDTO);
	
	public EmbarcacionDTO findById(Long id);
	
	public EmbarcacionDTO findByIdAndEmpresa(Long id, Empresa empresa);
	
	public List<EmbarcacionDTO> findAll();
	
	public List<EmbarcacionDTO> findAllByEmpresa(Empresa empresa);
	
	public Page<EmbarcacionDTO> findAllPage(Pageable page);
	
	public Page<EmbarcacionDTO> findAllByEmpresaPage(Empresa empresa, Pageable page);
	
	public void delete(Long id);
}