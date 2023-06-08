package com.co.igg.catastro.usuarios.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.co.igg.catastro.common.models.Usuario;

@RepositoryRestResource(path="usuarios")
public interface IUsuarioDao extends PagingAndSortingRepository<Usuario, Long>{

	@RestResource(path="buscar-username")
	public Usuario findByDsUsername(@Param("dsUsername") String dsUsername);
	
	@Query("select u from Usuario u where u.dsUsername=?1")
	public Usuario getUsuarioByDsUsername(String dsUsername);
	
}
