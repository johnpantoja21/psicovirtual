package com.psicovirtual.procesos.modelo.ejb.entity.procesos;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipos_servicios database table.
 * 
 */
@Entity
@Table(name="tipos_servicios")
@NamedQuery(name="TiposServicio.findAll", query="SELECT t FROM TiposServicio t")
public class TiposServicio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_TIPO")
	private int idTipo;

	@Column(name="NOMBRE_TIPO")
	private String nombreTipo;

	
	private String estado;
	
	
	//bi-directional many-to-one association to Servicio
	@OneToMany(mappedBy="tiposServicio")
	private List<Servicio> servicios;

	public TiposServicio() {
	}

	public int getIdTipo() {
		return this.idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public String getNombreTipo() {
		return this.nombreTipo;
	}

	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}

	public List<Servicio> getServicios() {
		return this.servicios;
	}

	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}

	public Servicio addServicio(Servicio servicio) {
		getServicios().add(servicio);
		servicio.setTiposServicio(this);

		return servicio;
	}

	public Servicio removeServicio(Servicio servicio) {
		getServicios().remove(servicio);
		servicio.setTiposServicio(null);

		return servicio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}