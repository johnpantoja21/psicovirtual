package com.psicovirtual.procesos.modelo.ejb.entity.procesos;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the tipo_cita database table.
 * 
 */
@Entity
@Table(name="tipo_cita")
@NamedQuery(name="TipoCita.findAll", query="SELECT t FROM TipoCita t")
public class TipoCita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_TIPO_CITA")
	private BigDecimal idTipoCita;

	private String descripcion;

	private String estado;

	//bi-directional many-to-one association to Cita
	@OneToMany(mappedBy="tipoCita")
	private List<Cita> citas;

	public TipoCita() {
	}

	public BigDecimal getIdTipoCita() {
		return this.idTipoCita;
	}

	public void setIdTipoCita(BigDecimal idTipoCita) {
		this.idTipoCita = idTipoCita;
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

	public List<Cita> getCitas() {
		return this.citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	public Cita addCita(Cita cita) {
		getCitas().add(cita);
		cita.setTipoCita(this);

		return cita;
	}

	public Cita removeCita(Cita cita) {
		getCitas().remove(cita);
		cita.setTipoCita(null);

		return cita;
	}

}