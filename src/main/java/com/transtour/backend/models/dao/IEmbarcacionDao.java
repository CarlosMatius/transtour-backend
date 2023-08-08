package com.transtour.backend.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transtour.backend.models.entity.Embarcacion;

public interface IEmbarcacionDao extends JpaRepository<Embarcacion, Long>{

}
