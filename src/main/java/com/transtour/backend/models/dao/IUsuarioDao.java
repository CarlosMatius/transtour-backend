package com.transtour.backend.models.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.entity.Empresa;
import com.transtour.backend.models.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long>{

	@Transactional(readOnly = true)
	public Optional<Usuario> findByIdentificacion(String identificacion);
	
	@Transactional(readOnly = true)
	public Optional<Usuario> findByIdentificacionAndEmpresa(String identificacion, Empresa empresa);
	
	@Transactional(readOnly = true)
	public Optional<Usuario> findByUsername(String user);
	
	@Transactional(readOnly = true)
	public List<Usuario> findByEmpresa(Empresa empresa);
	
	@Transactional(readOnly = true)
	public Optional<Usuario> findByIdAndEmpresa(Long id, Empresa empresa);
	
	@Transactional(readOnly = true)
	public Page<Usuario> findByEmpresa(Empresa empresa, Pageable pageable);

}