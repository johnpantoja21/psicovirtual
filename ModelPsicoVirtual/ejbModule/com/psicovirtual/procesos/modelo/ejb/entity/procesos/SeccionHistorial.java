package com.psicovirtual.procesos.modelo.ejb.entity.procesos;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the seccion_historial database table.
 * 
 */
@Entity
@Table(name="seccion_historial")
@NamedQuery(name="SeccionHistorial.findAll", query="SELECT s FROM SeccionHistorial s")
public class SeccionHistorial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_SECCION")
	private int idSeccion;

	@Lob
	private byte[] descripcion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_HISTORIAL")
	private Date fechaHistorial;

	//bi-directional many-to-one association to SeccionFamilia
	@OneToMany(mappedBy="seccionHistorial")
	private List<SeccionFamilia> seccionFamilias;

	//bi-directional many-to-one association to Cita
	@ManyToOne
	@JoinColumn(name="ID_CITA")
	private Cita cita;

	//bi-directional many-to-one association to Tema
	@ManyToOne
	@JoinColumn(name="ID_TEMA")
	private Tema tema;

	public SeccionHistorial() {
	}

	public int getIdSeccion() {
		return this.idSeccion;
	}

	public void setIdSeccion(int idSeccion) {
		this.idSeccion = idSeccion;
	}

	public byte[] getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(byte[] descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaHistorial() {
		return this.fechaHistorial;
	}

	public void setFechaHistorial(Date fechaHistorial) {
		this.fechaHistorial = fechaHistorial;
	}

	public List<SeccionFamilia> getSeccionFamilias() {
		return this.seccionFamilias;
	}

	public void setSeccionFamilias(List<SeccionFamilia> seccionFamilias) {
		this.seccionFamilias = seccionFamilias;
	}

	public SeccionFamilia addSeccionFamilia(SeccionFamilia seccionFamilia) {
		getSeccionFamilias().add(seccionFamilia);
		seccionFamilia.setSeccionHistorial(this);

		return seccionFamilia;
	}

	public SeccionFamilia removeSeccionFamilia(SeccionFamilia seccionFamilia) {
		getSeccionFamilias().remove(seccionFamilia);
		seccionFamilia.setSeccionHistorial(null);

		return seccionFamilia;
	}

	public Cita getCita() {
		return this.cita;
	}

	public void setCita(Cita cita) {
		this.cita = cita;
	}

	public Tema getTema() {
		return this.tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

}