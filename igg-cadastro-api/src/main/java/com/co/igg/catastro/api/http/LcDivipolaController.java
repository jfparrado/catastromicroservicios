package com.co.igg.catastro.api.http;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.co.igg.catastro.api.service.ILadmColService;
import com.co.igg.catastro.common.models.LcDivipola;

@RestController
@RequestMapping("/divipola")
public class LcDivipolaController {
	
	@Autowired
	private ILadmColService ladmColService;
	
	@RequestMapping( value = {"/{divipola}", ""}, method = RequestMethod.GET )
	public List<LcDivipola> getDepartamentos( @PathVariable String divipola ){
		return ladmColService.findDivipolaAll(divipola);
	}
	
}
