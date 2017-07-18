package com.psicovirtual.procesos.modelo.ejb.entity.procesos;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the tipo_usuario database table.
 * 
 */
@Entity
@Table(name="tipo_usuario")
@NamedQuery(name="TipoUsuario.findAll", query="SELECT t FROM TipoUsuario t")
public class TipoUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_TIPO_USU")
	private int idTipoUsu;

	private String descripcion;

	private String estado;

	private String nombre;

	//bi-directional many-to-one association to PaginasPerfile
	@OneToMany(mappedBy="tipoUsuario")
	private List<PaginasPerfile> paginasPerfiles;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="tipoUsuario")
	private List<Usuario> usuarios;

	public TipoUsuario() {
	}


	public int getIdTipoUsu() {
		return idTipoUsu;
	}


	public void setIdTipoUsu(int idTipoUsu) {
		this.idTipoUsu = idTipoUsu;
	}


	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<PaginasPerfile> getPaginasPerfiles() {
		return this.paginasPerfiles;
	}

	public void setPaginasPerfiles(List<PaginasPerfile> paginasPerfiles) {
		this.paginasPerfiles = paginasPerfiles;
	}

	public PaginasPerfile addPaginasPerfile(PaginasPerfile paginasPerfile) {
		getPaginasPerfiles().add(paginasPerfile);
		paginasPerfile.setTipoUsuario(this);

		return paginasPerfile;
	}

	public PaginasPerfile removePaginasPerfile(PaginasPerfile paginasPerfile) {
		getPaginasPerfiles().remove(paginasPerfile);
		paginasPerfile.setTipoUsuario(null);

		return paginasPerfile;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setTipoUsuario(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setTipoUsuario(null);

		return usuario;
	}

}