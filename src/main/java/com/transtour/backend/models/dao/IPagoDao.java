package com.transtour.backend.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transtour.backend.models.entity.Pago;

public interface IPagoDao extends JpaRepository<Pago, Long>{

}
