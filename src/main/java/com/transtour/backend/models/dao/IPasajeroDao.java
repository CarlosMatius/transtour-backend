package com.transtour.backend.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transtour.backend.models.entity.Pasajero;

public interface IPasajeroDao extends JpaRepository<Pasajero, Long>{

}
