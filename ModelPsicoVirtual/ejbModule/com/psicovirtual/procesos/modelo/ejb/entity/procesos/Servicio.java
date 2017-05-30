package com.psicovirtual.procesos.modelo.ejb.entity.procesos;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the servicios database table.
 * 
 */
@Entity
@Table(name="servicios")
@NamedQuery(name="Servicio.findAll", query="SELECT s FROM Servicio s")
public class Servicio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_SERVICIO")
	private int idServicio;

	@Column(name="NOMBRE_SERVICIO")
	private String nombreServicio;

	private int precio;

	//bi-directional many-to-one association to Cita
	@OneToMany(mappedBy="servicio")
	private List<Cita> citas;

	//bi-directional many-to-one association to TiposServicio
	@ManyToOne
	@JoinColumn(name="ID_TIPO")
	private TiposServicio tiposServicio;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID_PSICOLOGO")
	private Usuario usuario;

	public Servicio() {
	}

	public int getIdServicio() {
		return this.idServicio;
	}

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

	public String getNombreServicio() {
		return this.nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public int getPrecio() {
		return this.precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public List<Cita> getCitas() {
		return this.citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	public Cita addCita(Cita cita) {
		getCitas().add(cita);
		cita.setServicio(this);

		return cita;
	}

	public Cita removeCita(Cita cita) {
		getCitas().remove(cita);
		cita.setServicio(null);

		return cita;
	}

	public TiposServicio getTiposServicio() {
		return this.tiposServicio;
	}

	public void setTiposServicio(TiposServicio tiposServicio) {
		this.tiposServicio = tiposServicio;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	public String listaServicios() {
		return "Servicio [nombreServicio=" + nombreServicio + "]";
	}
	
	
	

}