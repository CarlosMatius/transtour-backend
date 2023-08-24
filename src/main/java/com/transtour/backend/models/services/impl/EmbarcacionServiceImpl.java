package com.transtour.backend.models.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.dao.IEmbarcacionDao;
import com.transtour.backend.models.dto.EmbarcacionDTO;
import com.transtour.backend.models.entity.Embarcacion;
import com.transtour.backend.models.entity.Empresa;
import com.transtour.backend.models.services.IEmbarcacionService;

@Service
public class EmbarcacionServiceImpl implements IEmbarcacionService{
	
	@Autowired
	private IEmbarcacionDao embarcacionDao;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	@Transactional
	public EmbarcacionDTO save(EmbarcacionDTO embarcacionDTO) {
		Embarcacion embarcacion = modelMapper.map(embarcacionDTO, Embarcacion.class);
		embarcacion = embarcacionDao.save(embarcacion);
		return modelMapper.map(embarcacion, EmbarcacionDTO.class);
	}

	@Override
	@Transactional(readOnly = true)
	public EmbarcacionDTO findById(Long id) {
		return modelMapper.map(embarcacionDao.findById(id), EmbarcacionDTO.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public EmbarcacionDTO findByIdAndEmpresa(Long id, Empresa empresa) {
		return modelMapper.map(embarcacionDao.findByIdAndEmpresa(id, empresa), EmbarcacionDTO.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EmbarcacionDTO> findAll() {
		List<EmbarcacionDTO> dtoList = new ArrayList<>();
		Iterable<Embarcacion> embarcaciones = embarcacionDao.findAll();
		for(Embarcacion embarcacion : embarcaciones) {
			EmbarcacionDTO destinoDto = modelMapper.map(embarcacion, EmbarcacionDTO.class);
			dtoList.add(destinoDto);	
		}
		return dtoList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<EmbarcacionDTO> findAllPage(Pageable page) {
		Page<Embarcacion> paginaEmbarcaciones = embarcacionDao.findAll(page);
		return paginaEmbarcaciones.map(embarcacion -> modelMapper.map(embarcacion, EmbarcacionDTO.class));
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<EmbarcacionDTO> findAllByEmpresaPage(Empresa empresa, Pageable page) {
		Page<Embarcacion> paginaEmbarcaciones = embarcacionDao.findByEmpresa(empresa, page);
		return paginaEmbarcaciones.map(embarcacion -> modelMapper.map(embarcacion, EmbarcacionDTO.class));
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<EmbarcacionDTO> findAllByEmpresa(Empresa empresa) {
		List<EmbarcacionDTO> dtoList = new ArrayList<>();
		Iterable<Embarcacion> embarcaciones = embarcacionDao.findByEmpresa(empresa);
		
		for(Embarcacion embarcacion : embarcaciones) {
			EmbarcacionDTO embarcacionDTO = modelMapper.map(embarcacion, EmbarcacionDTO.class);
			dtoList.add(embarcacionDTO);	
		}
		return dtoList;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		embarcacionDao.deleteById(id);
	}
}