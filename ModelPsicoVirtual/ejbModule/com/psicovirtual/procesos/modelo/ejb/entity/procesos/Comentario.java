package com.psicovirtual.procesos.modelo.ejb.entity.procesos;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the comentarios database table.
 * 
 */
@Entity
@Table(name="comentarios")
@NamedQuery(name="Comentario.findAll", query="SELECT c FROM Comentario c")
public class Comentario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_COMENTARIO")
	private BigDecimal idComentario;

	private String estado;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_PUBLICACION")
	private Date fechaPublicacion;

	private String testimonio;

	//bi-directional many-to-one association to ClientesPsicologo
	@ManyToOne
	@JoinColumn(name="ID_CLIE_PSICO")
	private ClientesPsicologo clientesPsicologo;

	public Comentario() {
	}

	public BigDecimal getIdComentario() {
		return this.idComentario;
	}

	public void setIdComentario(BigDecimal idComentario) {
		this.idComentario = idComentario;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaPublicacion() {
		return this.fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getTestimonio() {
		return this.testimonio;
	}

	public void setTestimonio(String testimonio) {
		this.testimonio = testimonio;
	}

	public ClientesPsicologo getClientesPsicologo() {
		return this.clientesPsicologo;
	}

	public void setClientesPsicologo(ClientesPsicologo clientesPsicologo) {
		this.clientesPsicologo = clientesPsicologo;
	}

}