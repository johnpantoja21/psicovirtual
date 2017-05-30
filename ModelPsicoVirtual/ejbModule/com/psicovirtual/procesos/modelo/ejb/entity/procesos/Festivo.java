package com.psicovirtual.procesos.modelo.ejb.entity.procesos;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the festivos database table.
 * 
 */
@Entity
@Table(name="festivos")
@NamedQuery(name="Festivo.findAll", query="SELECT f FROM Festivo f")
public class Festivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_FESTIVO")
	private int idFestivo;

	private String estado;

	@Temporal(TemporalType.DATE)
	private Date festivo;

	public Festivo() {
	}

	public int getIdFestivo() {
		return this.idFestivo;
	}

	public void setIdFestivo(int idFestivo) {
		this.idFestivo = idFestivo;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFestivo() {
		return this.festivo;
	}

	public void setFestivo(Date festivo) {
		this.festivo = festivo;
	}

}