package com.transtour.backend.models.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.dao.IMuelleDao;
import com.transtour.backend.models.dto.MuelleDTO;
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
	public MuelleDTO save(MuelleDTO muelleDto) {
		Muelle muelle = modelMapper.map(muelleDto, Muelle.class);
		muelle = muelleDao.save(muelle);
		return modelMapper.map(muelle, MuelleDTO.class);
	}

	@Override
	@Transactional(readOnly = true)
	public MuelleDTO findById(Long id) {
		return modelMapper.map(muelleDao.findById(id).orElse(null), MuelleDTO.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MuelleDTO> findAll() {
		List<MuelleDTO> dtoList = new ArrayList<>();
		Iterable<Muelle> muelles = muelleDao.findAll();
		for(Muelle muelle : muelles) {
			MuelleDTO muelleDto = modelMapper.map(muelle, MuelleDTO.class);
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
