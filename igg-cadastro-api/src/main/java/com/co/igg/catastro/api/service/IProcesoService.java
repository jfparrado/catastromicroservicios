package com.co.igg.catastro.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.co.igg.catastro.common.models.Documento;
import com.co.igg.catastro.common.models.Proceso;
import com.co.igg.catastro.common.models.ProcesoInteresado;

public interface IProcesoService {
	
	public void saveProceso(Proceso proceso);
	
	public Proceso findProcesoById(Long id);

	public Page<Proceso> findProcesoAll(Pageable pageable) ;
	
	public List<Proceso> findProcesoCriteria(Map<String, String> mapParams, Pageable pageable) ;
	
	public void saveProcesoInteresado(ProcesoInteresado procesoInteresado);
	
	public void saveDocumento(Documento documento);
	public Documento findByDsDocumento(String dsDocumento);
	
}
