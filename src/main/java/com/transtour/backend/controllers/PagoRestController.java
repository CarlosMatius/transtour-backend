package com.transtour.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.transtour.backend.models.dto.PagoDto;
import com.transtour.backend.models.services.IPagoService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class PagoRestController {

	@Autowired
	private IPagoService pagoservice;

	@GetMapping("/pagos")
	public List<PagoDto> index() {
		return pagoservice.findAll();
	}
	
	@PostMapping("/pagos")
	@ResponseStatus(HttpStatus.CREATED)
	public PagoDto create(@RequestBody PagoDto pagoDto) {
		return pagoservice.save(pagoDto);
	}
	
	@GetMapping("/pagos/{nombreComercio}")
	public PagoDto show(@PathVariable String nombreComercio) {
		return pagoservice.findByNombreComercio(nombreComercio);
	}
}
