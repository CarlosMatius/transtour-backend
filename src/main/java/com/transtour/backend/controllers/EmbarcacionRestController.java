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

import com.transtour.backend.models.dto.EmbarcacionDto;
import com.transtour.backend.models.services.IEmbarcacionService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class EmbarcacionRestController {

	@Autowired
	private IEmbarcacionService embarcacionService;

	@GetMapping("/embarcaciones")
	public List<EmbarcacionDto> index() {
		return embarcacionService.findAll();
	}
	
	@PostMapping("/embarcaciones")
	@ResponseStatus(HttpStatus.CREATED)
	public EmbarcacionDto create(@RequestBody EmbarcacionDto embarcacionDto) {
		return embarcacionService.save(embarcacionDto);
	}
	
	@PutMapping("/embarcaciones/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public EmbarcacionDto update(@RequestBody EmbarcacionDto embarcacionDto, @PathVariable Long id) {
		EmbarcacionDto embarcacionActual = embarcacionService.findById(id);
		
		embarcacionActual.setNombre(embarcacionDto.getNombre());
		embarcacionActual.setCapacidad(embarcacionDto.getCapacidad());
		embarcacionActual.setEnabled(embarcacionDto.isEnabled());
		
		return embarcacionService.save(embarcacionActual);
	}
	
	@DeleteMapping("/embarcaciones/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		embarcacionService.delete(id);
	}
}
