package com.co.igg.catastro.api.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.igg.catastro.api.persistence.dao.IUsuarioDao;
import com.co.igg.catastro.api.service.IAuthService;
import com.co.igg.catastro.common.models.Usuario;

@Service
public class AuthServiceImpl implements IAuthService{
	@Autowired
	private IUsuarioDao usuarioDao;
	
	public Usuario findUsuarioByDsUsername(String dsUsername){
		return usuarioDao.getUsuarioByDsUsername(dsUsername);
	}
	
	public List<Usuario> findAllUsuarios(){
		return (List<Usuario>) usuarioDao.findAll();
	}
	
}
