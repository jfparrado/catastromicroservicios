package com.co.igg.catastro.common.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lc_interesado_tipo")
public class LcInteresadoDocumentoTipo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	private String interesadoDocumentoTipo;
	
	private String descripcion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInteresadoDocumentoTipo() {
		return interesadoDocumentoTipo;
	}

	public void setInteresadoDocumentoTipo(String interesadoDocumentoTipo) {
		this.interesadoDocumentoTipo = interesadoDocumentoTipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
