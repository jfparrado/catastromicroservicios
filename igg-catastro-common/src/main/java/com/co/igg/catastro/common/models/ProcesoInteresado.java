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
@Table(name = "ct_proceso_interesado")
public class ProcesoInteresado {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_proceso_persona")
	private Long idProcesoPersona ;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="interesado_id")
	private LcInteresado lcInteresado;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_proceso")
	private Proceso proceso;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_tipo_persona_relacion")
	private Tipo tipoPersonaRelacion;

	public Long getIdProcesoPersona() {
		return idProcesoPersona;
	}

	public void setIdProcesoPersona(Long idProcesoPersona) {
		this.idProcesoPersona = idProcesoPersona;
	}

	public LcInteresado getLcInteresado() {
		return lcInteresado;
	}

	public void setLcInteresado(LcInteresado lcInteresado) {
		this.lcInteresado = lcInteresado;
	}

	public Proceso getProceso() {
		return proceso;
	}

	public void setProceso(Proceso proceso) {
		this.proceso = proceso;
	}

	public Tipo getTipoPersonaRelacion() {
		return tipoPersonaRelacion;
	}

	public void setTipoPersonaRelacion(Tipo tipoPersonaRelacion) {
		this.tipoPersonaRelacion = tipoPersonaRelacion;
	}
	
	
}
