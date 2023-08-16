package com.transtour.backend.models.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.transtour.backend.models.entity.Pago;

public interface IPagoDao extends JpaRepository<Pago, Long>{

	@Transactional(readOnly = true)
	Optional<Pago> findByNumeroRecibo(String numeroRecibo);
}
