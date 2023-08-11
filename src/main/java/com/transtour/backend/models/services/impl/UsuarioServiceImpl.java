package com.transtour.backend.models.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.dao.IUsuarioDao;
import com.transtour.backend.models.dto.UsuarioDTO;
import com.transtour.backend.models.dto.UsuarioResponse;
import com.transtour.backend.models.entity.Usuario;
import com.transtour.backend.models.services.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
	
	@Autowired
	private IUsuarioDao usuarioDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public UsuarioDTO save(UsuarioDTO usuarioDto) {
		Usuario usuario = modelMapper.map(usuarioDto, Usuario.class);
		usuario = usuarioDao.save(usuario);
		return modelMapper.map(usuario, UsuarioDTO.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public UsuarioDTO findById(Long id) {
		return modelMapper.map(usuarioDao.findById(id).orElse(null), UsuarioDTO.class);
	}

	@Override
	@Transactional(readOnly = true)
	public UsuarioResponse findByIdentificacion(String identificacion) {
		return modelMapper.map(usuarioDao.findByIdentificacion(identificacion).orElse(null), UsuarioResponse.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UsuarioResponse> findAll() {
		List<UsuarioResponse> dtoList = new ArrayList<>();
		Iterable<Usuario> usuarios = usuarioDao.findAll();
		for(Usuario usuario : usuarios) {
			UsuarioResponse usuarioResponse = modelMapper.map(usuario, UsuarioResponse.class);
			dtoList.add(usuarioResponse);	
		}
		return dtoList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<UsuarioResponse> findAll(Pageable page) {
		Page<Usuario> paginaUsuarioss = usuarioDao.findAll(page);
		return paginaUsuarioss.map(usuario -> modelMapper.map(usuario, UsuarioResponse.class));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		usuarioDao.deleteById(id);
	}
}
