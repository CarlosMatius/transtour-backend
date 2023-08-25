package com.transtour.backend.models.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.entity.Reserva;

public interface IReservaDao extends JpaRepository<Reserva, Long>{

	@Transactional(readOnly = true)
	Optional<Reserva> findByCodigoReserva(String codigoReserva);
	
	@Transactional(readOnly = true)
	@Query("SELECT r FROM Reserva r " +
			   "INNER JOIN r.itinerario i " +
	           "INNER JOIN i.embarcacion e " +
	           "INNER JOIN e.empresa em " +
	           "WHERE r.id = ?1 AND em.id = ?2")
	public Optional<Reserva> findReservaByIdAndEmpresaId(Long id, Long empresaId);
	
	@Transactional(readOnly = true)
	@Query("SELECT r FROM Reserva r " +
			   "INNER JOIN r.itinerario i " +
	           "INNER JOIN i.embarcacion e " +
	           "INNER JOIN e.empresa em " +
	           "WHERE em.id = ?1")
	List<Reserva> findReservasByEmpresaId (Long empresaId);
	
	@Transactional(readOnly = true)
	@Query("SELECT r FROM Reserva r " +
			   "INNER JOIN r.itinerario i " +
	           "INNER JOIN i.embarcacion e " +
	           "INNER JOIN e.empresa em " +
	           "WHERE em.id = ?1")
	public Page<Reserva> findReservasByEmpresaId(Long empresaId, Pageable pageable);
}