package com.co.igg.catastro.api.http;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.co.igg.catastro.api.service.IAuthService;
import com.co.igg.catastro.common.models.Solicitud;
import com.co.igg.catastro.common.models.Usuario;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/usuario")
public class UsuarioController{
	
	@Autowired
	private IAuthService authService;
	
	@RequestMapping( value = {"/", ""}, method = RequestMethod.GET)
	public List<Usuario> getSolicitudAll( Pageable pageable ){
		return authService.findAllUsuarios();
	}
	
}
