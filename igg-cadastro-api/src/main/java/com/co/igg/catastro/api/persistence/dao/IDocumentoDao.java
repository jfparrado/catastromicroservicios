package com.co.igg.catastro.api.persistence.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.co.igg.catastro.common.models.Documento;
import com.co.igg.catastro.common.models.Persona;

public interface IDocumentoDao  extends PagingAndSortingRepository<Documento, Long> {

	public Documento findByDsDocumento(String dsDocumento);
	
}
