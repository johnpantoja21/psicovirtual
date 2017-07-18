package com.psicovirtual.procesos.modelo.ejb.entity.procesos;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the paginas database table.
 * 
 */
@Entity
@Table(name="paginas")
@NamedQuery(name="Pagina.findAll", query="SELECT p FROM Pagina p")
public class Pagina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PAGINA")
	private BigDecimal idPagina;

	private String accion;

	private String descripcion;

	private String url;

	//bi-directional many-to-one association to PaginasPerfile
	@OneToMany(mappedBy="pagina")
	private List<PaginasPerfile> paginasPerfiles;

	public Pagina() {
	}

	public BigDecimal getIdPagina() {
		return this.idPagina;
	}

	public void setIdPagina(BigDecimal idPagina) {
		this.idPagina = idPagina;
	}

	public String getAccion() {
		return this.accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<PaginasPerfile> getPaginasPerfiles() {
		return this.paginasPerfiles;
	}

	public void setPaginasPerfiles(List<PaginasPerfile> paginasPerfiles) {
		this.paginasPerfiles = paginasPerfiles;
	}

	public PaginasPerfile addPaginasPerfile(PaginasPerfile paginasPerfile) {
		getPaginasPerfiles().add(paginasPerfile);
		paginasPerfile.setPagina(this);

		return paginasPerfile;
	}

	public PaginasPerfile removePaginasPerfile(PaginasPerfile paginasPerfile) {
		getPaginasPerfiles().remove(paginasPerfile);
		paginasPerfile.setPagina(null);

		return paginasPerfile;
	}

}