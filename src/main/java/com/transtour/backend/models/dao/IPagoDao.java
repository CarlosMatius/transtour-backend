package com.transtour.backend.models.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.entity.Pago;

public interface IPagoDao extends JpaRepository<Pago, Long>{

	@Transactional(readOnly = true)
	Optional<Pago> findByNumeroRecibo(String numeroRecibo);
	
	@Transactional(readOnly = true)
	@Query("SELECT p FROM Pago p " +
			   "INNER JOIN p.reserva r " +
			   "INNER JOIN r.itinerario i " +
			   "INNER JOIN i.embarcacion e " +
			   "INNER JOIN e.empresa emp " +
	           "WHERE p.numeroRecibo = ?1 AND em.id = ?2")
	Optional<Pago> findByNumeroReciboAndEmpresaId(String numeroRecibo, Long empresaId);
	
	@Transactional(readOnly = true)
	@Query("SELECT p FROM Pago p " +
			   "INNER JOIN p.reserva r " +
			   "INNER JOIN r.itinerario i " +
			   "INNER JOIN i.embarcacion e " +
			   "INNER JOIN e.empresa emp " +
	           "WHERE em.id = ?1")
	List<Pago> findPagosByEmpresaId (Long empresaId);
	
	@Transactional(readOnly = true)
	@Query("SELECT p FROM Pago p " +
			   "INNER JOIN p.reserva r " +
			   "INNER JOIN r.itinerario i " +
			   "INNER JOIN i.embarcacion e " +
			   "INNER JOIN e.empresa emp " +
	           "WHERE em.id = ?1")
	public Page<Pago> findPagosByEmpresaId(Long empresaId, Pageable pageable);
}