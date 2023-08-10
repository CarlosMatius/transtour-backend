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

import com.transtour.backend.models.dto.MuelleDto;
import com.transtour.backend.models.services.IMuelleService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class MuelleRestController {

	@Autowired
	private IMuelleService muelleService;

	@GetMapping("/muelles")
	public List<MuelleDto> index() {
		return muelleService.findAll();
	}
	
	@PostMapping("/muelles")
	@ResponseStatus(HttpStatus.CREATED)
	public MuelleDto create(@RequestBody MuelleDto muelleDto) {
		return muelleService.save(muelleDto);
	}
	
	@PutMapping("/muelles/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public MuelleDto update(@RequestBody MuelleDto muelleDto, @PathVariable Long id) {
		MuelleDto muelleActual = muelleService.findById(id);
		
		muelleActual.setNombre(muelleDto.getNombre());
		
		return muelleService.save(muelleActual);
	}
	
	@DeleteMapping("/muelles/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		muelleService.delete(id);
	}
}
