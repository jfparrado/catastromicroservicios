package com.co.igg.catastro.api.persistence.dao;

import java.math.BigInteger;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.co.igg.catastro.common.models.Persona;

public interface IPersonaDao  extends PagingAndSortingRepository<Persona, BigInteger>{

	public Persona findByNrDocumento(String nrDocumento);
}
