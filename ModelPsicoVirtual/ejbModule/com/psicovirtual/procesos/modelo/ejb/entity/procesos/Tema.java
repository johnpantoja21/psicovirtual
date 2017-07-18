package com.psicovirtual.procesos.modelo.ejb.entity.procesos;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
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
	private BigDecimal idTema;

	private String descripcion;

	private String estado;

	@Column(name="ID_PSICOLOGO")
	private String idPsicologo;

	//bi-directional many-to-one association to SeccionHistorial
	@OneToMany(mappedBy="tema")
	private List<SeccionHistorial> seccionHistorials;

	public Tema() {
	}

	public BigDecimal getIdTema() {
		return this.idTema;
	}

	public void setIdTema(BigDecimal idTema) {
		this.idTema = idTema;
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

	public String getIdPsicologo() {
		return this.idPsicologo;
	}

	public void setIdPsicologo(String idPsicologo) {
		this.idPsicologo = idPsicologo;
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

}