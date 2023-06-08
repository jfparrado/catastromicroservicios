package com.co.igg.catastro.api.http;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.igg.catastro.api.service.ILadmColService;
import com.co.igg.catastro.common.models.LcPredio;
import com.co.igg.catastro.api.Constants;

@CrossOrigin(origins = {Constants.API_URL})
@RestController
@RequestMapping("/predio")
public class LcPredioController {

	@Autowired
	private ILadmColService ladmColService;
	
	@RequestMapping( value = {"/search"}, method = RequestMethod.GET)
	public List<LcPredio> getSolicitudSearch(@RequestParam Map<String,String> mapParams, Pageable pageable ){
		return ladmColService.findPredioCriteria(mapParams, pageable);
	}
	
	@RequestMapping(value = {
			"/numeroPredial/{numeroPredial}",
			"/matriculaInmobiliaria/{matriculaInmobiliaria}"
		}, method = RequestMethod.GET)
	public LcPredio getPredio(@PathVariable Map<String, String> mapParam){
		if(mapParam.get("matriculaInmobiliaria")!=null)
			return ladmColService.findPredioByMatriculaInmobiliaria(mapParam.get("matriculaInmobiliaria"));
		else if(mapParam.get("numeroPredial")!=null)
			return ladmColService.findPredioByNumeroPredial(mapParam.get("numeroPredial"));
		return new LcPredio();	
	}
	
}
