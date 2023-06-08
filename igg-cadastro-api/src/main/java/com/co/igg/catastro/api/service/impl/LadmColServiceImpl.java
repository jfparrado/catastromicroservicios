package com.co.igg.catastro.api.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.co.igg.catastro.api.persistence.dao.ILcDivipolaDao;
import com.co.igg.catastro.api.persistence.dao.ILcInteresadoDao;
import com.co.igg.catastro.api.persistence.dao.ILcPredioDao;
import com.co.igg.catastro.api.persistence.dao.ILcSexoTipoDao;
import com.co.igg.catastro.api.service.ILadmColService;
import com.co.igg.catastro.common.models.LcDivipola;
import com.co.igg.catastro.common.models.LcInteresado;
import com.co.igg.catastro.common.models.LcPredio;
import com.co.igg.catastro.common.models.LcSexoTipo;

@Service
public class LadmColServiceImpl  implements ILadmColService {

	@Autowired
	private ILcInteresadoDao interesadoDao;
	
	@Autowired
	private ILcSexoTipoDao sexoTipoDao;
	
	@Autowired 
	private ILcPredioDao lcPredioDao;
	
	@Autowired 
	private ILcDivipolaDao lcDivipolaDao;
	
	/* 
	 * METODIS PARA INFORMACIONES DE INTERESADOS
	 */
	
	@Transactional
	public Page<LcInteresado> findLcInteresadoAll( Pageable pageable ) {
		return (Page<LcInteresado>) interesadoDao.findAll(pageable);
	}
	
	@Transactional
	public void saveInteresado(LcInteresado persona) {
		interesadoDao.save(persona);
	}
	
	@Transactional(readOnly = true)
	public LcInteresado findLcInteresadoById(BigInteger id) {
		return interesadoDao.findById(id).orElse(null);
	}
	
	@Transactional(readOnly = true)
	public LcInteresado findLcInteresadoByDocumentoIdentidad(String documentoIdentidad) {
		return interesadoDao.findByDocumentoIdentidad(documentoIdentidad);
	}
	
	/*
	 * FIN METODOS INTERESADOS
	 */
	
	/*
	 * INICIO METODOS TIPOS
	 */
	@Transactional
	public List<LcSexoTipo> findLcSexoTipoAll() {
		return (List<LcSexoTipo>) sexoTipoDao.findAll();
	}
	
	@Transactional
	public List<LcDivipola> findDivipolaAll(String dpto) {
		return (List<LcDivipola>) lcDivipolaDao.findAllByDivipola(dpto);
	}
	
	
	/*
	 * FIN METODOS TIPOS
	 */
	
	/*
	 *  INICIO METODOS PREDIOS
	 */
	
	public LcPredio findPredioByNumeroPredial(String numeroPredial) {
		return lcPredioDao.findByNumeroPredial(numeroPredial);
	}
		
	public LcPredio findPredioByMatriculaInmobiliaria(String matriculaInmobiliaria) {
		return lcPredioDao.findByMatriculaInmobiliaria(matriculaInmobiliaria);
	}
	
	public List<LcPredio> findPredioCriteria(Map<String, String> mapParams, Pageable pageable) {
		
		Page page = lcPredioDao.findAll(new Specification<LcPredio>() {
            @Override
            public Predicate toPredicate(Root<LcPredio> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                  if(mapParams.get("matriculaInmobiliaria")!=null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("matriculaInmobiliaria"), mapParams.get("matriculaInmobiliaria"))));
                }
                if(mapParams.get("numeroPredial")!=null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("numeroPredial"), mapParams.get("numeroPredial"))));
                }
                if(mapParams.get("nrRadicado")!=null) {	
                	predicates.add(
                			 criteriaBuilder.and(
                					 criteriaBuilder.equal(root.join("solicitudes").get("nrRadicado"),mapParams.get("nrRadicado"))
                					 )
                			 );
                }
                if(mapParams.get("numeroDocumento")!=null) {	
                	predicates.add(
                			 criteriaBuilder.and(
                					 criteriaBuilder.equal(root.join("interesados").get("lcInteresado").get("documentoIdentidad"),mapParams.get("numeroDocumento"))
                					 )
                			 );
                }
                if(mapParams.get("razonSocial")!=null) {	
                	predicates.add(
                			 criteriaBuilder.and(
                					 criteriaBuilder.equal(root.join("interesados").get("lcInteresado").get("razonSocial"),mapParams.get("razonSocial"))
                					 )
                			 );
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, pageable);
		
        page.getTotalElements();        // get total elements
        page.getTotalPages();           // get total pages
        
        return page.getContent();       // get List of Employee
        
	}
}
