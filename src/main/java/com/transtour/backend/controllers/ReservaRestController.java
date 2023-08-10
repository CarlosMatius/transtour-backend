package com.transtour.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.transtour.backend.models.dto.ReservaDto;
import com.transtour.backend.models.services.IReservaService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class ReservaRestController {

	@Autowired
	private IReservaService reservaService;

	@GetMapping("/reservas")
	public List<ReservaDto> index() {
		return reservaService.findAll();
	}
	
	@GetMapping("/reservas/{codigoReserva}")
	public ReservaDto showCodigo(@PathVariable String codigoReserva) {
		return reservaService.findByCodigoReserva(codigoReserva);
	}
	
	@PostMapping("/reservas")
	@ResponseStatus(HttpStatus.CREATED)
	public ReservaDto create(@RequestBody ReservaDto reservaDto) {
		return reservaService.save(reservaDto);
	}
	
	@DeleteMapping("/reservas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		reservaService.delete(id);
	}
}
