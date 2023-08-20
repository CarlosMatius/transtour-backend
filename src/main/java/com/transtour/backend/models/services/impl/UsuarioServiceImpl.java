package com.transtour.backend.models.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.dao.IUsuarioDao;
import com.transtour.backend.models.dto.UsuarioDTO;
import com.transtour.backend.models.dto.response.UsuarioResponse;
import com.transtour.backend.models.entity.Usuario;
import com.transtour.backend.models.services.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService, UserDetailsService{
	
	private Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);
	
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
		return modelMapper.map(usuarioDao.findById(id), UsuarioDTO.class);
	}

	@Override
	@Transactional(readOnly = true)
	public UsuarioResponse findByIdentificacion(String identificacion) {
		return modelMapper.map(usuarioDao.findByIdentificacion(identificacion), UsuarioResponse.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public UsuarioResponse findByUsername(String user) {
		return modelMapper.map(usuarioDao.findByUsername(user), UsuarioResponse.class);
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

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UsuarioDTO usuarioDTO = modelMapper.map(usuarioDao.findByUsername(username), UsuarioDTO.class);
		
		if(usuarioDTO == null) {
			log.error("Error en el login: no existe el usuario {} en el sistema", username);
			throw new UsernameNotFoundException("Error en el login: no existe el usuario '"+username+"' el sistema");
		}
		
		List<GrantedAuthority> authorities = usuarioDTO.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.collect(Collectors.toList());
				
		return new User(usuarioDTO.getUsername(), usuarioDTO.getPassword(), usuarioDTO.isEnabled(), true, true, true, authorities);
	}
}
