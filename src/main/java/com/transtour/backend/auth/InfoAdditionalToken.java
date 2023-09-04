package com.transtour.backend.auth;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.transtour.backend.helpers.EmpresaToken;
import com.transtour.backend.models.dto.UsuarioDTO;
import com.transtour.backend.models.services.IUsuarioService;

@Component
public class InfoAdditionalToken implements TokenEnhancer{
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		UsuarioDTO usuario = usuarioService.findByUsername(authentication.getName());

		Map<String, Object> info = new HashMap<>();
		info.put("nombre", usuario.getNombre());
		info.put("apellido", usuario.getApellido());
		info.put("empresa", usuario.getEmpresa() != null ? modelMapper.map(usuario.getEmpresa(), EmpresaToken.class) : "SUPERADMINISTRADOR");
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}
}
