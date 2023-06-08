package com.co.igg.catastro.common.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "lc_predio")
public class LcPredio {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	private String departamento;
	
	private String municipio;
	
	private String matriculaInmobiliaria;
	private String numeroPredial;
	
	@OneToMany(mappedBy="lcPredio")
    private List<Solicitud> solicitudes;
	
	@OneToMany(mappedBy="lcPredio")
    private List<LcPredioInteresado> interesados;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMatriculaInmobiliaria() {
		return matriculaInmobiliaria;
	}
	public void setMatriculaInmobiliaria(String matriculaInmobiliaria) {
		this.matriculaInmobiliaria = matriculaInmobiliaria;
	}
	public String getNumeroPredial() {
		return numeroPredial;
	}
	public void setNumeroPredial(String numeroPredial) {
		this.numeroPredial = numeroPredial;
	}
	public List<Solicitud> getSolicitudes() {
		return solicitudes;
	}
	public void setSolicitudes(List<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}
	public List<LcPredioInteresado> getInteresados() {
		return interesados;
	}
	public void setInteresados(List<LcPredioInteresado> interesados) {
		this.interesados = interesados;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	
	
}
