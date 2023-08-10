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

import com.transtour.backend.models.dto.TipoIdentificacionDto;
import com.transtour.backend.models.services.ITipoIdentificacionService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class TipoIdentificacionRestController {

	@Autowired
	private ITipoIdentificacionService tipoService;

	@GetMapping("/tipos-identificaciones")
	public List<TipoIdentificacionDto> index() {
		return tipoService.findAll();
	}
	
	@PostMapping("/tipos-identificaciones")
	@ResponseStatus(HttpStatus.CREATED)
	public TipoIdentificacionDto create(@RequestBody TipoIdentificacionDto tipoIdentificacionDto) {
		return tipoService.save(tipoIdentificacionDto);
	}
	
	@PutMapping("/tipos-identificaciones/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public TipoIdentificacionDto update(@RequestBody TipoIdentificacionDto tipoIdentificacionDto, @PathVariable Long id) {
		TipoIdentificacionDto tipoIdentificacionActual = tipoService.findById(id);
		
		tipoIdentificacionActual.setNombre(tipoIdentificacionDto.getNombre());
		
		return tipoService.save(tipoIdentificacionActual);
	}
	
	@DeleteMapping("/tipos-identificaciones/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		tipoService.delete(id);
	}
}
