package com.co.igg.catastro.common.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ct_documento_tipo")
public class DocumentoTipo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_documento_tipo")
	private Long idDocumentoTipo;
	
	@Column(name="ds_documento_tipo")
	private String dsDocumentoTipo;

	@OneToMany(mappedBy="documentoTipo")
    private List<ChecklistDocumento> checklist;
	
	public Long getIdDocumentoTipo() {
		return idDocumentoTipo;
	}

	public void setIdDocumentoTipo(Long idDocumentoTipo) {
		this.idDocumentoTipo = idDocumentoTipo;
	}

	public String getDsDocumentoTipo() {
		return dsDocumentoTipo;
	}

	public void setDsDocumentoTipo(String dsDocumentoTipo) {
		this.dsDocumentoTipo = dsDocumentoTipo;
	}

	public DocumentoTipo() {
	}
	
	public DocumentoTipo(Long idDocumentoTipo) {
		this.idDocumentoTipo=idDocumentoTipo;
	}

	public List<ChecklistDocumento> getChecklist() {
		return checklist;
	}

	public void setChecklist(List<ChecklistDocumento> checklist) {
		this.checklist = checklist;
	}
	
	
}
