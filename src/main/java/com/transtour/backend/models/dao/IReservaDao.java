package com.transtour.backend.models.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.entity.Reserva;

public interface IReservaDao extends JpaRepository<Reserva, Long>{

	@Transactional(readOnly = true)
	Optional<Reserva> findByCodigoReserva(String codigoReserva);
}
