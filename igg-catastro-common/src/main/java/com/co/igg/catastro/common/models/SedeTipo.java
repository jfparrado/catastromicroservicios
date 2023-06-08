package com.co.igg.catastro.common.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "ct_sede_tipo")
public class SedeTipo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_sede_tipo")
	private Long idSedeTipo ;

	@Column(name="ds_sede_tipo")
	private String dsSedeTipo ;

	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dtCreacion ;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dtActualizacion ;
	
	private Boolean flEstado ;

	@OneToMany(mappedBy="sedeTipo")
    private List<Sede> sedes;
	
	public List<Sede> getSedes() {
		return sedes;
	}

	public void setSedes(List<Sede> sedes) {
		this.sedes = sedes;
	}

	public Long getIdSedeTipo() {
		return idSedeTipo;
	}

	public void setIdSedeTipo(Long idSedeTipo) {
		this.idSedeTipo = idSedeTipo;
	}

	public String getDsSedeTipo() {
		return dsSedeTipo;
	}

	public void setDsSedeTipo(String dsSedeTipo) {
		this.dsSedeTipo = dsSedeTipo;
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

	public Boolean getFlEstado() {
		return flEstado;
	}

	public void setFlEstado(Boolean flEstado) {
		this.flEstado = flEstado;
	}
	
	
}
