package com.co.igg.catastro.api.http;

import java.lang.System;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.co.igg.catastro.bpmn;
import com.co.igg.catastro.bpmn.services.ProcessService;
import com.co.igg.catastro.api.service.IAuthService;
import com.co.igg.catastro.api.service.ILadmColService;
import com.co.igg.catastro.api.service.IProcesoService;
import com.co.igg.catastro.api.utils.PdfUtil;
import com.co.igg.catastro.common.models.LcInteresado;
import com.co.igg.catastro.common.models.MutacionClase;
import com.co.igg.catastro.common.models.MutacionTipo;
import com.co.igg.catastro.common.models.Proceso;
import com.co.igg.catastro.common.models.Usuario;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.DocumentException;
import com.qoppa.pdf.PDFException;

import feign.Feign;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import com.co.igg.catastro.api.http.to.FormProceso;
import com.co.igg.catastro.api.rest.BpmnClienteRest;
import com.co.igg.catastro.api.Constants;

@CrossOrigin(origins = { Constants.API_URL })
@RestController
@RequestMapping("/proceso")
public class ProcesoController {

	private static BpmnClienteRest client = Feign.builder().target(BpmnClienteRest.class, Constants.API_URL);
	private static ObjectMapper mapper = new ObjectMapper();
	private static PdfUtil pdfUtil = new PdfUtil();
	@Autowired
	private IProcesoService procesoService;

	@Autowired
	private ILadmColService lcService;

	@Autowired
	private IAuthService authService;

	@GetMapping("/{id}")
	public Proceso show(@PathVariable Long id) {
		return this.procesoService.findProcesoById(id);
	}

	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public Page<Proceso> getProcesoAll(Pageable pageable) {
		return procesoService.findProcesoAll(pageable);
	}

	@RequestMapping(value = { "/search" }, method = RequestMethod.GET)
	public List<Proceso> getProcesoSearch(@RequestParam Map<String, String> mapParams, Pageable pageable) {
		System.out.println("mapParams: " + mapParams);
		System.out.println("pageable: " + pageable);
		return procesoService.findProcesoCriteria(mapParams, pageable);
	}

	@PostMapping(value = "/process/{idProceso}/asignacion/{idUsuario}")
	public Proceso updateUsuarioAsignacion(@PathVariable Map<String, String> mapMsg) throws IOException {
		Proceso currentProceso = this.procesoService.findProcesoById(Long.parseLong(mapMsg.get("idProceso")));
		Usuario user = new Usuario(Long.parseLong(mapMsg.get("idUsuario")));
		currentProceso.setUsuarioAsignacion(user);

		/* CAPTURANDO LA TAREA ACTUAL DEL PROCESO */
		String respuesta = client.getTask(currentProceso.getDsReferenciaProceso());
		JsonNode actualObj = mapper.readTree(respuesta);

		String task = "";
		for (JsonNode jsonNode : actualObj) {
			task = jsonNode.get("id").asText();
		}
		/* REPORTANDO TAREA COMPLETA */
		Map<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		String msg = String.format("{ \"variables\": { \"idProceso\": { \"value\": %d } } }",
				currentProceso.getIdProceso());

		respuesta = client.taskComplete(headers, msg, task);

		/* ACTUALIZANDO EL PROCESO CON NUEVO ESTADO DE LA TAREA */
		respuesta = client.activityInstances(currentProceso.getDsReferenciaProceso());
		actualObj = mapper.readTree(respuesta);

		procesoService.saveProceso(currentProceso);
		System.out.println("Valor de currentProceso: " + currentProceso);
		return currentProceso;

	}

