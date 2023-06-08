package com.co.igg.catastro.common.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ct_mutacion_tipo")
public class MutacionTipo implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_mutacion_tipo")
	private Long idMutacionTipo  ;
	
	private String dsMutacionTipo ;
	
	private String dsDetalle ;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dtCreacion ;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dtActualizacion ;
	
	private Boolean flEstado ;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "mutacionTipos")
	@JsonBackReference
    private List<MutacionClase> mutacionClases;

	public MutacionTipo() {}
	
	public MutacionTipo( Long idMutacionTipo  ) {
		this.idMutacionTipo  = idMutacionTipo ;
	}

	

	public Long getIdMutacionTipo() {
		return idMutacionTipo;
	}

	public void setIdMutacionTipo(Long idMutacionTipo) {
		this.idMutacionTipo = idMutacionTipo;
	}

	public String getDsMutacionTipo() {
		return dsMutacionTipo;
	}

	public void setDsMutacionTipo(String dsMutacionTipo) {
		this.dsMutacionTipo = dsMutacionTipo;
	}

	public String getDsDetalle() {
		return dsDetalle;
	}

	public void setDsDetalle(String dsDetalle) {
		this.dsDetalle = dsDetalle;
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

	public List<MutacionClase> getMutacionClases() {
		return mutacionClases;
	}

	public void setMutacionClases(List<MutacionClase> mutacionClases) {
		this.mutacionClases = mutacionClases;
	}
	
	
	
}
