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

@Entity
@Table(name = "ct_checklist_documento")
public class ChecklistDocumento {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_checklist_documento")
	private Long idCheckListDocumento;
	
	@Column(name="ds_checklist_documento")
	private String dsChecklistDocumento;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_documento_tipo")
	@JsonBackReference
	private DocumentoTipo documentoTipo;

	public Long getIdCheckListDocumento() {
		return idCheckListDocumento;
	}

	public void setIdCheckListDocumento(Long idCheckListDocumento) {
		this.idCheckListDocumento = idCheckListDocumento;
	}

	public String getDsChecklistDocumento() {
		return dsChecklistDocumento;
	}

	public void setDsChecklistDocumento(String dsChecklistDocumento) {
		this.dsChecklistDocumento = dsChecklistDocumento;
	}

	public DocumentoTipo getDocumentoTipo() {
		return documentoTipo;
	}

	public void setDocumentoTipo(DocumentoTipo documentoTipo) {
		this.documentoTipo = documentoTipo;
	}

	
	
	
}
