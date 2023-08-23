package com.transtour.backend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transtour.backend.models.dto.EmbarcacionDTO;
import com.transtour.backend.models.services.IEmbarcacionService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1")
public class EmbarcacionRestController {

	private static final String MESSAGE = "message";
	private static final String ERROR = "error";
	
	@Autowired
	private IEmbarcacionService embarcacionService;

	@GetMapping("/embarcaciones")
	public List<EmbarcacionDTO> index() {
		return embarcacionService.findAll();
	}
	
	@GetMapping("/embarcaciones/page/{page}")
	public Page<EmbarcacionDTO> page(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 3);
		return embarcacionService.findAll(pageable);
	}
	
	@PostMapping("/embarcaciones")
	public ResponseEntity<Object> create(@Valid @RequestBody EmbarcacionDTO embarcacionDTO, BindingResult result) {
		
		EmbarcacionDTO embarcacionNew;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put(ERROR, errors);			
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			
			embarcacionNew = embarcacionService.save(embarcacionDTO);
			
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo registrar la embarcacion en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "la embarcacion ha sido registrada exitosamente!");
		response.put("embarcacion", embarcacionNew);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/embarcaciones/{id}")
	public ResponseEntity<Object> update(@Valid @RequestBody EmbarcacionDTO embarcacionDTO, BindingResult result, @PathVariable Long id) {
		EmbarcacionDTO embarcacionActual = embarcacionService.findById(id);
		EmbarcacionDTO embarcacionActualizada;
		
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put(ERROR, errors);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(embarcacionActual == null) {
			response.put(MESSAGE, "Error: la embarcacion con ID: " + id + " No existe en el sistema");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			embarcacionActual.setNombre(embarcacionDTO.getNombre());
			embarcacionActual.setCapacidad(embarcacionDTO.getCapacidad());
			embarcacionActual.setEnabled(embarcacionDTO.isEnabled());
			
			embarcacionActualizada = embarcacionService.save(embarcacionActual);
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo actualizar la embarcacion en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "La embarcacion ha sido actualizada exitosamente!");
		response.put("destino", embarcacionActualizada);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/embarcaciones/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			embarcacionService.delete(id);
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo eliminar la embarcacion en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "la embarcacion ha sido eliminada exitosamente!");
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
