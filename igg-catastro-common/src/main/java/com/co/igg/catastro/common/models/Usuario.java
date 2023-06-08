package com.co.igg.catastro.common.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "oauth_usuarios")
public class Usuario  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private Long idUsuario;
	
	private String dsUsername;

	private String dsPassword;
	
	private String dsNombre;
	
	private String dsEmail;

	private Integer nrIntentos;
	
	private Boolean flEstatus;
	
	@ManyToMany
	@JoinTable(
	  name = "oauth_usuarios_roles", 
	  joinColumns = @JoinColumn(name = "id_usuario"), 
	  inverseJoinColumns = @JoinColumn(name = "id_role"))
	private List<Role> roles;

	
	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDsUsername() {
		return dsUsername;
	}

	public void setDsUsername(String dsUsername) {
		this.dsUsername = dsUsername;
	}

	public String getDsPassword() {
		return dsPassword;
	}

	public void setDsPassword(String dsPassword) {
		this.dsPassword = dsPassword;
	}

	public Boolean getFlEstatus() {
		return flEstatus;
	}

	public void setFlEstatus(Boolean flEstatus) {
		this.flEstatus = flEstatus;
	}

	public String getDsNombre() {
		return dsNombre;
	}

	public void setDsNombre(String dsNombre) {
		this.dsNombre = dsNombre;
	}

	public String getDsEmail() {
		return dsEmail;
	}

	public void setDsEmail(String dsEmail) {
		this.dsEmail = dsEmail;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Integer getNrIntentos() {
		return nrIntentos;
	}

	public void setNrIntentos(Integer nrIntentos) {
		this.nrIntentos = nrIntentos;
	}

	public Usuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Usuario() {
	}
}
