package com.co.igg.catastro.api.persistence.dao;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.co.igg.catastro.common.models.Solicitud;

public interface ISolicitudDao  extends PagingAndSortingRepository<Solicitud, Long> ,JpaSpecificationExecutor<Solicitud>{
	
	
}
