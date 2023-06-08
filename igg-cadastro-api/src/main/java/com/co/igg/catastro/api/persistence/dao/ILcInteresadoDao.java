package com.co.igg.catastro.api.persistence.dao;

import java.math.BigInteger;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.co.igg.catastro.common.models.LcInteresado;

public interface ILcInteresadoDao extends PagingAndSortingRepository<LcInteresado, BigInteger>{
	public LcInteresado findByDocumentoIdentidad(String documentoIdentidad);
}
