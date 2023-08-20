package com.transtour.backend.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.transtour.backend.models.dto.UsuarioDTO;
import com.transtour.backend.models.dto.response.UsuarioResponse;

public interface IUsuarioService {
	
	public UsuarioDTO save(UsuarioDTO usuarioDto);
	
	public UsuarioDTO findById(Long id);
	
	public UsuarioResponse findByIdentificacion(String identificacion);
	
	public UsuarioResponse findByUsername(String user);
	
	public List<UsuarioResponse> findAll();
	
	public Page<UsuarioResponse> findAll(Pageable page);
	
	public void delete(Long id);
}