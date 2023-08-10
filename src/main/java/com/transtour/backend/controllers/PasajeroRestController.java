package com.transtour.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.transtour.backend.models.dto.PasajeroDto;
import com.transtour.backend.models.services.IPasajeroService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class PasajeroRestController {

	@Autowired
	private IPasajeroService pasajeroService;

	@PostMapping("/pasajeros")
	@ResponseStatus(HttpStatus.CREATED)
	public PasajeroDto create(@RequestBody PasajeroDto pasajeroDto) {
		return pasajeroService.save(pasajeroDto);
	}
	
	@PutMapping("/pasajeros/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public PasajeroDto update(@RequestBody PasajeroDto pasajeroDto, @PathVariable Long id) {
		PasajeroDto pasajeroActual = pasajeroService.findById(id);
		
		pasajeroActual.setNombre(pasajeroDto.getNombre());
		pasajeroActual.setApellido(pasajeroDto.getApellido());
		pasajeroActual.setTipoIdentificacion(pasajeroDto.getTipoIdentificacion());
		pasajeroActual.setIdentificacion(pasajeroDto.getIdentificacion());
		
		return pasajeroService.save(pasajeroActual);
	}

}
