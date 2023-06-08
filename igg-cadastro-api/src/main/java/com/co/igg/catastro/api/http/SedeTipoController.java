package com.co.igg.catastro.api.http;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.co.igg.catastro.api.service.IAdminService;
import com.co.igg.catastro.common.models.SedeTipo;
import com.co.igg.catastro.api.Constants;

@CrossOrigin(origins = {Constants.API_URL})
@RestController
@RequestMapping("/sede-tipo")
public class SedeTipoController {
	@Autowired
    private IAdminService adminService;
	
	@RequestMapping( value = {"/",""}, method = RequestMethod.GET )
	public List<SedeTipo> getAllSedesTipos(){
		return adminService.findAllSedeTipo();
	}
}
