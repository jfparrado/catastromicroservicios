package com.co.igg.catastro.oauth.services;

import com.co.igg.catastro.common.models.Usuario;

public interface IUsuarioService {
	
	public Usuario findByUsername(String username);
	
	public Usuario update(Usuario usuario, Long id);
	
}
