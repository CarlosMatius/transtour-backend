package com.transtour.backend.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.transtour.backend.models.dto.UsuarioDTO;
import com.transtour.backend.models.services.IUsuarioService;

@Component
public class InfoAdditionalToken implements TokenEnhancer{
	
	@Autowired
	private IUsuarioService usuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		UsuarioDTO usuario = usuarioService.findByUsername(authentication.getName());

		Map<String, Object> info = new HashMap<>();
		info.put("nombre", usuario.getNombre() + " " + usuario.getApellido());
		info.put("empresa", usuario.getEmpresa() != null ? usuario.getEmpresa().getNombre() : "superadministrador");
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}

}
