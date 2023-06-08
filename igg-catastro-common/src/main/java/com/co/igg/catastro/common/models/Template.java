package com.co.igg.catastro.common.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ct_template")
public class Template {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_template")
	private Long idTemplate ;
	
	private String dsContent ;

	public Long getIdTemplate() {
		return idTemplate;
	}

	public void setIdTemplate(Long idTemplate) {
		this.idTemplate = idTemplate;
	}

	public String getDsContent() {
		return dsContent;
	}

	public void setDsContent(String dsContent) {
		this.dsContent = dsContent;
	}


}
