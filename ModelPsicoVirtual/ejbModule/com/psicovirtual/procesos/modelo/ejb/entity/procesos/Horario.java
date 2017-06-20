package com.psicovirtual.procesos.modelo.ejb.entity.procesos;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

/**
 * The persistent class for the horario database table.
 * 
 */
@Entity
@NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h")
public class Horario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_HORARIO")
	private int idHorario;

	private String estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_INICIAL")
	private Date fechaInicial;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_FINAL")
	private Date fechaFinal;

	// bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name = "ID_PSICO")
	private Usuario usuario;

	public Horario() {
	}

	public int getIdHorario() {
		return this.idHorario;
	}

	public void setIdHorario(int idHorario) {
		this.idHorario = idHorario;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}



	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}