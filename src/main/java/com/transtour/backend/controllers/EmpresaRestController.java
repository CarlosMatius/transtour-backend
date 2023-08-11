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

import com.transtour.backend.models.dto.EmpresaDTO;
import com.transtour.backend.models.services.IEmpresaService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1")
public class EmpresaRestController {

	private static final String MESSAGE = "message";
	private static final String ERROR = "error";
	
	@Autowired
	private IEmpresaService empresaService;

	@GetMapping("/empresas")
	public List<EmpresaDTO> index() {
		return empresaService.findAll();
	}
	
	@GetMapping("/empresas/{nit}")
	public EmpresaDTO show(@PathVariable String nit) {
		return empresaService.findByNit(nit);
	}
	
	@GetMapping("/empresas/page/{page}")
	public Page<EmpresaDTO> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 3);
		return empresaService.findAll(pageable);
	}
	
	@PostMapping("/empresas")
	public ResponseEntity<Object> create(@Valid @RequestBody EmpresaDTO empresaDTO, BindingResult result) {
		
		EmpresaDTO empresaNew;
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
			
			empresaNew = empresaService.save(empresaDTO);
			
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo registrar la empresa en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "La empresa ha sido registrada exitosamente!");
		response.put("destino", empresaNew);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/empresas/{id}")
	public ResponseEntity<Object> update(@Valid @RequestBody EmpresaDTO empresaDto, BindingResult result, @PathVariable Long id) {
		
		EmpresaDTO empresaActual = empresaService.findById(id);
		EmpresaDTO empresaActualizada;
		
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put(ERROR, errors);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(empresaActual == null) {
			response.put(MESSAGE, "Error: la empresa con ID: " + id + " No existe en el sistema");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			empresaActual.setNombre(empresaDto.getNombre());
			empresaActual.setNit(empresaDto.getNit());
			empresaActual.setEmail(empresaDto.getEmail());
			empresaActual.setTelefono(empresaDto.getTelefono());
			empresaActual.setEnabled(empresaDto.isEnabled());
			
			empresaActualizada = empresaService.save(empresaActual);
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo actualizar la empresa en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "La empresa ha sido actualizada exitosamente!");
		response.put("empresa", empresaActualizada);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}
	
	@DeleteMapping("/empresas/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			empresaService.delete(id);
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo eliminar la empresa en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "La empresa ha sido eliminada exitosamente!");
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
