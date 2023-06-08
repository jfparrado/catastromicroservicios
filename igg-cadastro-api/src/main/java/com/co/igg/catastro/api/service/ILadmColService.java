package com.co.igg.catastro.api.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.co.igg.catastro.common.models.LcDivipola;
import com.co.igg.catastro.common.models.LcInteresado;
import com.co.igg.catastro.common.models.LcPredio;
import com.co.igg.catastro.common.models.LcSexoTipo;

public interface ILadmColService {
	
	// Busca todos los LcInteresado paginados
	public Page<LcInteresado> findLcInteresadoAll(Pageable pageable);
	
	// Guarda un LcInteresado en la base de datos
	public void saveInteresado(LcInteresado persona);
	
	// Busca un LcInteresado por su ID
	public LcInteresado findLcInteresadoById(BigInteger id);
	
	// Busca un LcInteresado por su documento de identidad
	public LcInteresado findLcInteresadoByDocumentoIdentidad(String documentoIdentidad);
	
	// Obtiene todos los LcSexoTipo
	public List<LcSexoTipo> findLcSexoTipoAll();
	
	// Obtiene todas las LcDivipola del departamento especificado
	public List<LcDivipola> findDivipolaAll(String dpto);
	
	// Busca LcPredio según los criterios proporcionados en el mapParams
	public List<LcPredio> findPredioCriteria(Map<String, String> mapParams, Pageable pageable);
	
	// Busca un LcPredio por su número predial
	public LcPredio findPredioByNumeroPredial(String numeroPredial);
	
	// Busca un LcPredio por su matrícula inmobiliaria
	public LcPredio findPredioByMatriculaInmobiliaria(String matriculaInmobiliaria);
}
