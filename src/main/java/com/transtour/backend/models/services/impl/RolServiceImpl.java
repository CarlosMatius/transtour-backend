package com.transtour.backend.models.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.dao.IRolDao;
import com.transtour.backend.models.dto.RolDto;
import com.transtour.backend.models.entity.Rol;
import com.transtour.backend.models.services.IRolService;

@Service
public class RolServiceImpl implements IRolService{

	@Autowired
	private IRolDao rolDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	@Transactional
	public RolDto save(RolDto rolDto) {
		Rol rol = modelMapper.map(rolDto, Rol.class);
		rol = rolDao.save(rol);
		return modelMapper.map(rol, RolDto.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<RolDto> findAll() {
		List<RolDto> dtoList = new ArrayList<>();
		Iterable<Rol> roles = rolDao.findAll();
		for(Rol rol : roles) {
			RolDto rolDto = modelMapper.map(rol, RolDto.class);
			dtoList.add(rolDto);	
		}
		return dtoList;
	}

}