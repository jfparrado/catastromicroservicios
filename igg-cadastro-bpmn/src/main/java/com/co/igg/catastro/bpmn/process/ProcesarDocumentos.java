package com.co.igg.catastro.bpmn.process;

import com.co.igg.catastro.bpmn.services.CmisService;

import org.apache.chemistry.opencmis.client.api.Document;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcesarDocumentos implements JavaDelegate {
	private static Logger log = LoggerFactory.getLogger(ProcesarDocumentos.class);

	@Autowired
	private CmisService cmisService;
	
	@Override
	public void execute(DelegateExecution ctx) throws Exception {
		
		log.info("--Creating documents...");
		//CmisService cmisServ = new CmisService();
		Integer solicitud = (Integer) ctx.getVariable("idSolicitud");
		String documentos = (String) ctx.getVariable("documento");
		String[] docs = documentos.split(",");
		
		for (String doc : docs) {
			Document docA = cmisService.createDocument(cmisService.getDocLibFolder("catastro-mutaciones", "/Mutaciones-Primera-Clase"), doc ,  solicitud);	
		}
        
        log.info("--Document Created...");
	}

}
