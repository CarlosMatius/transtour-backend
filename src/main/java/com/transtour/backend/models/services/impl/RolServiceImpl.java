package com.transtour.backend.models.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.dao.IRolDao;
import com.transtour.backend.models.dto.RolDTO;
import com.transtour.backend.models.entity.Rol;
import com.transtour.backend.models.services.IRolService;

@Service
public class RolServiceImpl implements IRolService {

	@Autowired
	private IRolDao rolDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	@Transactional
	public RolDTO save(RolDTO rolDTO) {
		Rol rol = modelMapper.map(rolDTO, Rol.class);
		rol = rolDao.save(rol);
		return modelMapper.map(rol, RolDTO.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public RolDTO findById(Long id) {
		return modelMapper.map(rolDao.findById(id), RolDTO.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<RolDTO> findAll() {
		List<RolDTO> dtoList = new ArrayList<>();
		Iterable<Rol> roles = rolDao.findAll();
		for(Rol rol : roles) {
			RolDTO rolDto = modelMapper.map(rol, RolDTO.class);
			dtoList.add(rolDto);	
		}
		return dtoList;
	}
}