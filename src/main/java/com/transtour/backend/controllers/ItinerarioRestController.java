package com.transtour.backend.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.transtour.backend.models.dto.ItinerarioDTO;
import com.transtour.backend.models.services.IEmbarcacionService;
import com.transtour.backend.models.services.IItinerarioService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1")
public class ItinerarioRestController {
	
	private static final String MESSAGE = "message";
	private static final String ERROR = "error";

	@Autowired
	private IItinerarioService itinerarioService;
	
	@Autowired
	private IEmbarcacionService embarcacionService;

	@GetMapping("/itinerarios")
	public List<ItinerarioDTO> index() {
		return itinerarioService.findAll();
	}
	
	@GetMapping("/itinerarios/page/{page}")
	public Page<ItinerarioDTO> page(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 3);
		return itinerarioService.findAll(pageable);
	}
	
	@GetMapping("/itinerarios/{fechaEmbarque}/{nombreDestino}")
	public ResponseEntity<Object> findByItinerario(@PathVariable String fechaEmbarque, @PathVariable String nombreDestino) {
		
		List<ItinerarioDTO> itinerariosDTO;
		Map<String, Object> response = new HashMap<>();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fecha = LocalDate.parse(fechaEmbarque, formato);
		
		try {
			itinerariosDTO = itinerarioService.findByFechaAndDestino(fecha, nombreDestino);
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo realizar la consulta a la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(itinerariosDTO.isEmpty()) {
			response.put(MESSAGE, "No hay itinerarios disponibles con esos datos");
			return new ResponseEntity<> (response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(itinerariosDTO, HttpStatus.OK);
	}
	
	@PostMapping("/itinerarios")
	public ResponseEntity<Object> create(@Valid @RequestBody ItinerarioDTO itinerarioRequest, BindingResult result) {
		
		EmbarcacionDTO embarcacion;
		ItinerarioDTO itinerarioNew;
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
			embarcacion = embarcacionService.findById(itinerarioRequest.getEmbarcacion().getId());
			itinerarioRequest.setCupos(embarcacion.getCapacidad());
			itinerarioNew = itinerarioService.save(itinerarioRequest);
			
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo registrar el itinerario en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "El itinerario ha sido registrado exitosamente!");
		response.put("itinerario", itinerarioNew);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/itinerarios/{id}")
	public ResponseEntity<Object> update(@Valid @RequestBody ItinerarioDTO itinerarioRequest, BindingResult result, @PathVariable Long id) {
		ItinerarioDTO itinerarioActual = itinerarioService.findById(id);
		ItinerarioDTO itinerarioActualizado;
		
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put(ERROR, errors);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(itinerarioActual == null) {
			response.put(MESSAGE, "Error: el itinerario con ID: " + id + " No existe en el sistema");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			itinerarioActual.setFechaEmbarque(itinerarioRequest.getFechaEmbarque());
			itinerarioActual.setHoraSalida(itinerarioRequest.getHoraSalida());
			itinerarioActual.setHoraRegreso(itinerarioRequest.getHoraRegreso());
			itinerarioActual.setPrecio(itinerarioRequest.getPrecio());
			itinerarioActual.setEmbarcacion(itinerarioRequest.getEmbarcacion());
			itinerarioActual.setDestino(itinerarioRequest.getDestino());
			itinerarioActual.setMuelle(itinerarioRequest.getMuelle());
			
			itinerarioActualizado = itinerarioService.save(itinerarioActual);
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo actualizar el itinerario en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "El itinerario ha sido actualizado exitosamente!");
		response.put("itinerario", itinerarioActualizado);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/itinerarios/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			itinerarioService.delete(id);
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo eliminar el itinerario en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "El itinerario ha sido eliminado exitosamente!");
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
