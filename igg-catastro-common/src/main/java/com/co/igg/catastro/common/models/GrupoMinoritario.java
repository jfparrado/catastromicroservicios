package com.co.igg.catastro.common.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "ct_grupo_minoritario")
public class GrupoMinoritario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_grupo_minoritario")
	private Long idGrupoMinoritario;
	
	@Column(name="ds_grupo_minoritario")
	private String dsGrupoMinoritario; 
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dtCreacion ;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dtActualizacion ;
	
	private Boolean flEstado;

	public Long getIdGrupoMinoritario() {
		return idGrupoMinoritario;
	}

	public void setIdGrupoMinoritario(Long idGrupoMinoritario) {
		this.idGrupoMinoritario = idGrupoMinoritario;
	}

	public String getDsGrupoMinoritario() {
		return dsGrupoMinoritario;
	}

	public void setDsGrupoMinoritario(String dsGrupoMinoritario) {
		this.dsGrupoMinoritario = dsGrupoMinoritario;
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

	public Boolean getFlEstado() {
		return flEstado;
	}

	public void setFlEstado(Boolean flEstado) {
		this.flEstado = flEstado;
	}
	
	
}
