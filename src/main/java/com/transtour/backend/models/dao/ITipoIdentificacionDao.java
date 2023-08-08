package com.transtour.backend.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transtour.backend.models.entity.TipoIdentificacion;

public interface ITipoIdentificacionDao extends JpaRepository<TipoIdentificacion, Long>{

}
