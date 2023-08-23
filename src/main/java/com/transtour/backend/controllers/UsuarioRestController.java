package com.transtour.backend.controllers;

import java.util.ArrayList;
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

import com.transtour.backend.models.dto.RolDTO;
import com.transtour.backend.models.dto.UsuarioDTO;
import com.transtour.backend.models.entity.Empresa;
import com.transtour.backend.models.services.IEmpresaService;
import com.transtour.backend.models.services.IRolService;
import com.transtour.backend.models.services.IUsuarioService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1")
public class UsuarioRestController {
	
	private static final String MESSAGE = "message";
	private static final String ERROR = "error";
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IRolService rolService;
	
	@Autowired
	private IEmpresaService empresaService;
	
	@GetMapping("/usuarios")
	public List<UsuarioDTO> index(Authentication authentication) {
		List<UsuarioDTO> usuarios;
		UsuarioDTO usuarioSesion = usuarioService.findByUsername(authentication.getName());
		
		if (isSuperAdmin(usuarioSesion.getRoles())) {
			usuarios  = usuarioService.findAll();
		}
		else {
			usuarios  = usuarioService.findAllByEmpresa(modelMapper.map(usuarioSesion.getEmpresa(), Empresa.class));
		}
		return usuarios;
	}
	
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Object> showId(@PathVariable Long id, Authentication authentication) {
		UsuarioDTO usuarioDTO;
		Map<String, Object> response = new HashMap<>();
		UsuarioDTO usuarioSesion = usuarioService.findByUsername(authentication.getName());
		
		try {
			
			if (isSuperAdmin(usuarioSesion.getRoles())) {
				usuarioDTO = usuarioService.findById(id);
				if(usuarioDTO == null) {
					response.put(MESSAGE, "El usuario con id: " + id +" No existe en el sistema");
					return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
				}
			}
			else {
				usuarioDTO = usuarioService.findByIdAndEmpresa(id,modelMapper.map(usuarioSesion.getEmpresa(), Empresa.class));
				if(usuarioDTO == null) {
					response.put(MESSAGE, "El usuario con id: " + id +" No existe en la empresa");
					return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
				}
			}
			
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo realizar la consulta a la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return new ResponseEntity<>(usuarioDTO, HttpStatus.OK); 
	}
	
	@GetMapping("/usuarios/by/{identificacion}")
	public ResponseEntity<Object> showIdentificacion(@PathVariable String identificacion, Authentication authentication) {
		UsuarioDTO usuarioDTO;
		Map<String, Object> response = new HashMap<>();
		UsuarioDTO usuarioSesion = usuarioService.findByUsername(authentication.getName());
		
		try {
			
			if (isSuperAdmin(usuarioSesion.getRoles())) {
				usuarioDTO = usuarioService.findByIdentificacion(identificacion);
				if(usuarioDTO == null) {
					response.put(MESSAGE, "El usuario con identificacion: " + identificacion +" No existe en el sistema");
					return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
				}
			}
			else {
				usuarioDTO = usuarioService.findByIdentificacionAndEmpresa(identificacion,modelMapper.map(usuarioSesion.getEmpresa(), Empresa.class));
				if(usuarioDTO == null) {
					response.put(MESSAGE, "El usuario con identificacion: " + identificacion +" No existe en la empresa");
					return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
				}
			}
			
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo realizar la consulta a la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return new ResponseEntity<>(usuarioDTO, HttpStatus.OK); 
	}
	
	@GetMapping("/usuarios/page/{page}")
	public Page<UsuarioDTO> page(@PathVariable Integer page, Authentication authentication) {
		Pageable pageable = PageRequest.of(page, 3);
		UsuarioDTO usuarioSesion = usuarioService.findByUsername(authentication.getName());
		
		if (isSuperAdmin(usuarioSesion.getRoles())) {
			return usuarioService.findAllPage(pageable);
		}
		else {
			return usuarioService.findAllByEmpresaPage(modelMapper.map(usuarioSesion.getEmpresa(), Empresa.class), pageable);
		}

	}
	
	@PostMapping("/usuarios")
	public ResponseEntity<Object> create(
			@Valid @RequestBody UsuarioDTO usuarioDTO, 
			BindingResult result, Authentication authentication) {

		Map<String, Object> response = new HashMap<>();
		UsuarioDTO usuarioSesion = usuarioService.findByUsername(authentication.getName());
		UsuarioDTO usuarioNew;
		RolDTO rolDTO;
		List<RolDTO> roles = new ArrayList<>();

		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put(ERROR, errors);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			
			for(RolDTO rolRequest: usuarioDTO.getRoles()) {
				rolDTO = rolService.findById(rolRequest.getId());
				if(rolDTO != null) {
					roles.add(rolDTO);
				}
			}
			
			if (!isSuperAdmin(usuarioSesion.getRoles())) {
				usuarioDTO.setEmpresa(usuarioSesion.getEmpresa());
			}
			
			usuarioDTO.setRoles(roles);
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
	public ResponseEntity<Object> update(
			@Valid @RequestBody UsuarioDTO usuarioDTO, 
			BindingResult result, @PathVariable Long id, 
			Authentication authentication) {
		
		UsuarioDTO usuarioSesion = usuarioService.findByUsername(authentication.getName());
		UsuarioDTO usuarioActual;
		UsuarioDTO usuarioActualizado;
		Map<String, Object> response = new HashMap<>();
		RolDTO rolDTO;
		List<RolDTO> roles = new ArrayList<>();
		
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put(ERROR, errors);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (isSuperAdmin(usuarioSesion.getRoles())) {
			usuarioActual = usuarioService.findById(id);
			if(usuarioActual == null) {
				response.put(MESSAGE, "El usuario con id: " + id +" No existe en el sistema");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		}
		else {
			usuarioActual = usuarioService.findByIdAndEmpresa(id,modelMapper.map(usuarioSesion.getEmpresa(), Empresa.class));
			if(usuarioActual == null) {
				response.put(MESSAGE, "El usuario con id: " + id +" No existe en la empresa");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		}
		
		try {
			for(RolDTO rolRequest: usuarioDTO.getRoles()) {
				rolDTO = rolService.findById(rolRequest.getId());
				if(rolDTO != null) {
					roles.add(rolDTO);
				}
			}
			
			usuarioActual.setNombre(usuarioDTO.getNombre());
			usuarioActual.setApellido(usuarioDTO.getApellido());
			usuarioActual.setTipoIdentificacion(usuarioDTO.getTipoIdentificacion());
			usuarioActual.setIdentificacion(usuarioDTO.getIdentificacion());
			usuarioActual.setUsername(usuarioDTO.getUsername());
			usuarioActual.setPassword(usuarioDTO.getPassword());
			usuarioActual.setEnabled(usuarioDTO.isEnabled());
			usuarioActual.setEmpresa(usuarioDTO.getEmpresa());
			usuarioActual.setRoles(roles);
			
			usuarioActualizado = usuarioService.save(usuarioActual);	
			
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo actualizar el itinerario en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put(MESSAGE, "El usuario ha sido actualizado exitosamente!");
		response.put("usuario", usuarioActualizado);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id, Authentication authentication) {
		
		Map<String, Object> response = new HashMap<>();
		UsuarioDTO usuarioDTO;
		UsuarioDTO usuarioEliminar = null;
		UsuarioDTO usuarioSesion = usuarioService.findByUsername(authentication.getName());
		
		try {
			if(isSuperAdmin(usuarioSesion.getRoles())) {
				usuarioDTO = usuarioService.findById(id);
				if(usuarioDTO != null) {
					usuarioEliminar = asignarUsuario(usuarioDTO.getEmpresa().getUsuarios(), usuarioDTO.getId());
					if(usuarioEliminar != null) {
						usuarioDTO.getEmpresa().getUsuarios().remove(usuarioEliminar);
						empresaService.save(usuarioDTO.getEmpresa());
						response.put(MESSAGE, "El usuario ha sido eliminado exitosamente!");
					}
				}
				else {
					response.put(MESSAGE, "El usuario con id: " + id +" No existe en el sistema");
					return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
				}
			}
			else {
				usuarioDTO = usuarioService.findByIdAndEmpresa(id,modelMapper.map(usuarioSesion.getEmpresa(), Empresa.class));
				if(usuarioDTO != null) {
					usuarioEliminar = asignarUsuario(usuarioDTO.getEmpresa().getUsuarios(), usuarioDTO.getId());
					if(usuarioEliminar != null) {
						usuarioDTO.getEmpresa().getUsuarios().remove(usuarioEliminar);
						empresaService.save(usuarioDTO.getEmpresa());
						response.put(MESSAGE, "El usuario ha sido eliminado exitosamente!");
					}
				}
				else {
					response.put(MESSAGE, "El usuario con id: " + id +" No existe en esta empresa");
					return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
				}
			}
				
		} catch (DataAccessException e) {
			response.put(MESSAGE, "No se pudo eliminar el usuario en la base de datos");
			response.put(ERROR, e.getMessage() +": "+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	private boolean isSuperAdmin(List<RolDTO> roles) {
		List<RolDTO> rolesAsignados = roles;
		boolean esSuperadministrador = false;
		
		for (RolDTO rol : rolesAsignados) {
			if (rol.getNombre().equals("ROLE_SUPERADMINISTRADOR")) {
		        esSuperadministrador = true;
		    }
		}
		return esSuperadministrador;
	}
	
	private UsuarioDTO asignarUsuario(List<UsuarioDTO> usuarios, Long usuarioId) {
		UsuarioDTO usuarioEliminar = null;
		for(UsuarioDTO usuario: usuarios) {
			if(usuario.getId().equals(usuarioId)) {
				usuarioEliminar = usuario;
				break;
			}
		}
		return usuarioEliminar;
	}
}
