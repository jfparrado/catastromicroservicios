package com.co.igg.catastro.api.service;

import java.util.List;

import com.co.igg.catastro.common.models.MutacionClase;
import com.co.igg.catastro.common.models.MutacionTipo;

public interface IMutacionService {

	public List<MutacionClase> findAllMutacionClase() ;
	public List<MutacionTipo> findAllMutacionTipo();
	
}
