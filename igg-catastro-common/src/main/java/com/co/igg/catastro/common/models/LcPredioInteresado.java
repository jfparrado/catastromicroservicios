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

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "lc_predio_interesado")
public class LcPredioInteresado {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="predio_id")
	@JsonBackReference
	private LcPredio lcPredio;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="interesado_id")
	private LcInteresado lcInteresado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	
}
