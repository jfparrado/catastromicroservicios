package com.co.igg.catastro.common.models;

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

@Entity
@Table(name = "ct_mutacionclase_documentotipo")
public class MutacionclaseDocumentotipo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_mutacionclase_documentotipo")
	private Long idMutacionclaseDocumentotipo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_mutacion_clase")
	@JsonBackReference
	private MutacionClase mutacionClase;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_documento_tipo")
	private DocumentoTipo documentoTipo;
	
	private Boolean flObligatorio;

	public Long getIdMutacionclaseDocumentotipo() {
		return idMutacionclaseDocumentotipo;
	}

	public void setIdMutacionclaseDocumentotipo(Long idMutacionclaseDocumentotipo) {
		this.idMutacionclaseDocumentotipo = idMutacionclaseDocumentotipo;
	}

	public MutacionClase getMutacionClase() {
		return mutacionClase;
	}

	public void setMutacionClase(MutacionClase mutacionClase) {
		this.mutacionClase = mutacionClase;
	}

	public DocumentoTipo getDocumentoTipo() {
		return documentoTipo;
	}

	public void setDocumentoTipo(DocumentoTipo documentoTipo) {
		this.documentoTipo = documentoTipo;
	}

	public Boolean getFlObligatorio() {
		return flObligatorio;
	}

	public void setFlObligatorio(Boolean flObligatorio) {
		this.flObligatorio = flObligatorio;
	}
	
	
}
