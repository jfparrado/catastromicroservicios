package com.co.igg.catastro.api.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.co.igg.catastro.common.models.LcDivipola;

public interface ILcDivipolaDao extends PagingAndSortingRepository<LcDivipola, String>{

	@Query(value="select d.* from lc_divipola d where d.divipola_id=?1", nativeQuery = true)
	public List<LcDivipola> findAllByDivipola(String divipola);
	
}
