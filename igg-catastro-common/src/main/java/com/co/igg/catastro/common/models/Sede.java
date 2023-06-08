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
@Table(name = "ct_sede")
public class Sede {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_sede")
	private Long idSede ;
	
	@Column(name="ds_sede")
	private String dsSede;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_sede_tipo")
	@JsonBackReference
	private SedeTipo sedeTipo;
	
	/*
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pais_ciudad")
	private PaisCiudad paisCiudad;
	*/
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dtCreacion ;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dtActualizacion ;
	
	private Boolean flEstado ;

	public Long getIdSede() {
		return idSede;
	}

	public void setIdSede(Long idSede) {
		this.idSede = idSede;
	}

	public String getDsSede() {
		return dsSede;
	}

	public void setDsSede(String dsSede) {
		this.dsSede = dsSede;
	}

	public SedeTipo getSedeTipo() {
		return sedeTipo;
	}

	public void setSedeTipo(SedeTipo sedeTipo) {
		this.sedeTipo = sedeTipo;
	}
/*
	public PaisCiudad getPaisCiudad() {
		return paisCiudad;
	}

	public void setPaisCiudad(PaisCiudad paisCiudad) {
		this.paisCiudad = paisCiudad;
	}
*/
	
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
	
	public Sede() {
	}
	
	public Sede(Long idSede) {
		this.idSede = idSede;
	}
	
}
