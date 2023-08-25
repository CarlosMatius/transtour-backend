package com.transtour.backend.models.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.entity.Itinerario;
import com.transtour.backend.models.entity.Pasajero;

public interface IPasajeroDao extends JpaRepository<Pasajero, Long>{

	@Transactional(readOnly = true)
	@Query("SELECT p FROM Pasajero p " +
	           "INNER JOIN Reserva r " +
	           "ON p.reserva = r.id  " +
	           "WHERE p.id = ?1 AND r.id = ?2")
	public Optional<Itinerario> findPasajeroByIdAndReservaId(Long id, Long reservaId);
}