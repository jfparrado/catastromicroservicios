package com.co.igg.catastro.api.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.igg.catastro.api.persistence.dao.IMutacionClaseDao;
import com.co.igg.catastro.api.persistence.dao.IMutacionTipoDao;
import com.co.igg.catastro.api.service.IMutacionService;
import com.co.igg.catastro.common.models.MutacionClase;
import com.co.igg.catastro.common.models.MutacionTipo;

@Service
public class MutacionServiceImpl implements IMutacionService {

	@Autowired
	private IMutacionClaseDao mutacionClase;
	
	@Autowired
	private IMutacionTipoDao mutacionTipo;
	
	@Transactional
	public List<MutacionClase> findAllMutacionClase() {
		return (List<MutacionClase>) mutacionClase.findAll();
	}
	
	@Transactional
	public List<MutacionTipo> findAllMutacionTipo() {
		return (List<MutacionTipo>) mutacionTipo.findAll();
	}
	
}
