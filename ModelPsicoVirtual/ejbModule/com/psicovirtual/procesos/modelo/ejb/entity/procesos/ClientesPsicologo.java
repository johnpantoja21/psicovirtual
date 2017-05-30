package com.psicovirtual.procesos.modelo.ejb.entity.procesos;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the clientes_psicologos database table.
 * 
 */
@Entity
@Table(name="clientes_psicologos")
@NamedQuery(name="ClientesPsicologo.findAll", query="SELECT c FROM ClientesPsicologo c")
public class ClientesPsicologo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_CLIE_PSICO")
	private int idCliePsico;

	@Column(name="CALIFICACION_PSICO")
	private int calificacionPsico;

	//bi-directional many-to-one association to Cita
	@OneToMany(mappedBy="clientesPsicologo")
	private List<Cita> citas;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID_USUARIO")
	private Usuario usuario1;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="ID_PSICOLOGO")
	private Usuario usuario2;

	//bi-directional many-to-one association to Comentario
	@OneToMany(mappedBy="clientesPsicologo")
	private List<Comentario> comentarios;

	public ClientesPsicologo() {
	}

	public int getIdCliePsico() {
		return this.idCliePsico;
	}

	public void setIdCliePsico(int idCliePsico) {
		this.idCliePsico = idCliePsico;
	}

	public int getCalificacionPsico() {
		return this.calificacionPsico;
	}

	public void setCalificacionPsico(int calificacionPsico) {
		this.calificacionPsico = calificacionPsico;
	}

	public List<Cita> getCitas() {
		return this.citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	public Cita addCita(Cita cita) {
		getCitas().add(cita);
		cita.setClientesPsicologo(this);

		return cita;
	}

	public Cita removeCita(Cita cita) {
		getCitas().remove(cita);
		cita.setClientesPsicologo(null);

		return cita;
	}

	public Usuario getUsuario1() {
		return this.usuario1;
	}

	public void setUsuario1(Usuario usuario1) {
		this.usuario1 = usuario1;
	}

	public Usuario getUsuario2() {
		return this.usuario2;
	}

	public void setUsuario2(Usuario usuario2) {
		this.usuario2 = usuario2;
	}

	public List<Comentario> getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Comentario addComentario(Comentario comentario) {
		getComentarios().add(comentario);
		comentario.setClientesPsicologo(this);

		return comentario;
	}

	public Comentario removeComentario(Comentario comentario) {
		getComentarios().remove(comentario);
		comentario.setClientesPsicologo(null);

		return comentario;
	}

}