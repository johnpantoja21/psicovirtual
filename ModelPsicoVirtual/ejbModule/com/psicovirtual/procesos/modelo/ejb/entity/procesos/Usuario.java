package com.psicovirtual.procesos.modelo.ejb.entity.procesos;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_USUARIO")
	private BigDecimal idUsuario;

	private String apellidos;

	private String celular;

	@Column(name="DESCRIPCION_PERFIL")
	private String descripcionPerfil;

	private String direccion;

	private String email;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_NAC")
	private Date fechaNac;

	private String foto;

	private String nombre;

	private String password;

	private String telefono;

	private String usuario;

	//bi-directional many-to-one association to ClientesPsicologo
	@OneToMany(mappedBy="usuario1")
	private List<ClientesPsicologo> clientesPsicologos1;

	//bi-directional many-to-one association to ClientesPsicologo
	@OneToMany(mappedBy="usuario2")
	private List<ClientesPsicologo> clientesPsicologos2;

	//bi-directional many-to-one association to Servicio
	@OneToMany(mappedBy="usuario")
	private List<Servicio> servicios;

	//bi-directional many-to-one association to TipoUsuario
	@ManyToOne
	@JoinColumn(name="ID_TIPO_USU")
	private TipoUsuario tipoUsuario;

	public Usuario() {
	}



	public BigDecimal getIdUsuario() {
		return idUsuario;
	}



	public void setIdUsuario(BigDecimal idUsuario) {
		this.idUsuario = idUsuario;
	}



	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDescripcionPerfil() {
		return this.descripcionPerfil;
	}

	public void setDescripcionPerfil(String descripcionPerfil) {
		this.descripcionPerfil = descripcionPerfil;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaNac() {
		return this.fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<ClientesPsicologo> getClientesPsicologos1() {
		return this.clientesPsicologos1;
	}

	public void setClientesPsicologos1(List<ClientesPsicologo> clientesPsicologos1) {
		this.clientesPsicologos1 = clientesPsicologos1;
	}

	public ClientesPsicologo addClientesPsicologos1(ClientesPsicologo clientesPsicologos1) {
		getClientesPsicologos1().add(clientesPsicologos1);
		clientesPsicologos1.setUsuario1(this);

		return clientesPsicologos1;
	}

	public ClientesPsicologo removeClientesPsicologos1(ClientesPsicologo clientesPsicologos1) {
		getClientesPsicologos1().remove(clientesPsicologos1);
		clientesPsicologos1.setUsuario1(null);

		return clientesPsicologos1;
	}

	public List<ClientesPsicologo> getClientesPsicologos2() {
		return this.clientesPsicologos2;
	}

	public void setClientesPsicologos2(List<ClientesPsicologo> clientesPsicologos2) {
		this.clientesPsicologos2 = clientesPsicologos2;
	}

	public ClientesPsicologo addClientesPsicologos2(ClientesPsicologo clientesPsicologos2) {
		getClientesPsicologos2().add(clientesPsicologos2);
		clientesPsicologos2.setUsuario2(this);

		return clientesPsicologos2;
	}

	public ClientesPsicologo removeClientesPsicologos2(ClientesPsicologo clientesPsicologos2) {
		getClientesPsicologos2().remove(clientesPsicologos2);
		clientesPsicologos2.setUsuario2(null);

		return clientesPsicologos2;
	}

	public List<Servicio> getServicios() {
		return this.servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public Servicio addServicio(Servicio servicio) {
		getServicios().add(servicio);
		servicio.setUsuario(this);

		return servicio;
	}

	public Servicio removeServicio(Servicio servicio) {
		getServicios().remove(servicio);
		servicio.setUsuario(null);

		return servicio;
	}

	public TipoUsuario getTipoUsuario() {
		return this.tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}