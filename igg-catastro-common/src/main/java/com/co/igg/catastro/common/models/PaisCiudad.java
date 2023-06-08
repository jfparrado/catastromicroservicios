package com.co.igg.catastro.common.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ct_pais_ciudad")
public class PaisCiudad {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pais_ciudad")
	private Long idPaisCiudad ;
	
	private String dsPaisCiudad;
	
}
