package com.co.igg.catastro.api.service;

import java.util.List;

import com.co.igg.catastro.common.models.*;

public interface IAdminService {

	public List<MutacionClase> findAllMutacionClase() ;
	public List<MutacionTipo> findAllMutacionTipo();
	public List<Sede> findAllSede();
	public List<SedeTipo> findAllSedeTipo();
	public List<PaisCiudad> findAllPaisCiudad();
	public List<GrupoMinoritario> findAllGrupoMinoritario();
	
}
