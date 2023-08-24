package com.transtour.backend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

import com.transtour.backend.helpers.CommonUtils;
import com.transtour.backend.models.dto.EmbarcacionDTO;
import com.transtour.backend.models.entity.Empresa;
import com.transtour.backend.models.services.IEmbarcacionService;
import com.transtour.backend.models.services.IEmpresaService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1")
public class EmbarcacionRestController {

	private static final String MESSAGE = "message";
	private static final String ERROR = "error";
	
	@Autowired
	private IEmbarcacionService embarcacionService;
	
	@Autowired
	private IEmpresaService empresaService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CommonUtils commonUtil;

	@GetMapping("/embarcaciones")
	public List<EmbarcacionDTO> index(Authentication authentication) {
		List<EmbarcacionDTO> embarcaciones;
		
		if (commonUtil.isSuperAdmin(authentication.getName())) {
			embarcaciones  = embarcacionService.findAll();
		}
		else {
			embarcaciones  = embarcacionService.findAllByEmpresa(modelMapper.map(commonUtil.infoUsuario(authentication.getName()).getEmpresa(), Empresa.class));
		}
		
		return embarcaciones;
	}
	
	@GetMapping("/embarcaciones/page/{page}")
	public Page<EmbarcacionDTO> page(@PathVariable Integer page, Authentication authentication) {
		Pageable pageable = PageRequest.of(page, 3);
		
		if (commonUtil.isSuperAdmin(authentication.getName())) {
			return embarcacionService.findAllPage(pageable);
		}
		else {
			return embarcacionService.findAllByEmpresaPage(modelMapper.map(commonUtil.infoUsuario(authentication.getName()).getEmpresa(), Empresa.class), pageable);
		}
	}
	
	@GetMapping("/embarcaciones/{id}")
	public ResponseEntity<Object> showId(@PathVariable Long id, Authentication authentication) {
		EmbarcacionDTO embarcacionDTO;
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			if (commonUtil.isSuperAdmin(authentication.getName())) {
				embarcacionDTO = embarcacionService.findById(id);
				if(embarcacionDTO == null) {
					response.put(MESSAGE, "La embarcacion con id: " + id +" No existe en el sistema");
					return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
				}
			}
			else {
				embarcacionDTO = embarcacionService.findByIdAndEmpresa(id,modelMapper.map(commonUtil.infoUsuario(authentication.getName()).getEmpresa(), Empresa.class));
				if(embarcacionDTO == null) {
					response.put(MESSAGE, "La embarcacion con id: " + id +" No existe en la empresa");
					return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
				}
			}
			
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo realizar la consulta a la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return new ResponseEntity<>(embarcacionDTO, HttpStatus.OK); 
	}
	
	@PostMapping("/embarcaciones")
	public ResponseEntity<Object> create(@Valid @RequestBody EmbarcacionDTO embarcacionDTO, BindingResult result, Authentication authentication) {
		Map<String, Object> response = new HashMap<>();
		EmbarcacionDTO embarcacionNew;
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put(ERROR, errors);			
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			if (!commonUtil.isSuperAdmin(authentication.getName())) {
				embarcacionDTO.setEmpresa(commonUtil.infoUsuario(authentication.getName()).getEmpresa());
			}

			embarcacionNew = embarcacionService.save(embarcacionDTO);
			
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo registrar la embarcacion en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "la embarcacion ha sido registrada exitosamente!");
		response.put("embarcacion", embarcacionNew);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/embarcaciones/{id}")
	public ResponseEntity<Object> update(@Valid @RequestBody EmbarcacionDTO embarcacionDTO, BindingResult result, @PathVariable Long id, Authentication authentication) {
		EmbarcacionDTO embarcacionActual;
		EmbarcacionDTO embarcacionActualizada;
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put(ERROR, errors);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (commonUtil.isSuperAdmin(authentication.getName())) {
			embarcacionActual = embarcacionService.findById(id);
			if(embarcacionActual == null) {
				response.put(MESSAGE, "Error: la embarcacion con id: " + id + " No existe en el sistema");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		}
		else {
			embarcacionActual = embarcacionService.findByIdAndEmpresa(id, modelMapper.map(commonUtil.infoUsuario(authentication.getName()).getEmpresa(), Empresa.class));
			if(embarcacionActual == null) {
				response.put(MESSAGE, "Error: la embarcacion con id: " + id +" No existe en la empresa");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		}
		
		try {
			embarcacionActual.setNombre(embarcacionDTO.getNombre());
			embarcacionActual.setCapacidad(embarcacionDTO.getCapacidad());
			embarcacionActual.setEnabled(embarcacionDTO.isEnabled());
			embarcacionActual.setEmpresa(embarcacionDTO.getEmpresa());
			
			embarcacionActualizada = embarcacionService.save(embarcacionActual);
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo actualizar la embarcacion en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "La embarcacion ha sido actualizada exitosamente!");
		response.put("embarcacion", embarcacionActualizada);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/embarcaciones/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id, Authentication authentication) {
		
		EmbarcacionDTO embarcacionDTO;
		EmbarcacionDTO embarcacionEliminar = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			if (commonUtil.isSuperAdmin(authentication.getName())) {
				embarcacionDTO = embarcacionService.findById(id);
				if(embarcacionDTO != null) {
					embarcacionEliminar = asignarEmbarcacion(embarcacionDTO.getEmpresa().getEmbarcaciones(), embarcacionDTO.getId());
					if(embarcacionEliminar != null) {
						embarcacionDTO.getEmpresa().getEmbarcaciones().remove(embarcacionEliminar);
						empresaService.save(embarcacionDTO.getEmpresa());
						response.put(MESSAGE, "La embarcacion ha sido eliminada exitosamente!");
					}
				}
				else {
					response.put(MESSAGE, "La embarcacion con id: " + id +" No existe en el sistema");
					return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
				}
			}
			else {
				embarcacionDTO = embarcacionService.findByIdAndEmpresa(id, modelMapper.map(commonUtil.infoUsuario(authentication.getName()).getEmpresa(), Empresa.class));
				if(embarcacionDTO != null) {
					embarcacionEliminar = asignarEmbarcacion(embarcacionDTO.getEmpresa().getEmbarcaciones(), embarcacionDTO.getId());
					if(embarcacionEliminar != null) {
						embarcacionDTO.getEmpresa().getEmbarcaciones().remove(embarcacionEliminar);
						empresaService.save(embarcacionDTO.getEmpresa());
						response.put(MESSAGE, "La embarcacion ha sido eliminada exitosamente!");
					}
				}
				else {
					response.put(MESSAGE, "La embarcacion con id: " + id +" No existe en esta empresa");
					return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
				}
			
			}
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo eliminar la embarcacion en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "la embarcacion ha sido eliminada exitosamente!");
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	private EmbarcacionDTO asignarEmbarcacion(List<EmbarcacionDTO> embarcaciones, Long embarcacionId) {
		EmbarcacionDTO embarcacionEliminar = null;
		for(EmbarcacionDTO embarcacion: embarcaciones) {
			if(embarcacion.getId().equals(embarcacionId)) {
				embarcacionEliminar = embarcacion;
				break;
			}
		}
		return embarcacionEliminar;
	}
}