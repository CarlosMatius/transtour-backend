package com.transtour.backend.models.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.dao.IEmpresaDao;
import com.transtour.backend.models.dto.EmpresaDto;
import com.transtour.backend.models.entity.Empresa;
import com.transtour.backend.models.services.IEmpresaService;

@Service
public class EmpresaServiceImpl implements IEmpresaService{

	@Autowired
	private IEmpresaDao empresaDao;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	@Transactional
	public EmpresaDto save(EmpresaDto empresaDto) {
		Empresa empresa = modelMapper.map(empresaDto, Empresa.class);
		empresa = empresaDao.save(empresa);
		return modelMapper.map(empresa, EmpresaDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public EmpresaDto findById(Long id) {
		return modelMapper.map(empresaDao.findById(id).orElse(null), EmpresaDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public EmpresaDto findByNit(String nit) {
		return modelMapper.map(empresaDao.findByNit(nit).orElse(null), EmpresaDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EmpresaDto> findAll() {
		List<EmpresaDto> dtoList = new ArrayList<>();
		Iterable<Empresa> empresas = empresaDao.findAll();
		for(Empresa empresa : empresas) {
			EmpresaDto empresaDto = modelMapper.map(empresa, EmpresaDto.class);
			dtoList.add(empresaDto);	
		}
		return dtoList;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		empresaDao.deleteById(id);
	}

}
