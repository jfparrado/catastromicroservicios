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
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ct_mutacion_clase")
public class MutacionClase implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_mutacion_clase")
	private Long idMutacionClase ;
	
	private String dsNombre ;
	
	private String dsShort;
	
	private String dsMutacion;
	
	private Date dtCreacion;
	
	private Date dtActualizacion;
	
	private Boolean flEstado;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_template")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Template template;
	
	@ManyToMany
	@JoinTable(
	  name = "ct_mutacionclase_mutaciontipo", 
	  joinColumns = @JoinColumn(name = "id_mutacion_clase"), 
	  inverseJoinColumns = @JoinColumn(name = "id_mutacion_tipo"))
	private List<MutacionTipo> mutacionTipos;
	
	@OneToMany(mappedBy="mutacionClase")
    private List<MutacionclaseDocumentotipo> mutacionClaseDocumentoTipos;
	
	public MutacionClase() {
	}
	
	public MutacionClase(Long idMutacionClase) {
		this.idMutacionClase = idMutacionClase;
	}

	
	public Long getIdMutacionClase() {
		return idMutacionClase;
	}

	public void setIdMutacionClase(Long idMutacionClase) {
		this.idMutacionClase = idMutacionClase;
	}

	public String getDsNombre() {
		return dsNombre;
	}

	public void setDsNombre(String dsNombre) {
		this.dsNombre = dsNombre;
	}

	public String getDsShort() {
		return dsShort;
	}

	public void setDsShort(String dsShort) {
		this.dsShort = dsShort;
	}

	public String getDsMutacion() {
		return dsMutacion;
	}

	public void setDsMutacion(String dsMutacion) {
		this.dsMutacion = dsMutacion;
	}

	public Date getDtCreacion() {
		return dtCreacion;
	}

	@JsonFormat(pattern="yyyy-MM-dd")
	public void setDtCreacion(Date dtCreacion) {
		this.dtCreacion = dtCreacion;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd")
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

	public List<MutacionTipo> getMutacionTipos() {
		return mutacionTipos;
	}

	public void setMutacionTipos(List<MutacionTipo> mutacionTipos) {
		this.mutacionTipos = mutacionTipos;
	}

	public List<MutacionclaseDocumentotipo> getMutacionClaseDocumentoTipos() {
		return mutacionClaseDocumentoTipos;
	}

	public void setMutacionClaseDocumentoTipo(List<MutacionclaseDocumentotipo> mutacionClaseDocumentoTipos) {
		this.mutacionClaseDocumentoTipos = mutacionClaseDocumentoTipos;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}
	
}
