package com.transtour.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transtour.backend.models.dto.RolDTO;
import com.transtour.backend.models.services.IRolService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1")
public class RolRestController {
	
	@Autowired
	private IRolService rolService;
	
	@GetMapping("/roles")
	public List<RolDTO> index() {
		return rolService.findAll();
	}
}