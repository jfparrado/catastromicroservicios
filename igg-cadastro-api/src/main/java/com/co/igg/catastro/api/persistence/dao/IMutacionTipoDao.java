package com.co.igg.catastro.api.persistence.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.co.igg.catastro.common.models.MutacionTipo;

public interface IMutacionTipoDao   extends PagingAndSortingRepository<MutacionTipo, Long> {

}
