package com.transtour.backend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

import com.transtour.backend.models.dto.MuelleDTO;
import com.transtour.backend.models.services.IMuelleService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1")
public class MuelleRestController {
	
	private static final String MESSAGE = "message";
	private static final String ERROR = "error";

	@Autowired
	private IMuelleService muelleService;

	@GetMapping("/muelles")
	public List<MuelleDTO> index() {
		return muelleService.findAll();
	}
	
	@PostMapping("/muelles")
	public ResponseEntity<Object> create(@Valid @RequestBody MuelleDTO muelleDTO, BindingResult result) {
		
		MuelleDTO muelleNew;
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
			muelleNew = muelleService.save(muelleDTO);
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo registrar el muelle en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "El muelle ha sido registrado exitosamente!");
		response.put("muelle", muelleNew);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/muelles/{id}")
	public ResponseEntity<Object>  update(@Valid @RequestBody MuelleDTO muelleDTO, BindingResult result, @PathVariable Long id) {
		MuelleDTO muelleActual = muelleService.findById(id);
		MuelleDTO muelleActualizado;
		
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put(ERROR, errors);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(muelleActual == null) {
			response.put(MESSAGE, "Error: el muelle con ID: " + id + " No existe en el sistema");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			muelleActual.setNombre(muelleDTO.getNombre());
			
			muelleActualizado = muelleService.save(muelleActual);
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo actualizar el muelle en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "El destino ha sido actualizado exitosamente!");
		response.put("muelle", muelleActualizado);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/muelles/{id}")
public ResponseEntity<Object> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			muelleService.delete(id);
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo eliminar el muelle en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "El muelle ha sido eliminado exitosamente!");
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
