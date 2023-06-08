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
@Table(name = "ct_solicitud_interesado")
public class SolicitudInteresado {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_solicitud_persona")
	private Long idSolicitudPersona ;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="interesado_id")
	private LcInteresado lcInteresado;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_solicitud")
	private Solicitud solicitud;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_tipo_persona_relacion")
	private Tipo tipoPersonaRelacion;

	public Long getIdSolicitudPersona() {
		return idSolicitudPersona;
	}

	public void setIdSolicitudPersona(Long idSolicitudPersona) {
		this.idSolicitudPersona = idSolicitudPersona;
	}

	public LcInteresado getLcInteresado() {
		return lcInteresado;
	}

	public void setLcInteresado(LcInteresado lcInteresado) {
		this.lcInteresado = lcInteresado;
	}

	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public Tipo getTipoPersonaRelacion() {
		return tipoPersonaRelacion;
	}

	public void setTipoPersonaRelacion(Tipo tipoPersonaRelacion) {
		this.tipoPersonaRelacion = tipoPersonaRelacion;
	}
	
	
}
