package com.co.igg.catastro.api.persistence.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.co.igg.catastro.common.models.MutacionClase;

public interface IMutacionClaseDao  extends PagingAndSortingRepository<MutacionClase, Long> {

}
