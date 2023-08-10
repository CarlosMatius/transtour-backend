package com.transtour.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.transtour.backend.models.dto.UsuarioDto;
import com.transtour.backend.models.services.IUsuarioService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class UsuarioRestController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("/usuarios")
	public List<UsuarioDto> index() {
		return usuarioService.findAll();
	}
	
	@GetMapping("/usuarios/{identificacion}")
	public UsuarioDto show(@PathVariable String identificacion) {
		return usuarioService.findByIdentificacion(identificacion);
	}
	
	@PostMapping("/usuarios")
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioDto create(@RequestBody UsuarioDto usuarioDto) {
		return usuarioService.save(usuarioDto);
	}
	
	@PutMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioDto update(@RequestBody UsuarioDto usuarioDto, @PathVariable Long id) {
		UsuarioDto usuarioActual = usuarioService.findById(id);
		
		usuarioActual.setNombre(usuarioDto.getNombre());
		usuarioActual.setApellido(usuarioDto.getApellido());
		usuarioActual.setTipoIdentificacion(usuarioDto.getTipoIdentificacion());
		usuarioActual.setIdentificacion(usuarioDto.getIdentificacion());
		usuarioActual.setUser(usuarioDto.getUser());
		usuarioActual.setClave(usuarioDto.getClave());
		usuarioActual.setEnabled(usuarioDto.isEnabled());
		usuarioActual.setEmpresa(usuarioDto.getEmpresa());
		
		return usuarioService.save(usuarioActual);
	}
	
	@DeleteMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		usuarioService.delete(id);
	}

}
