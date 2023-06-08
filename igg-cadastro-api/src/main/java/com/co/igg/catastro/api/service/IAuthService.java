package com.co.igg.catastro.api.service;
import java.util.List;

import com.co.igg.catastro.common.models.Usuario;

public interface IAuthService {
	public Usuario findUsuarioByDsUsername(String dsUsername);
	public List<Usuario> findAllUsuarios();
}
