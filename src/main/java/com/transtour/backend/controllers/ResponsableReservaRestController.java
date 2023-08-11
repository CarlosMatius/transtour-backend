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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transtour.backend.models.dto.ResponsableReservaDTO;
import com.transtour.backend.models.services.IResponsableReservaService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1")
public class ResponsableReservaRestController {
	
	private static final String MESSAGE = "message";
	private static final String ERROR = "error";
	
	@Autowired
	private IResponsableReservaService responsableService;
	
	@PostMapping("/responsables")
public ResponseEntity<Object> create(@Valid @RequestBody ResponsableReservaDTO responsableDTO, BindingResult result) {
		
		ResponsableReservaDTO responsableNew;
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
			
			responsableNew = responsableService.save(responsableDTO);
			
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo registrar la informacion de contacto de la reserva en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "La informacion de contacto ha sido registrada exitosamente!");
		response.put("Responsable Reserva", responsableNew);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
