package com.co.igg.catastro.api.service.impl;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.co.igg.catastro.api.persistence.dao.IDocumentoDao;
import com.co.igg.catastro.api.persistence.dao.ISolicitudDao;
import com.co.igg.catastro.api.persistence.dao.ISolicitudInteresadoDao;
import com.co.igg.catastro.api.service.ISolicitudService;
import com.co.igg.catastro.common.models.Documento;
import com.co.igg.catastro.common.models.Solicitud;
import com.co.igg.catastro.common.models.SolicitudInteresado;

@Service
public class SolicitudServiceImpl implements ISolicitudService{//implementara lo descrito en ISolicitudService
	
	@Autowired
	private ISolicitudDao solicitudDao;
	
	@Autowired
	private ISolicitudInteresadoDao solicitudInteresadoDao;

	@Autowired
	private IDocumentoDao documentoDao;
	
	//De la Solicitud
	@Transactional
	public void saveSolicitud(Solicitud solicitud) {
		solicitudDao.save(solicitud);
	}
	
	@Transactional(readOnly = true)
	public Solicitud findSolicitudById(Long id) {
		return solicitudDao.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public Page<Solicitud> findSolicitudAll(Pageable pageable) {
		return solicitudDao.findAll(pageable);
	}

	public List<Solicitud> findSolicitudCriteria(Map<String, String> mapParams, Pageable pageable) {
		
		Page page = solicitudDao.findAll(new Specification<Solicitud>() {
            @Override
            public Predicate toPredicate(Root<Solicitud> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(mapParams.get("nrDocumento")!=null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("lcInteresado").get("documentoIdentidad"), mapParams.get("nrDocumento"))));
                }
                if(mapParams.get("razonSocial")!=null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("lcInteresado").get("razonSocial"), mapParams.get("razonSocial"))));
                }
                if(mapParams.get("matriculaInmobiliaria")!=null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("lcPredio").get("matriculaInmobiliaria"), mapParams.get("matriculaInmobiliaria"))));
                }
                if(mapParams.get("numeroPredial")!=null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("lcPredio").get("numeroPredial"), mapParams.get("numeroPredial"))));
                }
                if(mapParams.get("nrRadicado")!=null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("nrRadicado"), mapParams.get("nrRadicado"))));
                }
                if(mapParams.get("dsActivityName")!=null) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("dsActivityName"), mapParams.get("dsActivityName"))));
                }
                
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, pageable);
		
        page.getTotalElements();        // get total elements
        page.getTotalPages();           // get total pages
        
        return page.getContent();       // get List of Employee
        
	}

	
	//De la Solicitud para una Persona
	@Transactional
	public void saveSolicitudInteresado(SolicitudInteresado solicitudInteresado) {
		solicitudInteresadoDao.save(solicitudInteresado);
	}
	
	@Transactional
	public void saveDocumento(Documento documento) {
		documentoDao.save(documento);
	}
	
	@Transactional(readOnly = true)
	public Documento findByDsDocumento(String dsDocumento) {
		return documentoDao.findByDsDocumento(dsDocumento);
	}
}
