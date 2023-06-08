package com.co.igg.catastro.api.persistence.dao;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.co.igg.catastro.common.models.Proceso;

public interface IProcesoDao  extends PagingAndSortingRepository<Proceso, Long> ,JpaSpecificationExecutor<Proceso>{
	
	
}
