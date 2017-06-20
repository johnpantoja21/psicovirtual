package com.psicovirtual.procesos.modelo.ejb.entity.procesos;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the paginas_perfiles database table.
 * 
 */
@Entity
@Table(name="paginas_perfiles")
@NamedQuery(name="PaginasPerfile.findAll", query="SELECT p FROM PaginasPerfile p")
public class PaginasPerfile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PAG_PER")
	private int idPagPer;

	//bi-directional many-to-one association to Pagina
	@ManyToOne
	@JoinColumn(name="ID_PAGINA")
	private Pagina pagina;

	//bi-directional many-to-one association to TipoUsuario
	@ManyToOne
	@JoinColumn(name="ID_TIPO_USU")
	private TipoUsuario tipoUsuario;

	public PaginasPerfile() {
	}

	public int getIdPagPer() {
		return this.idPagPer;
	}

	public void setIdPagPer(int idPagPer) {
		this.idPagPer = idPagPer;
	}

	public Pagina getPagina() {
		return this.pagina;
	}

	public void setPagina(Pagina pagina) {
		this.pagina = pagina;
	}

	public TipoUsuario getTipoUsuario() {
		return this.tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}