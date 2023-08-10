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

import com.transtour.backend.models.dto.EmpresaDto;
import com.transtour.backend.models.services.IEmpresaService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class EmpresaRestController {

	@Autowired
	private IEmpresaService empresaService;

	@GetMapping("/empresas")
	public List<EmpresaDto> index() {
		return empresaService.findAll();
	}
	
	@GetMapping("/empresas/{nit}")
	public EmpresaDto show(@PathVariable String nit) {
		return empresaService.findByNit(nit);
	}
	
	@PostMapping("/empresas")
	@ResponseStatus(HttpStatus.CREATED)
	public EmpresaDto create(@RequestBody EmpresaDto empresaDto) {
		return empresaService.save(empresaDto);
	}
	
	@PutMapping("/empresas/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public EmpresaDto update(@RequestBody EmpresaDto empresaDto, @PathVariable Long id) {
		EmpresaDto empresaActual = empresaService.findById(id);
		
		empresaActual.setNombre(empresaDto.getNombre());
		empresaActual.setNit(empresaDto.getNit());
		empresaActual.setEmail(empresaDto.getEmail());
		empresaActual.setTelefono(empresaDto.getTelefono());
		empresaActual.setEnabled(empresaDto.isEnabled());
		
		return empresaService.save(empresaActual);
	}
	
	@DeleteMapping("/empresas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		empresaService.delete(id);
	}
}
