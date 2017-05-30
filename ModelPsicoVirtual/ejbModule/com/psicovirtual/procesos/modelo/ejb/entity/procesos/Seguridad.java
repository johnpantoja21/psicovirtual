package com.psicovirtual.procesos.modelo.ejb.entity.procesos;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the seguridad database table.
 * 
 */
@Entity
@NamedQuery(name="Seguridad.findAll", query="SELECT s FROM Seguridad s")
public class Seguridad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_SEGURIDAD")
	private int idSeguridad;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_VENC")
	private Date fechaVenc;

	@Column(name="ID_USU_PSICO")
	private String idUsuPsico;

	private String llave;

	public Seguridad() {
	}

	public int getIdSeguridad() {
		return this.idSeguridad;
	}

	public void setIdSeguridad(int idSeguridad) {
		this.idSeguridad = idSeguridad;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaVenc() {
		return this.fechaVenc;
	}

	public void setFechaVenc(Date fechaVenc) {
		this.fechaVenc = fechaVenc;
	}

	public String getIdUsuPsico() {
		return this.idUsuPsico;
	}

	public void setIdUsuPsico(String idUsuPsico) {
		this.idUsuPsico = idUsuPsico;
	}

	public String getLlave() {
		return this.llave;
	}

	public void setLlave(String llave) {
		this.llave = llave;
	}

}