package com.co.igg.catastro.common.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ct_documento")
public class Documento {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_documento")
	private Long idDocumento;
	
	@Column(name="ds_documento")
	private String dsDocumento;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_solicitud")
	@JsonBackReference
	private Solicitud solicitud;

	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dtCreacion ;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dtActualizacion ;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_documento_tipo")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	DocumentoTipo documentoTipo;

	public String getDsDocumento() {
		return dsDocumento;
	}

	public void setDsDocumento(String dsDocumento) {
		this.dsDocumento = dsDocumento;
	}

	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
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

	public Long getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Long idDocumento) {
		this.idDocumento = idDocumento;
	}

	public DocumentoTipo getDocumentoTipo() {
		return documentoTipo;
	}

	public void setDocumentoTipo(DocumentoTipo documentoTipo) {
		this.documentoTipo = documentoTipo;
	}
	
	
}
