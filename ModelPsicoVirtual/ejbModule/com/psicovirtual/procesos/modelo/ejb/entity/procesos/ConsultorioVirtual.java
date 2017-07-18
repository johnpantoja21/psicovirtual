package com.psicovirtual.procesos.modelo.ejb.entity.procesos;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the consultorio_virtual database table.
 * 
 */
@Entity
@Table(name="consultorio_virtual")
@NamedQuery(name="ConsultorioVirtual.findAll", query="SELECT c FROM ConsultorioVirtual c")
public class ConsultorioVirtual implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_CONSULTORIO")
	private BigDecimal idConsultorio;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="ID_CLIE_PSICO")
	private int idCliePsico;

	@Column(name="ID_SALA")
	private String idSala;

	public ConsultorioVirtual() {
	}

	public BigDecimal getIdConsultorio() {
		return this.idConsultorio;
	}

	public void setIdConsultorio(BigDecimal idConsultorio) {
		this.idConsultorio = idConsultorio;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getIdCliePsico() {
		return this.idCliePsico;
	}

	public void setIdCliePsico(int idCliePsico) {
		this.idCliePsico = idCliePsico;
	}

	public String getIdSala() {
		return this.idSala;
	}

	public void setIdSala(String idSala) {
		this.idSala = idSala;
	}

}