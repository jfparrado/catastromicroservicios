package com.co.igg.catastro.api.persistence.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.co.igg.catastro.common.models.LcPredio;

public interface ILcPredioDao  extends PagingAndSortingRepository<LcPredio, Long> ,JpaSpecificationExecutor<LcPredio>{
	
	@Query(value = "select p.* from lc_predio p where p.numero_predial=?1 limit 1", nativeQuery = true)
	public LcPredio findByNumeroPredial(String numeroPredial);
	
	@Query(value = "select p.* from lc_predio p where p.matricula_inmobiliaria=?1 limit 1", nativeQuery = true)
	public LcPredio findByMatriculaInmobiliaria(String matriculaInmobiliaria);
	
}
