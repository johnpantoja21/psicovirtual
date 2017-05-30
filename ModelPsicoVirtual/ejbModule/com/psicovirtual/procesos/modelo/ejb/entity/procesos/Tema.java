package com.psicovirtual.procesos.modelo.ejb.entity.procesos;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the temas database table.
 * 
 */
@Entity
@Table(name="temas")
@NamedQuery(name="Tema.findAll", query="SELECT t FROM Tema t")
public class Tema implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_TEMA")
	private int idTema;

	private String descripcion;

	

	private String estado;
	
	//bi-directional many-to-one association to SeccionHistorial
	@OneToMany(mappedBy="tema")
	private List<SeccionHistorial> seccionHistorials;

	public Tema() {
	}

	public int getIdTema() {
		return this.idTema;
	}

	public void setIdTema(int idTema) {
		this.idTema = idTema;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<SeccionHistorial> getSeccionHistorials() {
		return this.seccionHistorials;
	}

	public void setSeccionHistorials(List<SeccionHistorial> seccionHistorials) {
		this.seccionHistorials = seccionHistorials;
	}

	public SeccionHistorial addSeccionHistorial(SeccionHistorial seccionHistorial) {
		getSeccionHistorials().add(seccionHistorial);
		seccionHistorial.setTema(this);

		return seccionHistorial;
	}

	public SeccionHistorial removeSeccionHistorial(SeccionHistorial seccionHistorial) {
		getSeccionHistorials().remove(seccionHistorial);
		seccionHistorial.setTema(null);

		return seccionHistorial;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	
	
}