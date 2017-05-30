package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNEstadoCita;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.EstadoCita;

@ManagedBean(name = "MBEstadoCita")
@SessionScoped
public class MBEstadoCita {
	MBMensajes mensajes = new MBMensajes();

	DNEstadoCita dNEstadoCita;

	private String descripcion;

	private String descripcionModificar;
	private String estadoModificar;

	private List<EstadoCita> listaEstadoCita;
	private EstadoCita estadoCitaSeleccionado;

	public MBEstadoCita() throws Exception {

		if (dNEstadoCita == null) {
			dNEstadoCita = new DNEstadoCita();
		}
		estadoCitaSeleccionado = new EstadoCita();
		listaEstadoCita = dNEstadoCita.listaEstadoCita();
	}

	public void guardar() throws Exception {
		if (dNEstadoCita == null) {
			dNEstadoCita = new DNEstadoCita();
		}

		if (descripcion.length() > 0) {

			EstadoCita insert = new EstadoCita();
			insert.setDescripcion(descripcion);
			insert.setEstado("ACTIVO");
			dNEstadoCita.guardarEstadoCita(insert);
			mensajes.mostrarMensaje("Registro Exitoso", 1);
			listaEstadoCita = dNEstadoCita.listaEstadoCita();
			limpiar();

		} else {
			mensajes.mostrarMensaje("Debe ingresar la descripción", 2);
		}

	}

	public void limpiar() {

		descripcion = null;
	}

	public void limpiarModificar() {
		estadoCitaSeleccionado = null;
		descripcionModificar = null;
		estadoModificar = null;
	}

	public void cargarModificar() {

		descripcionModificar = estadoCitaSeleccionado.getDescripcion();
		estadoModificar = estadoCitaSeleccionado.getEstado();
	}

	public void guardarModificar() throws Exception {
		if (dNEstadoCita == null) {
			dNEstadoCita = new DNEstadoCita();
		}
		if (estadoCitaSeleccionado != null && descripcionModificar.length() > 0) {
			estadoCitaSeleccionado.setDescripcion(descripcionModificar);
			estadoCitaSeleccionado.setEstado(estadoModificar);
			dNEstadoCita.modificarEstadoCita(estadoCitaSeleccionado);
			mensajes.mostrarMensaje("Registro Moficado Exitosamente ", 1);
			listaEstadoCita = dNEstadoCita.listaEstadoCita();
			limpiarModificar();

		} else {
			mensajes.mostrarMensaje("Debe ingresar la descripción", 2);
		}

	}

	public MBMensajes getMensajes() {
		return mensajes;
	}

	public void setMensajes(MBMensajes mensajes) {
		this.mensajes = mensajes;
	}

	public DNEstadoCita getdNEstadoCita() {
		return dNEstadoCita;
	}

	public void setdNEstadoCita(DNEstadoCita dNEstadoCita) {
		this.dNEstadoCita = dNEstadoCita;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<EstadoCita> getListaEstadoCita() {
		return listaEstadoCita;
	}

	public void setListaEstadoCita(List<EstadoCita> listaEstadoCita) {
		this.listaEstadoCita = listaEstadoCita;
	}

	public EstadoCita getEstadoCitaSeleccionado() {
		return estadoCitaSeleccionado;
	}

	public void setEstadoCitaSeleccionado(EstadoCita estadoCitaSeleccionado) {
		this.estadoCitaSeleccionado = estadoCitaSeleccionado;
	}

	public String getDescripcionModificar() {
		return descripcionModificar;
	}

	public void setDescripcionModificar(String descripcionModificar) {
		this.descripcionModificar = descripcionModificar;
	}

	public String getEstadoModificar() {
		return estadoModificar;
	}

	public void setEstadoModificar(String estadoModificar) {
		this.estadoModificar = estadoModificar;
	}

}
