package com.transtour.backend.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transtour.backend.models.entity.Reserva;

public interface IReservaDao extends JpaRepository<Reserva, Long>{

}
