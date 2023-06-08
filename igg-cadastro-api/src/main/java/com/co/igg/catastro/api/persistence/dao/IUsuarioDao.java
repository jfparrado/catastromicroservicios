package com.co.igg.catastro.api.persistence.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.co.igg.catastro.common.models.Usuario;

public interface IUsuarioDao extends PagingAndSortingRepository<Usuario, Long>{

	@Query("select u from Usuario u where u.dsUsername=?1")
	public Usuario getUsuarioByDsUsername(String dsUsername);
	
}
