package com.transtour.backend.models.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import com.transtour.backend.models.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long>{

	@Transactional(readOnly = true)
	Optional<Usuario> findByIdentificacion(String identificacion);
	
	@Transactional(readOnly = true)
	public Usuario findByUser(String user);
	
	/*@Transactional(readOnly = true)
	@Query("select u from User u where u.user=?1 and u.empresa?2")
	public Usuario findByUser2(String user, Empresa empresa);*/
}
