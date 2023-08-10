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

import com.transtour.backend.models.dto.ItinerarioDto;
import com.transtour.backend.models.services.IItinerarioService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class ItinerarioRestController {

	@Autowired
	private IItinerarioService itinerarioService;

	@GetMapping("/itinerarios")
	public List<ItinerarioDto> index() {
		return itinerarioService.findAll();
	}
	
	
	@PostMapping("/itinerarios")
	@ResponseStatus(HttpStatus.CREATED)
	public ItinerarioDto create(@RequestBody ItinerarioDto itinerarioDto) {
		return itinerarioService.save(itinerarioDto);
	}
	
	@PutMapping("/itinerarios/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ItinerarioDto update(@RequestBody ItinerarioDto itinerarioDto, @PathVariable Long id) {
		ItinerarioDto itinerarioActual = itinerarioService.findById(id);
		
		itinerarioActual.setFechaEmbarque(itinerarioDto.getFechaEmbarque());
		itinerarioActual.setHoraSalidad(itinerarioDto.getHoraSalidad());
		itinerarioActual.setHoraRegreso(itinerarioDto.getHoraRegreso());
		itinerarioActual.setPrecio(itinerarioDto.getPrecio());
		itinerarioActual.setEmbarcacion(itinerarioDto.getEmbarcacion());
		itinerarioActual.setDestino(itinerarioDto.getDestino());
		itinerarioActual.setMuelle(itinerarioDto.getMuelle());
		
		return itinerarioService.save(itinerarioActual);
	}
	
	@DeleteMapping("/itinerarios/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		itinerarioService.delete(id);
	}
}
