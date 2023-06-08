package com.co.igg.catastro.common.models;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "ct_persona")
public class Persona {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_persona")
	private BigInteger idPersona ;
	
	@Column(name="nr_documento")
	private String nrDocumento; 
	
	@Column(name="ds_nombre")
	private String dsNombre; 
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dtCreacion ;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dtActualizacion ;

	public BigInteger getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(BigInteger idPersona) {
		this.idPersona = idPersona;
	}

	public String getNrDocumento() {
		return nrDocumento;
	}

	public void setNrDocumento(String nrDocumento) {
		this.nrDocumento = nrDocumento;
	}

	public String getDsNombre() {
		return dsNombre;
	}

	public void setDsNombre(String dsNombre) {
		this.dsNombre = dsNombre;
	}

	public Date getDtCreacion() {
		return dtCreacion;
	}

	public void setDtCreacion(Date dtCreacion) {
		this.dtCreacion = dtCreacion;
	}

	public Date getDtActualizacion() {
		return dtActualizacion;
	}

	public void setDtActualizacion(Date dtActualizacion) {
		this.dtActualizacion = dtActualizacion;
	}
	
	
}
