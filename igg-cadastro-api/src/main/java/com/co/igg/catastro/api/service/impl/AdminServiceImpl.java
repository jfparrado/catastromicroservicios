package com.co.igg.catastro.api.service.impl;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.igg.catastro.api.persistence.dao.*;
import com.co.igg.catastro.common.models.*;

import com.co.igg.catastro.api.service.IAdminService;

@Service
public class AdminServiceImpl implements IAdminService {
	
	@Autowired
	private IMutacionClaseDao mutacionClase;
	
	@Autowired
	private IMutacionTipoDao mutacionTipo;

	@Autowired
	private ISedeDao sede;
	
	@Autowired
	private ISedeTipoDao sedeTipo;
	
	@Autowired
	private IPaisCiudad paisCiudad;

	@Autowired
	private IGrupoMinoritario grupoMinoritario;
	
	@Transactional
	public List<MutacionClase> findAllMutacionClase() {
		return (List<MutacionClase>) mutacionClase.findAll();
	}
	
	@Transactional
	public List<MutacionTipo> findAllMutacionTipo() {
		return (List<MutacionTipo>) mutacionTipo.findAll();
	}
	
	@Transactional
	public List<Sede> findAllSede() {
		return (List<Sede>) sede.findAll();
	}
	
	@Transactional
	public List<SedeTipo> findAllSedeTipo() {
		return (List<SedeTipo>) sedeTipo.findAll();
	}
	
	@Transactional
	public List<PaisCiudad> findAllPaisCiudad() {
		return (List<PaisCiudad>) paisCiudad.findAll();
	}
	
	@Transactional
	public List<GrupoMinoritario> findAllGrupoMinoritario() {
		return (List<GrupoMinoritario>) grupoMinoritario.findAll();
	}
}
