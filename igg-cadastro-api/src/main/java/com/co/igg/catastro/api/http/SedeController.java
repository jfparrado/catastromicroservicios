package com.co.igg.catastro.api.http;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.co.igg.catastro.api.service.IAdminService;
import com.co.igg.catastro.common.models.Sede;
import com.co.igg.catastro.api.Constants;

@CrossOrigin(origins = {Constants.API_URL})
@RestController
@RequestMapping("/sede")
public class SedeController {
	@Autowired
    private IAdminService adminService;
	
	@RequestMapping( value = {"/",""}, method = RequestMethod.GET )
	public List<Sede> getAllSedes(){
		return adminService.findAllSede();
	}
}
