package com.transtour.backend.models.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.dao.IEmpresaDao;
import com.transtour.backend.models.dto.response.EmpresaResponse;
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
	public EmpresaResponse save(EmpresaResponse empresaDto) {
		Empresa empresa = modelMapper.map(empresaDto, Empresa.class);
		empresa = empresaDao.save(empresa);
		return modelMapper.map(empresa, EmpresaResponse.class);
	}

	@Override
	@Transactional(readOnly = true)
	public EmpresaResponse findById(Long id) {
		return modelMapper.map(empresaDao.findById(id), EmpresaResponse.class);
	}

	@Override
	@Transactional(readOnly = true)
	public EmpresaResponse findByNit(String nit) {
		return modelMapper.map(empresaDao.findByNit(nit), EmpresaResponse.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EmpresaResponse> findAll() {
		List<EmpresaResponse> dtoList = new ArrayList<>();
		Iterable<Empresa> empresas = empresaDao.findAll();
		for(Empresa empresa : empresas) {
			EmpresaResponse empresaDto = modelMapper.map(empresa, EmpresaResponse.class);
			dtoList.add(empresaDto);	
		}
		return dtoList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<EmpresaResponse> findAll(Pageable page) {
		Page<Empresa> paginaEmpresas = empresaDao.findAll(page);
		return paginaEmpresas.map(empresa -> modelMapper.map(empresa, EmpresaResponse.class));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		empresaDao.deleteById(id);
	}

}
