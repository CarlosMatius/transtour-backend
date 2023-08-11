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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transtour.backend.models.dto.PasajeroDTO;
import com.transtour.backend.models.services.IPasajeroService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1")
public class PasajeroRestController {

	private static final String MESSAGE = "message";
	private static final String ERROR = "error";
	
	@Autowired
	private IPasajeroService pasajeroService;

	@PostMapping("/pasajeros")
	public ResponseEntity<Object> create(@Valid @RequestBody PasajeroDTO pasajeroDTO, BindingResult result) {
		
		PasajeroDTO pasajeroNew;
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
			
			pasajeroNew = pasajeroService.save(pasajeroDTO);
			
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo agregar el pasajero a la reserva");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "El pasajero ha sido agregado a la reserva exitosamente!");
		response.put("pasajero", pasajeroNew);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/pasajeros/{id}")
	public ResponseEntity<Object> update(@Valid @RequestBody PasajeroDTO pasajeroDTO, BindingResult result, @PathVariable Long id) {
		PasajeroDTO pasajeroActual = pasajeroService.findById(id);
		PasajeroDTO pasajeroActualizado;
		
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put(ERROR, errors);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(pasajeroActual == null) {
			response.put(MESSAGE, "Error: el pasajero con ID: " + id + " No pertenece a ninguna reserva");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			pasajeroActual.setNombre(pasajeroDTO.getNombre());
			pasajeroActual.setApellido(pasajeroDTO.getApellido());
			pasajeroActual.setTipoIdentificacion(pasajeroDTO.getTipoIdentificacion());
			pasajeroActual.setIdentificacion(pasajeroDTO.getIdentificacion());
			
			pasajeroActualizado = pasajeroService.save(pasajeroActual);
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo actualizar el pasajero en la reserva");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "El pasajero ha sido actualizado exitosamente!");
		response.put("pasajero", pasajeroActualizado);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
