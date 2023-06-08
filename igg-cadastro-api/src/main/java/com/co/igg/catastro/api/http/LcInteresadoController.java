package com.co.igg.catastro.api.http;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.co.igg.catastro.api.service.ILadmColService;
import com.co.igg.catastro.common.models.LcInteresado;
import com.co.igg.catastro.api.Constants;

@CrossOrigin(origins = {Constants.API_URL})
@RestController
@RequestMapping("/lcInteresado")
public class LcInteresadoController {
	@Autowired
	private ILadmColService lcInteresadoService;
	
	@RequestMapping( value = {"/search/documentoIdentidad/{documentoIdentidad}"}, method = RequestMethod.GET )
	public LcInteresado getLcInteresadoByDocumento( @PathVariable Map<String, String> mapParams ){
		return lcInteresadoService.findLcInteresadoByDocumentoIdentidad(mapParams.get("documentoIdentidad"));
	}
	
	@RequestMapping( value = {"/", ""}, method = RequestMethod.GET)
	public Page<LcInteresado> getLcInteresadoAll( Pageable pageable ){
		return lcInteresadoService.findLcInteresadoAll(pageable);
	}
}
