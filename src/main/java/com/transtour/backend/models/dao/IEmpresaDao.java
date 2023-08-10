package com.transtour.backend.models.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.entity.Empresa;

public interface IEmpresaDao extends JpaRepository<Empresa, Long>{

	@Transactional(readOnly = true)
	Optional<Empresa> findByNit(String nit);
}
