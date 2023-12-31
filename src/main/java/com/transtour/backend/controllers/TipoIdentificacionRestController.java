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

import com.transtour.backend.models.dto.TipoIdentificacionDTO;
import com.transtour.backend.models.services.ITipoIdentificacionService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1")
public class TipoIdentificacionRestController {
	
	private static final String MESSAGE = "message";
	private static final String ERROR = "error";

	@Autowired
	private ITipoIdentificacionService tipoService;

	@GetMapping("/tipos-identificaciones")
	public List<TipoIdentificacionDTO> index() {
		return tipoService.findAll();
	}
	
	@PostMapping("/tipos-identificaciones")
public ResponseEntity<Object> create(@Valid @RequestBody TipoIdentificacionDTO tipoDTO, BindingResult result) {
		
		TipoIdentificacionDTO tipoNew;
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
			
			tipoNew = tipoService.save(tipoDTO);
			
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo registrar el tipo de documento en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "El tipo de docuemnto ha sido registrado exitosamente!");
		response.put("tipo de documento", tipoNew);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/tipos-identificaciones/{id}")
	public ResponseEntity<Object> update(@Valid @RequestBody TipoIdentificacionDTO tipoDTO, BindingResult result, @PathVariable Long id) {
		TipoIdentificacionDTO tipoActual = tipoService.findById(id);
		TipoIdentificacionDTO tipoActualizado ;
		
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put(ERROR, errors);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(tipoActual == null) {
			response.put(MESSAGE, "Error: el tipo de docmento con ID: " + id + " No existe en el sistema");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			tipoActual.setNombre(tipoDTO.getNombre());
			
			tipoActualizado = tipoService.save(tipoActual);
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo actualizar el tipo de documento en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "El destino ha sido actualizado exitosamente!");
		response.put("tipo de documento", tipoActualizado);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/tipos-identificaciones/{id}")
public ResponseEntity<Object> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			tipoService.delete(id);
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo eliminar el tipo de documento en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "El tipo de documento ha sido eliminado exitosamente!");
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
