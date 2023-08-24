package com.transtour.backend.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.transtour.backend.models.dto.RolDTO;
import com.transtour.backend.models.dto.UsuarioDTO;
import com.transtour.backend.models.services.IUsuarioService;

@Component
public class CommonUtils {
	
	@Autowired
	private IUsuarioService usuarioService;

	public boolean isSuperAdmin(String username) {
		UsuarioDTO usuarioSesion = usuarioService.findByUsername(username);
		boolean esSuperadministrador = false;
		for (RolDTO rol : usuarioSesion.getRoles()) {
			if (rol.getNombre().equals("ROLE_SUPERADMINISTRADOR")) {
		        esSuperadministrador = true;
		    }
		}
		return esSuperadministrador;
	}
	
	public UsuarioDTO infoUsuario(String username) {
		return usuarioService.findByUsername(username);
	}
}