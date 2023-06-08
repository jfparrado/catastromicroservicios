package com.co.igg.catastro.oauth.security.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.co.igg.catastro.common.models.Usuario;
import com.co.igg.catastro.oauth.services.IUsuarioService;

import feign.FeignException;

@Component
public class AuthenticationSuccessErrorHandler implements AuthenticationEventPublisher {

	private Logger log = LoggerFactory.getLogger(AuthenticationSuccessErrorHandler.class);

	@Autowired
	private IUsuarioService usuarioService;

	public void publishAuthenticationSuccess(Authentication authentication) {
		UserDetails user = (UserDetails) authentication.getPrincipal();
		String mensaje = "Success Login: " + user.getUsername();
		System.out.println(mensaje);
		log.info(mensaje);
		
		Usuario usuario = usuarioService.findByUsername(authentication.getName());
		
		if(usuario.getNrIntentos() != null && usuario.getNrIntentos() > 0) {
			usuario.setNrIntentos(0);
			usuarioService.update(usuario, usuario.getIdUsuario());
		}
		
	}

	public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
		
		String mensaje = "Error en el Login: " + exception.getMessage();
		log.error(mensaje);
		System.out.println(mensaje);

		try {
			
			StringBuilder errors = new StringBuilder();
			errors.append(mensaje);
			
			Usuario usuario = usuarioService.findByUsername(authentication.getName());
			if (usuario.getNrIntentos() == null) {
				usuario.setNrIntentos(0);
			}
			
			log.info("Intentos actual es de: " + usuario.getNrIntentos());
			
			usuario.setNrIntentos(usuario.getNrIntentos()+1);
			
			log.info("Intentos después es de: " + usuario.getNrIntentos());
			
			errors.append(" - Intentos del login: " + usuario.getNrIntentos());
			
			if(usuario.getNrIntentos() >= 3) {
				String errorMaxIntentos = String.format("El usuario %s des-habilitado por máximos intentos.", usuario.getDsUsername());
				log.error(errorMaxIntentos);
				errors.append(" - " + errorMaxIntentos);
				usuario.setFlEstatus(false);
			}
			
			usuarioService.update(usuario, usuario.getIdUsuario());
			
		} catch (FeignException e) {
			log.error(String.format("El usuario %s no existe en el sistema", authentication.getName()));
		}

	}
}
