package com.transtour.backend.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transtour.backend.models.entity.Itinerario;

public interface IItinerarioDao extends JpaRepository<Itinerario, Long>{

}
