package com.transtour.backend.models.services;

import java.util.List;

import com.transtour.backend.models.dto.UsuarioDto;

public interface IUsuarioService {
	
	public UsuarioDto save(UsuarioDto usuarioDto);
	
	public UsuarioDto findById(Long id);
	
	public UsuarioDto findByIdentificacion(String identificacion);
	
	public List<UsuarioDto> findAll();
	
	public void delete(Long id);
}