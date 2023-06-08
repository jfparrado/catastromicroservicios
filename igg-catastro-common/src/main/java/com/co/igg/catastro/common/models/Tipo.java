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

@Entity
@Table(name = "ct_tipo")
public class Tipo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tipo")
	private Long idTipo ;
	
	@Column(name="ds_tipo")
	private String dsTipo; 
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_tipo_parent")
	private Tipo tipoParent;

	
	public Tipo() {}

	public Tipo(Long idTipo) {
		this.idTipo = idTipo;
	}

	
	public Long getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Long idTipo) {
		this.idTipo = idTipo;
	}

	public String getDsTipo() {
		return dsTipo;
	}

	public void setDsTipo(String dsTipo) {
		this.dsTipo = dsTipo;
	}

	public Tipo getTipoParent() {
		return tipoParent;
	}

	public void setTipoParent(Tipo tipoParent) {
		this.tipoParent = tipoParent;
	}
	
	
}
