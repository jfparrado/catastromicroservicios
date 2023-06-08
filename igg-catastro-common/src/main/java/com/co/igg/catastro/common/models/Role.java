package com.co.igg.catastro.common.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "oauth_roles")
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_role")
	private Long idRole;
	
	private String dsRole;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	@JsonBackReference
	private List<Usuario> usuarios;
	
	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	public String getDsRole() {
		return dsRole;
	}

	public void setDsRole(String dsRole) {
		this.dsRole = dsRole;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
}
