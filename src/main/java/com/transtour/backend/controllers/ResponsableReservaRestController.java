package com.transtour.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.transtour.backend.models.dto.ResponsableReservaDto;
import com.transtour.backend.models.services.IResponsableReservaService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class ResponsableReservaRestController {
	
	@Autowired
	private IResponsableReservaService responsableService;
	
	@PostMapping("/responsables")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponsableReservaDto create(@RequestBody ResponsableReservaDto responsableDto) {
		return responsableService.save(responsableDto);
	}
}
