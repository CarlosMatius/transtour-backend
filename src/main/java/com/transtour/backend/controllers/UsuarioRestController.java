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

import com.transtour.backend.models.dto.UsuarioDTO;
import com.transtour.backend.models.dto.UsuarioResponse;
import com.transtour.backend.models.services.IUsuarioService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1")
public class UsuarioRestController {
	
	private static final String MESSAGE = "message";
	private static final String ERROR = "error";
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("/usuarios")
	public List<UsuarioResponse> index() {
		return usuarioService.findAll();
	}
	
	@GetMapping("/usuarios/{identificacion}")
	public ResponseEntity<Object> show(@PathVariable String identificacion) {
		UsuarioResponse usuarioResponse;
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			usuarioResponse = usuarioService.findByIdentificacion(identificacion);
			
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo realizar la consulta a la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(usuarioResponse == null) {
			response.put(MESSAGE, "El usuario con identificacion: " + identificacion +" No existe en el sistema");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(usuarioResponse, HttpStatus.OK); 
	}
	
	@GetMapping("/usuarios/page/{page}")
	public Page<UsuarioResponse> page(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 3);
		return usuarioService.findAll(pageable);
	}
	
	@PostMapping("/usuarios")
	public ResponseEntity<Object> create(@Valid @RequestBody UsuarioDTO usuarioDTO, BindingResult result) {
		
		UsuarioDTO usuarioNew;
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
			
			usuarioNew = usuarioService.save(usuarioDTO);
			
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo registrar el usuario en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "El usuario ha sido registrado exitosamente!");
		response.put("usuario", usuarioNew);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<Object> update(@Valid @RequestBody UsuarioDTO usuarioDTO, BindingResult result, @PathVariable Long id) {
		UsuarioDTO usuarioActual = usuarioService.findById(id);
		UsuarioDTO usuarioActualizado;
		
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put(ERROR, errors);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(usuarioActual == null) {
			response.put(MESSAGE, "Error: el usuario con ID: " + id + " No existe en el sistema");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			usuarioActual.setNombre(usuarioDTO.getNombre());
			usuarioActual.setApellido(usuarioDTO.getApellido());
			usuarioActual.setTipoIdentificacion(usuarioDTO.getTipoIdentificacion());
			usuarioActual.setIdentificacion(usuarioDTO.getIdentificacion());
			usuarioActual.setUsername(usuarioDTO.getUsername());
			usuarioActual.setPassword(usuarioDTO.getPassword());
			usuarioActual.setEnabled(usuarioDTO.isEnabled());
			usuarioActual.setEmpresa(usuarioDTO.getEmpresa());
			
			usuarioActualizado = usuarioService.save(usuarioActual);
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo actualizar el usuario en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "El destino ha sido actualizado exitosamente!");
		response.put("usuario", usuarioActualizado);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			usuarioService.delete(id);
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo eliminar el usuario en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "El usuario ha sido eliminado exitosamente!");
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
