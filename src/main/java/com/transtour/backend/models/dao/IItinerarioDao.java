package com.transtour.backend.models.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.entity.Itinerario;

public interface IItinerarioDao extends JpaRepository<Itinerario, Long>{
	
	@Transactional(readOnly = true)
	@Query("select i from Itinerario i "
			+ "join i.destino d "
			+ "join i.embarcacion e "
			+ "join i.muelle m "
			+ "where i.fechaEmbarque = :fechaEmbarque and i.destino.nombre = :nombreDestino")
	List<Itinerario> findByFechaAndDestino(LocalDate fechaEmbarque, String nombreDestino);
}
