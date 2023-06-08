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
@Table(name = "ct_proceso")
public class Proceso implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_proceso")
	private Long idProceso;
	private String nrRadicado;
	private String dsReferenciaProceso;
	private String mensaje;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_radicacion")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_usuario_asignacion")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Usuario usuarioAsignacion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_mutacion_clase")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private MutacionClase mutacionClase;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_mutacion_tipo")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private MutacionTipo mutacionTipo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lc_interesado_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private LcInteresado lcInteresado;

	public Long getIdProceso() {
		return idProceso;
	}

	public void setIdProceso(Long idProceso) {
		this.idProceso = idProceso;
	}

	public String getNrRadicado() {
		return nrRadicado;
	}

	public void setNrRadicado(String nrRadicado) {
		this.nrRadicado = nrRadicado;
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

	public LcInteresado getLcInteresado() {
		return lcInteresado;
	}

	public void setLcInteresado(LcInteresado lcInteresado) {
		this.lcInteresado = lcInteresado;
	}

	// depende de lo que se eleccione en mutaciones

	public Usuario getUsuarioAsignacion() {
		return usuarioAsignacion;
	}

	public void setUsuarioAsignacion(Usuario usuarioAsignacion) {
		this.usuarioAsignacion = usuarioAsignacion;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
