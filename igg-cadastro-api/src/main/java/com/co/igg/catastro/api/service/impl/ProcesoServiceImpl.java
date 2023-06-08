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
import com.co.igg.catastro.api.persistence.dao.IProcesoDao;
import com.co.igg.catastro.api.persistence.dao.IProcesoInteresadoDao;
import com.co.igg.catastro.api.service.IProcesoService;
import com.co.igg.catastro.common.models.Documento;
import com.co.igg.catastro.common.models.Proceso;
import com.co.igg.catastro.common.models.ProcesoInteresado;

@Service
public class ProcesoServiceImpl implements IProcesoService{
	
	@Autowired
	private IProcesoDao procesoDao;
	
	@Autowired
	private IProcesoInteresadoDao procesoInteresadoDao;

	@Autowired
	private IDocumentoDao documentoDao;
	
	//De la Proceso
	@Transactional
	public void saveProceso(Proceso proceso) {
		System.out.println("proceso es:"+proceso);
		procesoDao.save(proceso);
	}
	
	@Transactional(readOnly = true)
	public Proceso findProcesoById(Long id) {
		return procesoDao.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public Page<Proceso> findProcesoAll(Pageable pageable) {
		return procesoDao.findAll(pageable);
	}

	public List<Proceso> findProcesoCriteria(Map<String, String> mapParams, Pageable pageable) {
		
		Page page = procesoDao.findAll(new Specification<Proceso>() {
            @Override
            public Predicate toPredicate(Root<Proceso> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
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

		//De la Proceso para una Persona
		@Transactional
		public void saveProcesoInteresado(ProcesoInteresado procesoInteresado) {
			procesoInteresadoDao.save(procesoInteresado);
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
