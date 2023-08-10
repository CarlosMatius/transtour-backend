package com.transtour.backend.models.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.dao.IMuelleDao;
import com.transtour.backend.models.dto.MuelleDto;
import com.transtour.backend.models.entity.Muelle;
import com.transtour.backend.models.services.IMuelleService;

@Service
public class MuelleServiceImpl implements IMuelleService{
	
	@Autowired
	private IMuelleDao muelleDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public MuelleDto save(MuelleDto muelleDto) {
		Muelle muelle = modelMapper.map(muelleDto, Muelle.class);
		muelle = muelleDao.save(muelle);
		return modelMapper.map(muelle, MuelleDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public MuelleDto findById(Long id) {
		return modelMapper.map(muelleDao.findById(id).orElse(null), MuelleDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MuelleDto> findAll() {
		List<MuelleDto> dtoList = new ArrayList<>();
		Iterable<Muelle> muelles = muelleDao.findAll();
		for(Muelle muelle : muelles) {
			MuelleDto muelleDto = modelMapper.map(muelle, MuelleDto.class);
			dtoList.add(muelleDto);	
		}
		return dtoList;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		muelleDao.deleteById(id);
	}

}
