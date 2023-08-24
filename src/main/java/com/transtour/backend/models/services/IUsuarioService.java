package com.transtour.backend.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.transtour.backend.models.dto.UsuarioDTO;
import com.transtour.backend.models.dto.UsuarioResponse;
import com.transtour.backend.models.entity.Empresa;

public interface IUsuarioService {
	
	public UsuarioDTO save(UsuarioDTO usuarioDTO);
	
	public UsuarioDTO findById(Long id);
	
	public UsuarioDTO findByIdAndEmpresa(Long id, Empresa empresa);
	
	public UsuarioDTO findByUsername(String user);
	
	public UsuarioResponse findByIdentificacion(String identificacion);
	
	public UsuarioResponse findByIdentificacionAndEmpresa(String identificacion, Empresa empresa);
	
	public List<UsuarioResponse> findAll();
	
	public List<UsuarioResponse> findAllByEmpresa(Empresa empresa);
	
	public Page<UsuarioResponse> findAllPage(Pageable page);
	
	public Page<UsuarioResponse> findAllByEmpresaPage(Empresa empresa, Pageable page);
	
	public void delete(Long id);
}