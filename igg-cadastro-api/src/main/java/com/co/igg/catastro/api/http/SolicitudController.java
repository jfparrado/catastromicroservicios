package com.co.igg.catastro.api.http;

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

import com.co.igg.catastro.api.service.IAuthService;
import com.co.igg.catastro.api.service.ILadmColService;
import com.co.igg.catastro.api.service.ISolicitudService;
import com.co.igg.catastro.api.utils.PdfUtil;
import com.co.igg.catastro.common.models.Documento;
import com.co.igg.catastro.common.models.DocumentoTipo;
import com.co.igg.catastro.common.models.LcDivipola;
import com.co.igg.catastro.common.models.LcInteresado;
import com.co.igg.catastro.common.models.LcPredio;
import com.co.igg.catastro.common.models.MutacionClase;
import com.co.igg.catastro.common.models.MutacionTipo;
import com.co.igg.catastro.common.models.Sede;
import com.co.igg.catastro.common.models.Solicitud;
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
import com.co.igg.catastro.api.http.to.FormSolicitud;
import com.co.igg.catastro.api.rest.BpmnClienteRest;
import com.co.igg.catastro.api.Constants;

@CrossOrigin(origins = {Constants.API_URL})
@RestController
@RequestMapping("/solicitud")
public class SolicitudController{

	private static BpmnClienteRest client = Feign.builder().target(BpmnClienteRest.class,Constants.API_URL);
	private static ObjectMapper mapper = new ObjectMapper();
	private static PdfUtil pdfUtil = new PdfUtil();
	@Autowired
	private ISolicitudService solicitudService;
	
	@Autowired
	private ILadmColService lcService;
	
	@Autowired
	private IAuthService authService;
	
	@Value("${filepath.from.fileUpload}")
	private String pathFile;
	
	@GetMapping("/{id}")
	public Solicitud show(@PathVariable Long id) {
		return this.solicitudService.findSolicitudById(id);
	}
	
	@RequestMapping( value = {"/", ""}, method = RequestMethod.GET)
	public Page<Solicitud> getSolicitudAll( Pageable pageable ){
		return solicitudService.findSolicitudAll(pageable);
	}
	
	@RequestMapping( value = {"/search"}, method = RequestMethod.GET)
	public List<Solicitud> getSolicitudSearch(@RequestParam Map<String,String> mapParams, Pageable pageable ){
		return solicitudService.findSolicitudCriteria(mapParams, pageable);
	}
	
	@PostMapping(value = "/process/{idSolicitud}/asignacion/{idUsuario}" )
	public Solicitud updateUsuarioAsignacion(@PathVariable Map<String, String> mapMsg) throws IOException{
		Solicitud currentSolicitud = this.solicitudService.findSolicitudById(Long.parseLong(mapMsg.get("idSolicitud")));
		Usuario user = new Usuario(Long.parseLong(mapMsg.get("idUsuario")));
		currentSolicitud.setUsuarioAsignacion(user);
		
		/*CAPTURANDO LA TAREA ACTUAL DEL PROCESO*/
		String respuesta = client.getTask(currentSolicitud.getDsReferenciaProceso());
		JsonNode actualObj = mapper.readTree(respuesta);
		
		String task="";
		for (JsonNode jsonNode : actualObj) {
			task =jsonNode.get("id").asText();
		}
		/*REPORTANDO TAREA COMPLETA*/
		Map<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		String msg = String.format("{ \"variables\": { \"idSolicitud\": { \"value\": %d } } }", currentSolicitud.getIdSolicitud() );
		
		respuesta = client.taskComplete(headers, msg, task);
		
		/*ACTUALIZANDO LA SOLICITUD CON NUEVO ESTADO DE LA TAREA*/
		respuesta = client.activityInstances(currentSolicitud.getDsReferenciaProceso());
		actualObj = mapper.readTree(respuesta);
		
		for (JsonNode jsonNode : actualObj.get("childActivityInstances")) {
			currentSolicitud.setDsActivityName(jsonNode.get("activityId").asText());
			currentSolicitud.setDtActivity(new Date());
		}
		
		solicitudService.saveSolicitud(currentSolicitud);
		
		return currentSolicitud;
		
	}
	
