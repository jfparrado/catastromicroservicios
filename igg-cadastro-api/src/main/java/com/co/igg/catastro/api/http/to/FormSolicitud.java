package com.co.igg.catastro.api.http.to;

import java.io.IOException;
import java.util.Date;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FormSolicitud {
	private Long idSolicitud;
	private Date dtFechaRadicacion;
	private String dsNombre;
	private Long idMutacionClase;
	private Long idMutacionTipo;
	private Long idSede;
	private Long idUsuarioAsignacion;
	private String nrDocumento;
	private String dsUsername;
	private String numeroPredial;
	private String matriculaInmobiliaria;
	private String idDepartamento;
	private String idMunicipio;
	private String fileTypes;
	private Integer estado;
	private String observacion;
	
	
	
	public Long getIdSolicitud() {
		return idSolicitud;
	}
	public void setIdSolicitud(Long idSolicitud) {
		this.idSolicitud = idSolicitud;
	}
	public Date getDtFechaRadicacion() {
		return dtFechaRadicacion;
	}
	public void setDtFechaRadicacion(Date dtFechaRadicacion) {
		this.dtFechaRadicacion = dtFechaRadicacion;
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
	public Long getIdSede() {
		return idSede;
	}
	public void setIdSede(Long idSede) {
		this.idSede = idSede;
	}
	
	
	public String getNumeroPredial() {
		return numeroPredial;
	}
	public void setNumeroPredial(String numeroPredial) {
		this.numeroPredial = numeroPredial;
	}
	public String getMatriculaInmobiliaria() {
		return matriculaInmobiliaria;
	}
	public void setMatriculaInmobiliaria(String matriculaInmobiliaria) {
		this.matriculaInmobiliaria = matriculaInmobiliaria;
	}
	public String getIdDepartamento() {
		return idDepartamento;
	}
	public void setIdDepartamento(String idDepartamento) {
		this.idDepartamento = idDepartamento;
	}
	public String getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(String idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	
	public Long getIdUsuarioAsignacion() {
		return idUsuarioAsignacion;
	}
	public void setIdUsuarioAsignacion(Long idUsuarioAsignacion) {
		this.idUsuarioAsignacion = idUsuarioAsignacion;
	}
	
	
	public Integer getEstado() {
		return estado;
	}
	public void setEstado(Integer estado) {
		this.estado = estado;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getFileTypes() {
		return fileTypes;
	}
	public void setFileTypes(String fileTypes) {
		this.fileTypes = fileTypes;
	}
	public FormSolicitud getJson(String json){
		FormSolicitud sForm = new FormSolicitud();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			sForm = objectMapper.readValue(json, FormSolicitud.class);
		} catch (IOException err) {
			System.out.printf("Error", err.toString());
		}
		return sForm;
	}
}
