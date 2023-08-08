package com.transtour.backend.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transtour.backend.models.entity.Empresa;

public interface IEmpresaDao extends JpaRepository<Empresa, Long>{

}
