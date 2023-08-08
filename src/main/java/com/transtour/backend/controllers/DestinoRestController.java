package com.transtour.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transtour.backend.models.dto.DestinoDto;
import com.transtour.backend.models.services.IDestinoService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/transtour")
public class DestinoRestController {
	
	@Autowired
	private IDestinoService destinoService;

	@GetMapping("/destinos")
	public List<DestinoDto> index() {
		return destinoService.findAll();
	}
}
