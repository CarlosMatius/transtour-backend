package com.transtour.backend.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.transtour.backend.models.dto.UsuarioDTO;
import com.transtour.backend.models.entity.Empresa;

public interface IUsuarioService {
	
	public UsuarioDTO save(UsuarioDTO usuarioDTO);
	
	public UsuarioDTO findById(Long id);
	
	public UsuarioDTO findByIdAndEmpresa(Long id, Empresa empresa);
	
	public UsuarioDTO findByUsername(String user);
	
	public UsuarioDTO findByIdentificacion(String identificacion);
	
	public UsuarioDTO findByIdentificacionAndEmpresa(String identificacion, Empresa empresa);
	
	public List<UsuarioDTO> findAll();
	
	public List<UsuarioDTO> findAllByEmpresa(Empresa empresa);
	
	public Page<UsuarioDTO> findAllPage(Pageable page);
	
	public Page<UsuarioDTO> findAllByEmpresaPage(Empresa empresa, Pageable page);
	
	public void delete(Long id);
	
	public void deleteByIdAndEmpresa(Long id, Empresa empresa);
}