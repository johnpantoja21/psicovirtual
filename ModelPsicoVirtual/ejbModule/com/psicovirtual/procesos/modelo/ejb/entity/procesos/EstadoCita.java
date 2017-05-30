package com.psicovirtual.procesos.modelo.ejb.entity.procesos;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the estado_citas database table.
 * 
 */
@Entity
@Table(name="estado_citas")
@NamedQuery(name="EstadoCita.findAll", query="SELECT e FROM EstadoCita e")
public class EstadoCita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_ESTADO_CITA")
	private BigDecimal idEstadoCita;

	private String descripcion;

	private String estado;
	
	//bi-directional many-to-one association to Cita
	@OneToMany(mappedBy="estadoCita")
	private List<Cita> citas;

	public EstadoCita() {
	}


	public BigDecimal getIdEstadoCita() {
		return idEstadoCita;
	}


	public void setIdEstadoCita(BigDecimal idEstadoCita) {
		this.idEstadoCita = idEstadoCita;
	}


	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Cita> getCitas() {
		return this.citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	public Cita addCita(Cita cita) {
		getCitas().add(cita);
		cita.setEstadoCita(this);

		return cita;
	}

	public Cita removeCita(Cita cita) {
		getCitas().remove(cita);
		cita.setEstadoCita(null);

		return cita;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	

}