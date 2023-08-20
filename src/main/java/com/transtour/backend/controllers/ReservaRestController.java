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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transtour.backend.models.dto.response.ReservaResponse;
import com.transtour.backend.models.services.IReservaService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1")
public class ReservaRestController {
	
	private static final String MESSAGE = "message";
	private static final String ERROR = "error";

	@Autowired
	private IReservaService reservaService;

	@GetMapping("/reservas")
	public List<ReservaResponse> index() {
		return reservaService.findAll();
	}
	
	@GetMapping("/reservas/{codigoReserva}")
	public ResponseEntity<Object> show(@PathVariable String codigoReserva) {
		ReservaResponse reservaDTO;
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			reservaDTO = reservaService.findByCodigoReserva(codigoReserva);
			
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo realizar la consulta a la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(reservaDTO == null) {
			response.put(MESSAGE, "La reserva con codigo: " + codigoReserva +" No existe en el sistema");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(reservaDTO, HttpStatus.OK); 
	}
	
	@GetMapping("/reservas/page/{page}")
	public Page<ReservaResponse> page(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 3);
		return reservaService.findAll(pageable);
	}
	
	@PostMapping("/reservas")
public ResponseEntity<Object> create(@Valid @RequestBody ReservaResponse reservaDTO, BindingResult result) {
		
		ReservaResponse reservaNew;
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
			
			reservaNew = reservaService.save(reservaDTO);
			
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo registrar la reserva en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "La reserva ha sido registrada exitosamente!");
		response.put("reserva", reservaNew);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/reservas/{id}")
public ResponseEntity<Object> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			reservaService.delete(id);
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo eliminar la reserva en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "La reserva ha sido eliminada exitosamente!");
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
