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

import com.transtour.backend.models.dto.response.DestinoResponse;
import com.transtour.backend.models.services.IDestinoService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1")
public class DestinoRestController {
	
	private static final String MESSAGE = "message";
	
	private static final String ERROR = "error";
	
	@Autowired
	private IDestinoService destinoService;

	@GetMapping("/destinos")
	public List<DestinoResponse> index() {
		return destinoService.findAll();
	}
	
	@GetMapping("/destinos/page/{page}")
	public Page<DestinoResponse> page(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 3);
		return destinoService.findAll(pageable);
	}
	
	@PostMapping("/destinos")
	public ResponseEntity<Object> create(@Valid @RequestBody DestinoResponse destinoDTO, BindingResult result) {
		
		DestinoResponse destinoNew;
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
			
			destinoNew = destinoService.save(destinoDTO);
			
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo registrar el destino en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "El destino ha sido registrado exitosamente!");
		response.put("destino", destinoNew);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/destinos/{id}")
	public ResponseEntity<Object> update(@Valid @RequestBody DestinoResponse destinoDTO, BindingResult result, @PathVariable Long id) {
		DestinoResponse destinoActual = destinoService.findById(id);
		DestinoResponse destinoActualizado ;
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put(ERROR, errors);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(destinoActual == null) {
			response.put(MESSAGE, "Error: el destino con ID: " + id + " No existe en el sistema");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			destinoActual.setNombre(destinoDTO.getNombre());
			destinoActual.setEnabled(destinoDTO.isEnabled());
			
			destinoActualizado = destinoService.save(destinoActual);
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo actualizar el destino en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "El destino ha sido actualizado exitosamente!");
		response.put("destino", destinoActualizado);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/destinos/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			destinoService.delete(id);
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo eliminar el destino en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "El destino ha sido eliminado exitosamente!");
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