	@PostMapping(value = "/process")
	public Proceso updateEjecucion(
			@RequestParam("sForm") String sForm) throws IOException {

		FormProceso procesoForm = new FormProceso().getJson(sForm);
		Proceso currentProceso = this.procesoService.findProcesoById(procesoForm.getIdProceso());

		/* CAPTURANDO LA TAREA ACTUAL DEL PROCESO */
		String respuesta = client.getTask(currentProceso.getDsReferenciaProceso());
		JsonNode actualObj = mapper.readTree(respuesta);

		String task = "";
		for (JsonNode jsonNode : actualObj) {
			task = jsonNode.get("id").asText();
		}

		/* REPORTANDO TAREA COMPLETA */
		Map<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		String msg = String.format("{ \"variables\": { \"idProceso\": { \"value\": %d } } }",
				currentProceso.getIdProceso());

		respuesta = client.taskComplete(headers, msg, task);
		/* ACTUALIZANDO LA proceso CON NUEVO ESTADO DE LA TAREA */
		respuesta = client.activityInstances(currentProceso.getDsReferenciaProceso());
		actualObj = mapper.readTree(respuesta);

		procesoService.saveProceso(currentProceso);

		return currentProceso;
	}

	@PostMapping(value = "/")
	public Proceso create(
			@RequestParam("sForm") String sForm,
			@RequestParam(value = "files", required = false) MultipartFile[] files,
			MultipartHttpServletRequest mrequest) throws IOException, PDFException, DocumentException {

		FormProceso procesoForm = new FormProceso().getJson(sForm);
		// 2. Crea la Proceso
		MutacionTipo mTipo = new MutacionTipo(procesoForm.getIdMutacionTipo());
		MutacionClase mClase = new MutacionClase(procesoForm.getIdMutacionClase());
		Usuario usuario = authService.findUsuarioByDsUsername(procesoForm.getDsUsername());
		ProcessService processService = new ProcessService();
		String idProceso = processService.createProcessAndSetId();
		
		Proceso proceso = new Proceso();
		proceso.setIdProceso(idProceso);
		proceso.setMensaje("");
		proceso.setMutacionTipo(mTipo);
		proceso.setMutacionClase(mClase);
		proceso.setUsuario(usuario);
		// 2. Crea la Proceso
		this.procesoService.saveProceso(proceso);

		// Crea la Relación
		// TODO Revisar el Tema de que tipo asignar en la proceso
		/*
		 * Tipo tipoPersonaRelacion = new Tipo(3L);
		 * 
		 * ProcesoInteresado procesoInteresado = new ProcesoInteresado();
		 * procesoInteresado.setLcInteresado(interesado);
		 * procesoInteresado.setProceso(proceso);
		 * procesoInteresado.setTipoPersonaRelacion(tipoPersonaRelacion);
		 * this.procesoService.saveProcesoInteresado(procesoInteresado);
		 */
		// Preparación de los documentos de la proceso

		ObjectMapper mapper = new ObjectMapper();

		JsonNode node = mapper.valueToTree(procesoForm);
		// Map<String, Object> headers = new HashMap<>();
		// headers.put("Content-Type", "application/json");
		// String msg = String.format("{ \"variables\": { \"idProceso\": { \"value\":
		// %d, \"type\": \"Integer\" }} }");

		// /*CAPTURANDO EL PROCESO*/
		// String respuesta = client.startProcess(headers, msg, "ProcessProceso");
		// JsonNode actualObj = mapper.readTree(respuesta);
		// proceso.setDsReferenciaProceso(actualObj.get("id").asText());
		// System.out.println("llegue a 4");
		// /*CAPTURANDO LA TAREA*/
		// respuesta = client.activityInstances(actualObj.get("id").asText());
		// actualObj = mapper.readTree(respuesta);

		System.out.println("llegue a 5");
		proceso = this.procesoService.findProcesoById(proceso.getIdProceso());

		this.procesoService.saveProceso(proceso);

		// System.out.println(respuesta);

		return proceso;

	}

	@PutMapping("/view/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Proceso update(@RequestBody FormProceso formProceso, @PathVariable Long id) {
		Proceso currentProceso = this.procesoService.findProcesoById(id);
		this.procesoService.saveProceso(currentProceso);
		return currentProceso;
	}

}
