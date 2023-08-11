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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transtour.backend.models.dto.PagoDTO;
import com.transtour.backend.models.services.IPagoService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1")
public class PagoRestController {

	private static final String MESSAGE = "message";
	private static final String ERROR = "error";
	
	@Autowired
	private IPagoService pagoservice;

	@GetMapping("/pagos")
	public List<PagoDTO> index() {
		return pagoservice.findAll();
	}
	
	@GetMapping("/pagos/page/{page}")
	public Page<PagoDTO> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 3);
		return pagoservice.findAll(pageable);
	}
	
	@GetMapping("/pagos/{nombreComercio}")
	public ResponseEntity<Object> show(@PathVariable String nombreComercio) {
		
		PagoDTO pagoDTO;
		Map<String, Object> response = new HashMap<>();
		
		try {
			pagoDTO = pagoservice.findByNombreComercio(nombreComercio);
			
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo realizar la consulta a la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(pagoDTO == null) {
			response.put(MESSAGE, "El pago con numero de recibo: " + nombreComercio +" No existe en el sistema");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(pagoDTO, HttpStatus.OK);

	}
	
	@PostMapping("/pagos")
	public ResponseEntity<Object> create(@Valid @RequestBody PagoDTO pagoDTO, BindingResult result) {
		
		PagoDTO pagoNew;
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
			
			pagoNew = pagoservice.save(pagoDTO);
			
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo registrar el pago en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "El pago ha sido registrado exitosamente!");
		response.put("pago", pagoNew);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
