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
import com.transtour.backend.models.dto.UsuarioResponse;
import com.transtour.backend.models.entity.Empresa;
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
	public UsuarioDTO save(UsuarioDTO usuarioDTO) {
		Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
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
	public UsuarioDTO findByIdAndEmpresa(Long id, Empresa empresa) {
		return modelMapper.map(usuarioDao.findByIdAndEmpresa(id, empresa), UsuarioDTO.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public UsuarioDTO findByUsername(String user) {
		return modelMapper.map(usuarioDao.findByUsername(user), UsuarioDTO.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public UsuarioResponse findByIdentificacion(String identificacion) {
		return modelMapper.map(usuarioDao.findByIdentificacion(identificacion), UsuarioResponse.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public UsuarioResponse findByIdentificacionAndEmpresa(String identificacion, Empresa empresa) {
		return modelMapper.map(usuarioDao.findByIdentificacionAndEmpresa(identificacion, empresa), UsuarioResponse.class);
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
	public List<UsuarioResponse> findAllByEmpresa(Empresa empresa) {
		List<UsuarioResponse> dtoList = new ArrayList<>();
		Iterable<Usuario> usuarios = usuarioDao.findByEmpresa(empresa);
		
		for(Usuario usuario : usuarios) {
			UsuarioResponse usuarioResponse = modelMapper.map(usuario, UsuarioResponse.class);
			dtoList.add(usuarioResponse);	
		}
		return dtoList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<UsuarioResponse> findAllPage(Pageable page) {
		Page<Usuario> paginaUsuarioss = usuarioDao.findAll(page);
		return paginaUsuarioss.map(usuario -> modelMapper.map(usuario, UsuarioResponse.class));
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<UsuarioResponse> findAllByEmpresaPage(Empresa empresa, Pageable page) {
		Page<Usuario> paginaUsuarios = usuarioDao.findByEmpresa(empresa, page);
		return paginaUsuarios.map(usuario -> modelMapper.map(usuario, UsuarioResponse.class));
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
		else if(!usuarioDTO.isEnabled()) {
			log.error("Error en el login: el usuario {} no esta habilitado para ingresar al sistema", username);
			throw new UsernameNotFoundException("Error en el login: el usuario '"+username+"' no esta habilitado para ingresar al sistema");
		}
		
		List<GrantedAuthority> authorities = usuarioDTO.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.collect(Collectors.toList());

		return new User(usuarioDTO.getUsername(), usuarioDTO.getPassword(), usuarioDTO.isEnabled(), true, true, true, authorities);
	}
}
