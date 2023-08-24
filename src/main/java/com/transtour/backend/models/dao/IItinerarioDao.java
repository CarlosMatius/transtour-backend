package com.transtour.backend.models.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.entity.Itinerario;

public interface IItinerarioDao extends JpaRepository<Itinerario, Long>{
	
	@Transactional(readOnly = true)
	@Query("SELECT i FROM Itinerario i " +
	           "INNER JOIN i.embarcacion e " +
	           "INNER JOIN e.empresa em " +
	           "INNER JOIN i.destino d " +
	           "INNER JOIN i.muelle m " +
	           "WHERE i.id = ?1 AND em.id = ?2")
	public Optional<Itinerario> findItinerarioByIdAndEmpresaId(Long id, Long empresaId);
	
	@Transactional(readOnly = true)
	@Query("SELECT i FROM Itinerario i " +
	           "INNER JOIN i.destino d " +
	           "INNER JOIN i.muelle m " +
	           "INNER JOIN i.embarcacion e " +
	           "WHERE i.fechaEmbarque = :fechaEmbarque " +
	           "AND d.nombre = :nombreDestino")
	List<Itinerario> findItinerariosByFechaEmbarqueAndDestino(LocalDate fechaEmbarque, String nombreDestino);
	
	@Transactional(readOnly = true)
	@Query("SELECT i FROM Itinerario i " +
	           "INNER JOIN i.embarcacion e " +
	           "INNER JOIN e.empresa em " +
	           "INNER JOIN i.destino d " +
	           "INNER JOIN i.muelle m " +
	           "WHERE em.id = ?1")
	List<Itinerario> findItinerariosByEmpresaId (Long empresaId);
	
	@Transactional(readOnly = true)
	@Query("SELECT i FROM Itinerario i " +
	           "INNER JOIN i.embarcacion e " +
	           "INNER JOIN e.empresa em " +
	           "INNER JOIN i.destino d " +
	           "INNER JOIN i.muelle m " +
	           "WHERE em.id = ?1")
	public Page<Itinerario> findItinerariosByEmpresaId(Long empresaId, Pageable pageable);
}