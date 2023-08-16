package com.transtour.backend.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.transtour.backend.models.dto.UsuarioResponse;
import com.transtour.backend.models.services.IUsuarioService;

@Component
public class InfoAdditionalToken implements TokenEnhancer{
	
	@Autowired
	private IUsuarioService usuarioService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		UsuarioResponse usuario = usuarioService.findByUsername(authentication.getName());

		Map<String, Object> info = new HashMap<>();
		info.put("info aditional", "Hola que tal " + usuario.getNombre() + " " + usuario.getApellido());
		info.put("usuario Id", usuario.getId());
		//info.put("empresa Id", usuario.getEmpresa().getId());
		//info.put("nombre empresa", usuario.getEmpresa().getNombre());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}

}
