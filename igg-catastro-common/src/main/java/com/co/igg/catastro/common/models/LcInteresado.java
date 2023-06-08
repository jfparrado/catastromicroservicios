package com.co.igg.catastro.common.models;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "lc_interesado")
public class LcInteresado {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private BigInteger id;
	
	private String documentoIdentidad;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String razonSocial;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sexo_tipo_id")
	@JsonBackReference
	private LcSexoTipo sexoTipo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="interesado_documento_tipo_id")
	@JsonBackReference
	private LcInteresadoDocumentoTipo interesadoDocumentoTipo ;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="interesado_tipo_id")
	@JsonBackReference
	private LcInteresadoTipo interesadoTipo;
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getDocumentoIdentidad() {
		return documentoIdentidad;
	}
	public void setDocumentoIdentidad(String documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
	}
	public String getPrimerNombre() {
		return primerNombre;
	}
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}
	public String getSegundoNombre() {
		return segundoNombre;
	}
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public LcSexoTipo getSexoTipo() {
		return sexoTipo;
	}
	public void setSexoTipo(LcSexoTipo sexoTipo) {
		this.sexoTipo = sexoTipo;
	}
	public LcInteresadoDocumentoTipo getInteresadoDocumentoTipo() {
		return interesadoDocumentoTipo;
	}
	public void setInteresadoDocumentoTipo(LcInteresadoDocumentoTipo interesadoDocumentoTipo) {
		this.interesadoDocumentoTipo = interesadoDocumentoTipo;
	}
	public LcInteresadoTipo getInteresadoTipo() {
		return interesadoTipo;
	}
	public void setInteresadoTipo(LcInteresadoTipo interesadoTipo) {
		this.interesadoTipo = interesadoTipo;
	}
	/*public List<LcPredio> getPredios() {
		return predios;
	}
	public void setPredios(List<LcPredio> predios) {
		this.predios = predios;
	}*/
	
}