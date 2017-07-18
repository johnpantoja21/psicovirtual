package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNTiposServicio;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.TiposServicio;

@ManagedBean(name = "MBTiposServicio")
@SessionScoped
public class MBTiposServicio {
	MBMensajes mensajes = new MBMensajes();

	DNTiposServicio dNTiposServicio;

	private String nombreTipo;

	private List<TiposServicio> listaTiposServicio;
	private TiposServicio tipoServicioSeleccionado;

	private String nombreTipoModificar;

	private String estadoModificar;

	public MBTiposServicio() throws Exception {

		if (dNTiposServicio == null) {
			dNTiposServicio = new DNTiposServicio();
		}
		listaTiposServicio = dNTiposServicio.listaTiposServicio();
		tipoServicioSeleccionado = new TiposServicio();
	}

	public void guardar() throws Exception {
		if (dNTiposServicio == null) {
			dNTiposServicio = new DNTiposServicio();
		}
		if (nombreTipo.length() > 0) {

			TiposServicio insert = new TiposServicio();
			insert.setNombreTipo(nombreTipo);
			insert.setEstado("ACTIVO");
			dNTiposServicio.guardarTiposServicio(insert);
			mensajes.mostrarMensaje("Registro Exitoso", 1);
			listaTiposServicio = dNTiposServicio.listaTiposServicio();
			limpiar();

		} else {
			mensajes.mostrarMensaje("Debe ingresar el nombre del servicio", 2);
		}

	}

	public void limpiar() {

		nombreTipo = null;
	}

	public void limpiarModificar() {
		System.out.println("saludos");
		tipoServicioSeleccionado = null;

		nombreTipoModificar = null;
		estadoModificar = null;
	}

	public void cargarModificar() {

		nombreTipoModificar = tipoServicioSeleccionado.getNombreTipo();
		estadoModificar = tipoServicioSeleccionado.getEstado();
	}

	public void guardarModificar() throws Exception {
		if (dNTiposServicio == null) {
			dNTiposServicio = new DNTiposServicio();
		}

		if (tipoServicioSeleccionado != null && nombreTipoModificar.length() > 0) {

			tipoServicioSeleccionado.setNombreTipo(nombreTipoModificar);
			tipoServicioSeleccionado.setEstado(estadoModificar);
			dNTiposServicio.modificarTiposServicio(tipoServicioSeleccionado);
			mensajes.mostrarMensaje("Registro Moficado Exitosamente ", 1);
			listaTiposServicio = dNTiposServicio.listaTiposServicio();
			limpiarModificar();

		} else {
			mensajes.mostrarMensaje("Debe ingresar el nombre del servicio", 2);
		}

	}

	public MBMensajes getMensajes() {
		return mensajes;
	}

	public void setMensajes(MBMensajes mensajes) {
		this.mensajes = mensajes;
	}

	public DNTiposServicio getdNTiposServicio() {
		return dNTiposServicio;
	}

	public void setdNTiposServicio(DNTiposServicio dNTiposServicio) {
		this.dNTiposServicio = dNTiposServicio;
	}

	public String getNombreTipo() {
		return nombreTipo;
	}

	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}

	public List<TiposServicio> getListaTiposServicio() {
		return listaTiposServicio;
	}

	public void setListaTiposServicio(List<TiposServicio> listaTiposServicio) {
		this.listaTiposServicio = listaTiposServicio;
	}

	public TiposServicio getTipoServicioSeleccionado() {
		return tipoServicioSeleccionado;
	}

	public void setTipoServicioSeleccionado(TiposServicio tipoServicioSeleccionado) {
		this.tipoServicioSeleccionado = tipoServicioSeleccionado;
	}

	public String getNombreTipoModificar() {
		return nombreTipoModificar;
	}

	public void setNombreTipoModificar(String nombreTipoModificar) {
		this.nombreTipoModificar = nombreTipoModificar;
	}

	public String getEstadoModificar() {
		return estadoModificar;
	}

	public void setEstadoModificar(String estadoModificar) {
		this.estadoModificar = estadoModificar;
	}

}
