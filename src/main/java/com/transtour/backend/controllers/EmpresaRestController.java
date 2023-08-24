package com.transtour.backend.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.transtour.backend.models.dto.EmpresaDTO;
import com.transtour.backend.models.services.IEmpresaService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1")
public class EmpresaRestController {

	private static final String MESSAGE = "message";
	private static final String ERROR = "error";
	private static final String CARPETA = "uploads";
	private static final String EMPRESA = "empresa";
	private final Logger log = LoggerFactory.getLogger(EmpresaRestController.class);
	
	@Autowired
	private IEmpresaService empresaService;
	

	@GetMapping("/empresas")
	public List<EmpresaDTO> index() {
		return empresaService.findAll();
	}
	
	@GetMapping("/empresas/page/{page}")
	public Page<EmpresaDTO> page(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 3);
		return empresaService.findAll(pageable);
	}
	
	@GetMapping("/empresas/by/{nit}")
	public ResponseEntity<Object> show(@PathVariable String nit) {
		
		EmpresaDTO empresa;
		Map<String, Object> response = new HashMap<>();
		
		try {
			empresa = empresaService.findByNit(nit);
		} catch (DataAccessException e) {
			response.put(MESSAGE, "Error al realizar la consulta en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(empresa == null) {
			response.put(MESSAGE, "Error: la empresa con Nit: " + nit + " No existe");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(empresa, HttpStatus.OK);
	}
	
	@GetMapping("/empresas/{id}")
	public ResponseEntity<Object> showId(@PathVariable Long id) {
		
		EmpresaDTO empresa;
		Map<String, Object> response = new HashMap<>();
		
		try {
			empresa = empresaService.findById(id);
		} catch (DataAccessException e) {
			response.put(MESSAGE, "Error al realizar la consulta en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(empresa == null) {
			response.put(MESSAGE, "Error: la empresa con Id: " + id + " No existe en el sistema");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(empresa, HttpStatus.OK);
	}
	
	@PostMapping("/empresas")
	public ResponseEntity<Object> create(@Valid @RequestBody EmpresaDTO empresaRequest, BindingResult result) {
		
		EmpresaDTO empresaNew;
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
			
			empresaNew = empresaService.save(empresaRequest);
			
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo registrar la empresa en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "La empresa ha sido registrada exitosamente!");
		response.put(EMPRESA, empresaNew);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/empresas/{id}")
	public ResponseEntity<Object> update(@Valid @RequestBody EmpresaDTO empresaRequest, BindingResult result, @PathVariable Long id) {
		
		EmpresaDTO empresaActual = empresaService.findById(id);
		EmpresaDTO empresaActualizada;
		
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put(ERROR, errors);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(empresaActual == null) {
			response.put(MESSAGE, "La empresa con ID: " + id + " No existe en el sistema");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			empresaActual.setNombre(empresaRequest.getNombre());
			empresaActual.setNit(empresaRequest.getNit());
			empresaActual.setEmail(empresaRequest.getEmail());
			empresaActual.setTelefono(empresaRequest.getTelefono());
			empresaActual.setEnabled(empresaRequest.isEnabled());
			
			empresaActualizada = empresaService.save(empresaActual);
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo actualizar la empresa en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(EMPRESA, empresaActualizada);
		response.put(MESSAGE, "La empresa ha sido actualizada exitosamente!");
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}
	
	@DeleteMapping("/empresas/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) throws IOException {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			EmpresaDTO empresaDTO = empresaService.findById(id);
			String nombreFotoAnterior = empresaDTO.getImagen();
			
			if(nombreFotoAnterior !=null && nombreFotoAnterior.length() > 0) {
				Path rutaFotoAnterior = Paths.get(CARPETA).resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					Files.delete(rutaFotoAnterior);
				}
			}
			empresaService.delete(id);
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo eliminar la empresa en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "La empresa ha sido eliminada exitosamente!");
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/empresas/upload")
	public ResponseEntity<Object> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("archivo") Long id) {
		Map<String, Object> response = new HashMap<>();
		
		EmpresaDTO empresaRequest = empresaService.findById(id);
		String ruta = archivo.getOriginalFilename();
		
		if(!archivo.isEmpty() && ruta !=null) {
			String nombreArchivo = UUID.randomUUID().toString() + "_" + ruta.replace(" ", "");
			Path rutaArchivo = Paths.get(CARPETA).resolve(nombreArchivo).toAbsolutePath();
			log.info("{}", rutaArchivo);
			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} catch (IOException e) {
				response.put(MESSAGE, "Error al subir la imagen de la empresa");
				response.put(ERROR, e.getMessage() +": "+ e.getCause().getMessage());
				return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = empresaRequest.getImagen();
			
			if(nombreFotoAnterior !=null && nombreFotoAnterior.length() > 0) {
				Path rutaFotoAnterior = Paths.get(CARPETA).resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					try {
						Files.delete(rutaFotoAnterior);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			empresaRequest.setImagen(nombreArchivo);
			empresaService.save(empresaRequest);
			
			response.put(EMPRESA, empresaRequest);
			response.put(MESSAGE, "Has subido correctamente la imagen: " + nombreArchivo);
		}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/empresas/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {
		Path rutaArchivo = Paths.get(CARPETA).resolve(nombreFoto).toAbsolutePath();
		log.info("{}", rutaArchivo);
		HttpHeaders cabecera = new HttpHeaders();
		Resource recurso = null;
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		if(recurso !=null) {
			if(!recurso.exists() && !recurso.isReadable()) {
				throw new ErrorExceptionUpload(nombreFoto);
			}
			
			cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		}
		
		return new ResponseEntity<>(recurso, cabecera, HttpStatus.OK);
	}
	
	// excepcion personalizada
	private class ErrorExceptionUpload extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public ErrorExceptionUpload(String parametro) {
			super("Error no se pudo cargar la imagen:" + parametro);
		}
	}
}
