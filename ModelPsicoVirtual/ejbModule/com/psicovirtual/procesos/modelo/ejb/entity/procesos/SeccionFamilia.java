package com.psicovirtual.procesos.modelo.ejb.entity.procesos;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the seccion_familia database table.
 * 
 */
@Entity
@Table(name="seccion_familia")
@NamedQuery(name="SeccionFamilia.findAll", query="SELECT s FROM SeccionFamilia s")
public class SeccionFamilia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_FAMILIA")
	private BigDecimal idFamilia;

	@Lob
	private byte[] descripcion;

	private String nombre;

	private String vinculo;

	//bi-directional many-to-one association to SeccionHistorial
	@ManyToOne
	@JoinColumn(name="ID_SECCION")
	private SeccionHistorial seccionHistorial;

	public SeccionFamilia() {
	}

	public BigDecimal getIdFamilia() {
		return this.idFamilia;
	}

	public void setIdFamilia(BigDecimal idFamilia) {
		this.idFamilia = idFamilia;
	}

	public byte[] getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(byte[] descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getVinculo() {
		return this.vinculo;
	}

	public void setVinculo(String vinculo) {
		this.vinculo = vinculo;
	}

	public SeccionHistorial getSeccionHistorial() {
		return this.seccionHistorial;
	}

	public void setSeccionHistorial(SeccionHistorial seccionHistorial) {
		this.seccionHistorial = seccionHistorial;
	}

}