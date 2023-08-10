package com.transtour.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.transtour.backend.models.dto.DestinoDto;
import com.transtour.backend.models.services.IDestinoService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class DestinoRestController {
	
	@Autowired
	private IDestinoService destinoService;

	@GetMapping("/destinos")
	public List<DestinoDto> index() {
		return destinoService.findAll();
	}
	
	@PostMapping("/destinos")
	@ResponseStatus(HttpStatus.CREATED)
	public DestinoDto create(@RequestBody DestinoDto destinoDto) {
		return destinoService.save(destinoDto);
	}
	
	@PutMapping("/destinos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public DestinoDto update(@RequestBody DestinoDto destinoDto, @PathVariable Long id) {
		DestinoDto destinoActual = destinoService.findById(id);
		
		destinoActual.setNombre(destinoDto.getNombre());
		destinoActual.setEnabled(destinoDto.isEnabled());
		
		return destinoService.save(destinoActual);
	}
	
	@DeleteMapping("/destinos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		destinoService.delete(id);
	}
}
