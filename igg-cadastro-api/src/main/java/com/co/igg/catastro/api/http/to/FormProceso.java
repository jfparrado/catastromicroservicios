package com.co.igg.catastro.api.http.to;

import java.io.IOException;
import java.util.Date;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FormProceso {
	private Long idProceso;
	private String dsNombre;
	private Long idMutacionClase;
	private Long idMutacionTipo;
	private String nrDocumento;
	private String dsUsername;
	private String fileTypes;
	private String mensaje;
	
	
	public Long getIdProceso() {
		return idProceso;
	}
	public void setIdProceso(Long idProceso) {
		this.idProceso = idProceso;
	}

	public String getDsNombre() {
		return dsNombre;
	}
	public void setDsNombre(String dsNombre) {
		this.dsNombre = dsNombre;
	}
	public Long getIdMutacionClase() {
		return idMutacionClase;
	}
	public void setIdMutacionClase(Long idMutacionClase) {
		this.idMutacionClase = idMutacionClase;
	}
	public Long getIdMutacionTipo() {
		return idMutacionTipo;
	}
	public void setIdMutacionTipo(Long idMutacionTipo) {
		this.idMutacionTipo = idMutacionTipo;
	}
	public String getNrDocumento() {
		return nrDocumento;
	}
	public void setNrDocumento(String nrDocumento) {
		this.nrDocumento = nrDocumento;
	}
	
	public String getDsUsername() {
		return dsUsername;
	}
	public void setDsUsername(String dsUsername) {
		this.dsUsername = dsUsername;
	}

	public String getFileTypes() {
		return fileTypes;
	}
	public void setFileTypes(String fileTypes) {
		this.fileTypes = fileTypes;
	}
	public FormProceso getJson(String json){
		FormProceso sForm = new FormProceso();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			sForm = objectMapper.readValue(json, FormProceso.class);
		} catch (IOException err) {
			System.out.printf("Error", err.toString());
		}
		return sForm;
	}
		
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
