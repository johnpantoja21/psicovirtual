package com.psicovirtual.procesos.modelo.ejb.entity.procesos;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the estado_cliente database table.
 * 
 */
@Entity
@Table(name="estado_cliente")
@NamedQuery(name="EstadoCliente.findAll", query="SELECT e FROM EstadoCliente e")
public class EstadoCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_ESTADO_CLIE")
	private BigDecimal idEstadoClie;

	private String descripcion;

	private String estado;

	//bi-directional many-to-one association to Cita
	@OneToMany(mappedBy="estadoCliente")
	private List<Cita> citas;

	public EstadoCliente() {
	}

	public BigDecimal getIdEstadoClie() {
		return this.idEstadoClie;
	}

	public void setIdEstadoClie(BigDecimal idEstadoClie) {
		this.idEstadoClie = idEstadoClie;
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
		cita.setEstadoCliente(this);

		return cita;
	}

	public Cita removeCita(Cita cita) {
		getCitas().remove(cita);
		cita.setEstadoCliente(null);

		return cita;
	}

}