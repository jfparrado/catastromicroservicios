package com.co.igg.catastro.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.co.igg.catastro.common.models.Documento;
import com.co.igg.catastro.common.models.Solicitud;
import com.co.igg.catastro.common.models.SolicitudInteresado;

public interface ISolicitudService {
	
	public void saveSolicitud(Solicitud solicitud);
	
	public Solicitud findSolicitudById(Long id);

	public Page<Solicitud> findSolicitudAll(Pageable pageable) ;
	
	public List<Solicitud> findSolicitudCriteria(Map<String, String> mapParams, Pageable pageable) ;
	
	public void saveSolicitudInteresado(SolicitudInteresado solicitudInteresado);
	
	public void saveDocumento(Documento documento);
	public Documento findByDsDocumento(String dsDocumento);
	
}
