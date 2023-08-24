package com.transtour.backend.models.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.entity.Embarcacion;
import com.transtour.backend.models.entity.Empresa;

public interface IEmbarcacionDao extends JpaRepository<Embarcacion, Long>{

	@Transactional(readOnly = true)
	public List<Embarcacion> findByEmpresa(Empresa empresa);
	
	@Transactional(readOnly = true)
	public Page<Embarcacion> findByEmpresa(Empresa empresa, Pageable pageable);
	
	@Transactional(readOnly = true)
	public Optional<Embarcacion> findByIdAndEmpresa(Long id, Empresa empresa);
}
