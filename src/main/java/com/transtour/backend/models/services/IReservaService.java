package com.transtour.backend.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.transtour.backend.models.dto.ReservaDTO;

public interface IReservaService {

	public ReservaDTO save(ReservaDTO reservaDTO);
	
	public ReservaDTO findById(Long id);
	
	public ReservaDTO findByIdAndEmpresaId(Long id, Long empresaId);
	
	public ReservaDTO findByCodigoReserva(String codigoReserva);
	
	public List<ReservaDTO> findAllByEmpresaId(Long empresaId);
	
	public List<ReservaDTO> findAll();
	
	public Page<ReservaDTO> findAllPage(Pageable page);
	
	public Page<ReservaDTO> findAllByEmpresaIdPage(Long empresaId, Pageable page);
	
	public void delete(Long id);
}