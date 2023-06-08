package com.co.igg.catastro.api.http.to;

import java.math.BigInteger;
import java.util.Date;

public class ProcesoTO {

	private BigInteger idProceso;
	private String nrRadicado;
	private String razonSocial;
	private String documentoIdentidad;
	private String numeroPredial;
	private String matriculaInmobiliaria;
	private Date dtCreation;
	private Date dtActualizacion;
	
	public BigInteger getIdProceso() {
		return idProceso;
	}
	
	public void setIdProceso(BigInteger idProceso) {
		this.idProceso = idProceso;
	}
	
	public String getNrRadicado() {
		return nrRadicado;
	}
	
	public void setNrRadicado(String nrRadicado) {
		this.nrRadicado = nrRadicado;
	}
	
	public String getRazonSocial() {
		return razonSocial;
	}
	
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	public String getDocumentoIdentidad() {
		return documentoIdentidad;
	}
	
	public void setDocumentoIdentidad(String documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
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
	
	public Date getDtCreation() {
		return dtCreation;
	}
	
	public void setDtCreation(Date dtCreation) {
		this.dtCreation = dtCreation;
	}
	
	public Date getDtActualizacion() {
		return dtActualizacion;
	}
	
	public void setDtActualizacion(Date dtActualizacion) {
		this.dtActualizacion = dtActualizacion;
	}
	
}
