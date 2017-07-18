package com.psicovirtual.procesos.modelo.ejb.entity.procesos;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the horario database table.
 * 
 */
@Entity
@NamedQuery(name="Horario.findAll", query="SELECT h FROM Horario h")
public class Horario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_HORARIO")
	private BigDecimal idHorario;

	private String estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_FINAL")
	private Date fechaFinal;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_INICIAL")
	private Date fechaInicial;

	//bi-directional many-to-one association to Cita
	@OneToMany(mappedBy="horario")
	private List<Cita> citas;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID_PSICO")
	private Usuario usuario;

	public Horario() {
	}

	public BigDecimal getIdHorario() {
		return this.idHorario;
	}

	public void setIdHorario(BigDecimal idHorario) {
		this.idHorario = idHorario;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaFinal() {
		return this.fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Date getFechaInicial() {
		return this.fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public List<Cita> getCitas() {
		return this.citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	public Cita addCita(Cita cita) {
		getCitas().add(cita);
		cita.setHorario(this);

		return cita;
	}

	public Cita removeCita(Cita cita) {
		getCitas().remove(cita);
		cita.setHorario(null);

		return cita;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}