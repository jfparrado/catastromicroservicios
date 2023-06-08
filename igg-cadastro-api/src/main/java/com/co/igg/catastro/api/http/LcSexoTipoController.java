package com.co.igg.catastro.api.http;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.co.igg.catastro.api.service.ILadmColService;
import com.co.igg.catastro.common.models.LcSexoTipo;
import com.co.igg.catastro.api.Constants;

@CrossOrigin(origins = {Constants.API_URL})
@RestController
@RequestMapping("/lcSexoTipo")
public class LcSexoTipoController {
	@Autowired
    private ILadmColService lcService;
	
	@RequestMapping( value = {"/",""}, method = RequestMethod.GET )
	public List<LcSexoTipo> getAllSedes(){
		return lcService.findLcSexoTipoAll();
	}
	
}
