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
import com.transtour.backend.models.dto.EmpresaDTO;
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
	public EmpresaDTO save(EmpresaDTO empresaDto) {
		Empresa empresa = modelMapper.map(empresaDto, Empresa.class);
		empresa = empresaDao.save(empresa);
		return modelMapper.map(empresa, EmpresaDTO.class);
	}

	@Override
	@Transactional(readOnly = true)
	public EmpresaDTO findById(Long id) {
		return modelMapper.map(empresaDao.findById(id).orElse(null), EmpresaDTO.class);
	}

	@Override
	@Transactional(readOnly = true)
	public EmpresaDTO findByNit(String nit) {
		return modelMapper.map(empresaDao.findByNit(nit).orElse(null), EmpresaDTO.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EmpresaDTO> findAll() {
		List<EmpresaDTO> dtoList = new ArrayList<>();
		Iterable<Empresa> empresas = empresaDao.findAll();
		for(Empresa empresa : empresas) {
			EmpresaDTO empresaDto = modelMapper.map(empresa, EmpresaDTO.class);
			dtoList.add(empresaDto);	
		}
		return dtoList;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<EmpresaDTO> findAll(Pageable page) {
		Page<Empresa> paginaEmpresas = empresaDao.findAll(page);
		return paginaEmpresas.map(empresa -> modelMapper.map(empresa, EmpresaDTO.class));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		empresaDao.deleteById(id);
	}

}
