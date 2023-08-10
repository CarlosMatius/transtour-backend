package com.transtour.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.transtour.backend.models.dto.RolDto;
import com.transtour.backend.models.services.IRolService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class RolRestController {

	@Autowired
	private IRolService rolService;
	
	@GetMapping("/roles")
	public List<RolDto> index() {
		return rolService.findAll();
	}
	
	@PostMapping("/roles")
	@ResponseStatus(HttpStatus.CREATED)
	public RolDto create(@RequestBody RolDto rolDto) {
		return rolService.save(rolDto);
	}
}
