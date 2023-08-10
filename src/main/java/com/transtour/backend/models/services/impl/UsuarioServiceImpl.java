package com.transtour.backend.models.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.dao.IUsuarioDao;
import com.transtour.backend.models.dto.UsuarioDto;
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
	public UsuarioDto save(UsuarioDto usuarioDto) {
		Usuario usuario = modelMapper.map(usuarioDto, Usuario.class);
		usuario = usuarioDao.save(usuario);
		return modelMapper.map(usuario, UsuarioDto.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public UsuarioDto findById(Long id) {
		return modelMapper.map(usuarioDao.findById(id).orElse(null), UsuarioDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public UsuarioDto findByIdentificacion(String identificacion) {
		return modelMapper.map(usuarioDao.findByIdentificacion(identificacion).orElse(null), UsuarioDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UsuarioDto> findAll() {
		List<UsuarioDto> dtoList = new ArrayList<>();
		Iterable<Usuario> usuarios = usuarioDao.findAll();
		for(Usuario usuario : usuarios) {
			UsuarioDto usuarioDto = modelMapper.map(usuario, UsuarioDto.class);
			dtoList.add(usuarioDto);	
		}
		return dtoList;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		usuarioDao.deleteById(id);
	}
	
	

}
