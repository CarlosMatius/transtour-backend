package com.transtour.backend.models.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import com.transtour.backend.models.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long>{

	@Transactional(readOnly = true)
	Optional<Usuario> findByIdentificacion(String identificacion);
	
	@Transactional(readOnly = true)
	public Usuario findByUsername(String user);
}
