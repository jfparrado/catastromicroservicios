package com.co.igg.catastro.api.persistence.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.co.igg.catastro.common.models.Sede;

public interface ISedeDao  extends PagingAndSortingRepository<Sede, Long>{

}
