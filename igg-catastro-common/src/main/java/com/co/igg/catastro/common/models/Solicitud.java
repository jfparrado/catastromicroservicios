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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Generated;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@DynamicUpdate
@Table(name = "ct_solicitud")
public class Solicitud implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_solicitud")
	private Long idSolicitud ;

	private String nrRadicado; 
	private String nrPredial;
	private String nrMariculaInmobilidaria;
	private String dsReferenciaProceso;
	private String dsActivityName;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_usuario_radicacion")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Usuario usuario;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_usuario_asignacion")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Usuario usuarioAsignacion;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_sede")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Sede sede;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_mutacion_clase")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private MutacionClase mutacionClase;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_mutacion_tipo")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private MutacionTipo mutacionTipo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="lc_predio_id")
	@JsonBackReference
	private LcPredio lcPredio;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="lc_divipola_id")
	private LcDivipola lcDivipola;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="lc_interesado_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private LcInteresado lcInteresado;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dtCreacion ;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dtActualizacion ;

	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dtActivity;
	
	@OneToMany(mappedBy="solicitud")
    private List<Documento> documentos;

	public Long getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(Long idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public String getNrRadicado() {
		return nrRadicado;
	}

	public void setNrRadicado(String nrRadicado) {
		this.nrRadicado = nrRadicado;
	}

	public String getNrPredial() {
		return nrPredial;
	}

	public void setNrPredial(String nrPredial) {
		this.nrPredial = nrPredial;
	}

	public String getNrMariculaInmobilidaria() {
		return nrMariculaInmobilidaria;
	}

	public void setNrMariculaInmobilidaria(String nrMariculaInmobilidaria) {
		this.nrMariculaInmobilidaria = nrMariculaInmobilidaria;
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

	public MutacionClase getMutacionClase() {
		return mutacionClase;
	}

	public void setMutacionClase(MutacionClase mutacionClase) {
		this.mutacionClase = mutacionClase;
	}

	public MutacionTipo getMutacionTipo() {
		return mutacionTipo;
	}

	public void setMutacionTipo(MutacionTipo mutacionTipo) {
		this.mutacionTipo = mutacionTipo;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getDsReferenciaProceso() {
		return dsReferenciaProceso;
	}

	public void setDsReferenciaProceso(String dsReferenciaProceso) {
		this.dsReferenciaProceso = dsReferenciaProceso;
	}

	public LcPredio getLcPredio() {
		return lcPredio;
	}

	public void setLcPredio(LcPredio lcPredio) {
		this.lcPredio = lcPredio;
	}

	public LcInteresado getLcInteresado() {
		return lcInteresado;
	}

	public void setLcInteresado(LcInteresado lcInteresado) {
		this.lcInteresado = lcInteresado;
	}

	public LcDivipola getLcDivipola() {
		return lcDivipola;
	}

	public void setLcDivipola(LcDivipola lcDivipola) {
		this.lcDivipola = lcDivipola;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	public String getDsActivityName() {
		return dsActivityName;
	}

	public void setDsActivityName(String dsActivityName) {
		this.dsActivityName = dsActivityName;
	}

	public Usuario getUsuarioAsignacion() {
		return usuarioAsignacion;
	}

	public void setUsuarioAsignacion(Usuario usuarioAsignacion) {
		this.usuarioAsignacion = usuarioAsignacion;
	}

	public Date getDtActivity() {
		return dtActivity;
	}

	public void setDtActivity(Date dtActivity) {
		this.dtActivity = dtActivity;
	}
	
}