	@PostMapping(value = "/process" )
	public Solicitud updateEjecucion(
			@RequestParam("sForm") String sForm) throws IOException {
		
		FormSolicitud solicitudForm = new FormSolicitud().getJson(sForm);
		Solicitud currentSolicitud = this.solicitudService.findSolicitudById(solicitudForm.getIdSolicitud());
		
		/*CAPTURANDO LA TAREA ACTUAL DEL PROCESO*/
		String respuesta = client.getTask(currentSolicitud.getDsReferenciaProceso());
		JsonNode actualObj = mapper.readTree(respuesta);
		
		String task="";
		for (JsonNode jsonNode : actualObj) {
			task =jsonNode.get("id").asText();
		}
		
		/*REPORTANDO TAREA COMPLETA*/
		Map<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		String msg = String.format("{ \"variables\": { \"idSolicitud\": { \"value\": %d } } }", currentSolicitud.getIdSolicitud() );
		
		respuesta = client.taskComplete(headers, msg, task);
		/*ACTUALIZANDO LA SOLICITUD CON NUEVO ESTADO DE LA TAREA*/
		respuesta = client.activityInstances(currentSolicitud.getDsReferenciaProceso());
		actualObj = mapper.readTree(respuesta);
		
		for (JsonNode jsonNode : actualObj.get("childActivityInstances")) {
			currentSolicitud.setDsActivityName(jsonNode.get("activityId").asText());
			currentSolicitud.setDtActivity(new Date());
		}
		
		solicitudService.saveSolicitud(currentSolicitud);
		
		return currentSolicitud;
	}
	
			
	// Anotación que indica que este método se utilizará para manejar las solicitudes POST a la ruta "/"
@PostMapping(value = "/")
public Solicitud create(
        // Parámetro recibido desde la solicitud HTTP, se espera que se proporcione como un parámetro de consulta llamado "sForm"
        @RequestParam("sForm") String sForm, 
        
        // Parámetro recibido desde la solicitud HTTP, se espera que sea un array de archivos llamado "files". 
        // Es opcional y se utiliza la anotación @RequestParam para indicar eso.
        @RequestParam(value="files", required=false) MultipartFile[] files, 
        
        // Objeto MultipartHttpServletRequest que permite acceder a los detalles de la solicitud multipart, incluyendo los archivos adjuntos
        MultipartHttpServletRequest mrequest
        ) throws IOException, PDFException, DocumentException  {
    
		// Se crea una instancia de la clase FormSolicitud y se llama al método getJson() para obtener una instancia de FormSolicitud a partir de la cadena "sForm"
		FormSolicitud solicitudForm = new FormSolicitud().getJson(sForm);
		
		//1. Busca la persona por el NrDocumento, si no existe crea la persona
		LcInteresado interesado = this.lcService.findLcInteresadoByDocumentoIdentidad(solicitudForm.getNrDocumento());
		if(interesado == null ) {
			interesado = new LcInteresado();
			interesado.setDocumentoIdentidad(solicitudForm.getNrDocumento());
			interesado.setRazonSocial(solicitudForm.getDsNombre());
			this.lcService.saveInteresado(interesado);
		}
		
		//2. Crea la Solicitud
		MutacionTipo mTipo = new MutacionTipo(solicitudForm.getIdMutacionTipo());
		MutacionClase mClase = new MutacionClase(solicitudForm.getIdMutacionClase());
		Sede mSede = new Sede(solicitudForm.getIdSede());
		LcDivipola divipola = new LcDivipola(solicitudForm.getIdMunicipio());
		
		Usuario usuario = authService.findUsuarioByDsUsername(solicitudForm.getDsUsername());
		LcPredio predio = lcService.findPredioByNumeroPredial(solicitudForm.getNumeroPredial());
		
		//TODO Pendiente Grabar los Grupos Minoritarios
		
		Solicitud solicitud = new Solicitud();
		solicitud.setDtCreacion(new Date());
		solicitud.setDtActualizacion(new Date());
		
		solicitud.setNrPredial(solicitudForm.getNumeroPredial());
		solicitud.setNrMariculaInmobilidaria(solicitudForm.getMatriculaInmobiliaria());
		
		solicitud.setMutacionTipo(mTipo);
		solicitud.setMutacionClase(mClase);
		solicitud.setLcDivipola(divipola);
		solicitud.setSede(mSede);
		solicitud.setUsuario(usuario);
		solicitud.setLcInteresado(interesado);
		solicitud.setLcPredio(predio);
		
		this.solicitudService.saveSolicitud(solicitud);
		
		//Crea la Relación
		//TODO Revisar el Tema de que tipo asignar en la solicitud
		/*Tipo tipoPersonaRelacion = new Tipo(3L);
		
		SolicitudInteresado solicitudInteresado = new SolicitudInteresado();
		solicitudInteresado.setLcInteresado(interesado);
		solicitudInteresado.setSolicitud(solicitud);
		solicitudInteresado.setTipoPersonaRelacion(tipoPersonaRelacion);
		this.solicitudService.saveSolicitudInteresado(solicitudInteresado);
		*/
		// Preparación de los documentos de la solicitud
		List<MultipartFile> multipartFiles = mrequest.getFiles("files[]");
		
		List<String> fileNames = new ArrayList<String>();
        if (null != multipartFiles && multipartFiles.size() > 0) 
        {
        	String[] fileType = solicitudForm.getFileTypes().split(",");
        	int indexF =0;
            for (MultipartFile multipartFile : multipartFiles) {
            	
                String fileName = multipartFile.getOriginalFilename();
                fileNames.add(fileName);
                
                Documento doc  = this.solicitudService.findByDsDocumento(fileName);
                
        		if(doc == null ) {
        			DocumentoTipo docTipo = new DocumentoTipo(Long.parseLong(fileType[indexF]));
        			doc = new Documento();
        			doc.setDsDocumento(String.format("_%s",fileName));
                    doc.setSolicitud(solicitud);
                    doc.setDtCreacion(new Date());
                    doc.setDtActualizacion(new Date());
                    doc.setDocumentoTipo(docTipo);
                    solicitudService.saveDocumento(doc);
                }
        		else {
        			doc.setDtActualizacion(new Date());
        			solicitudService.saveDocumento(doc);
        		}
                
                File file = new File(this.pathFile, fileName);
                try{
                    multipartFile.transferTo(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                pdfUtil.processPDF3(
                		String.format("%s/%s", this.pathFile, fileName), 
                		String.format("%s/_%s", this.pathFile, fileName) 
                	);
                
                indexF++;
            }
        }
        String filesNames = fileNames.stream().collect(Collectors.joining(","));
		
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode node = mapper.valueToTree(solicitudForm);
		
		Map<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		String msg = String.format("{ \"variables\": { \"idSolicitud\": { \"value\": %d, \"type\": \"Integer\" }, \"documento\": { \"value\": \"%s\", \"type\": \"String\" }  } }", solicitud.getIdSolicitud(), filesNames );
		
		/*CAPTURANDO EL PROCESO*/
		String respuesta = client.startProcess(headers, msg, "ProcessSolicitud");
		JsonNode actualObj = mapper.readTree(respuesta);
		solicitud.setDsReferenciaProceso(actualObj.get("id").asText());
		
		/*CAPTURANDO LA TAREA*/
		respuesta = client.activityInstances(actualObj.get("id").asText());
		actualObj = mapper.readTree(respuesta);
		
		for (JsonNode jsonNode : actualObj.get("childActivityInstances")) {
			solicitud.setDsActivityName(jsonNode.get("activityId").asText());
			solicitud.setDtActivity(new Date());
		}
		
		solicitud=this.solicitudService.findSolicitudById(solicitud.getIdSolicitud());
		
		this.solicitudService.saveSolicitud(solicitud);
		
		System.out.println(respuesta);
		
		return solicitud;
		
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Solicitud update(@RequestBody FormSolicitud formSolicitud, @PathVariable Long id) {
		Solicitud currentSolicitud = this.solicitudService.findSolicitudById(id);
		currentSolicitud.setDtActualizacion(new Date());
		this.solicitudService.saveSolicitud(currentSolicitud);
		return currentSolicitud;
	}
	
}
