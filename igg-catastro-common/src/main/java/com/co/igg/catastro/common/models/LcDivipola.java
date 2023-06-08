package com.co.igg.catastro.common.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "lc_divipola")
public class LcDivipola {
	
	@Id
	@Column(name="id")
	private String id;
	
	private String nombre;
	
	@Transient
	private String nombreDpto;
	
	private String latitud;
	private String longitud;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="divipola_id")
	@JsonBackReference
	private LcDivipola departamento;
	
	@OneToMany(mappedBy="departamento")
    private List<LcDivipola> municipios;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LcDivipola getDepartamento() {
		return departamento;
	}

	public void setDepartamento(LcDivipola departamento) {
		this.departamento = departamento;
	}

	public List<LcDivipola> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<LcDivipola> municipios) {
		this.municipios = municipios;
	}

	public LcDivipola() {
		
	}
	
	public LcDivipola(String id) {
		this.id = id;
	}

	public String getNombreDpto() {
		if(departamento != null)
			return departamento.getNombre();
		else
			return "";
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

}
