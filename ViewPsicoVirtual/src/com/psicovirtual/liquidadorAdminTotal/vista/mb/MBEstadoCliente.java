package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNEstadoCliente;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.EstadoCliente;

@ManagedBean(name = "MBEstadoCliente")
@SessionScoped
public class MBEstadoCliente {
	MBMensajes mensajes = new MBMensajes();

	DNEstadoCliente dNEstadoCliente;

	private String descripcion;

	private String descripcionModificar;
	private String estadoModificar;

	private List<EstadoCliente> listaEstadoCliente;
	private EstadoCliente estadoClienteSeleccionado;

	public MBEstadoCliente() throws Exception {

		if (dNEstadoCliente == null) {
			dNEstadoCliente = new DNEstadoCliente();
		}
		estadoClienteSeleccionado = new EstadoCliente();
		listaEstadoCliente = dNEstadoCliente.listaEstadoCliente();
	}

	public void guardar() throws Exception {
		if (dNEstadoCliente == null) {
			dNEstadoCliente = new DNEstadoCliente();
		}

		if (descripcion.length() > 0) {

			EstadoCliente insert = new EstadoCliente();
			insert.setDescripcion(descripcion);
			insert.setEstado("ACTIVO");
			dNEstadoCliente.guardarEstadoCliente(insert);
			mensajes.mostrarMensaje("Registro Exitoso", 1);
			listaEstadoCliente = dNEstadoCliente.listaEstadoCliente();
			limpiar();

		} else {
			mensajes.mostrarMensaje("Debe ingresar la descripción", 2);
		}

	}

	public void limpiar() {

		descripcion = null;
	}

	public void limpiarModificar() {
		estadoClienteSeleccionado = null;
		descripcionModificar = null;
		estadoModificar = null;
	}
	public void cargarModificar() {

		descripcionModificar = estadoClienteSeleccionado.getDescripcion();
		estadoModificar = estadoClienteSeleccionado.getEstado();
	}
	public void guardarModificar() throws Exception {
		if (dNEstadoCliente == null) {
			dNEstadoCliente = new DNEstadoCliente();
		}
		if (estadoClienteSeleccionado != null && descripcionModificar.length() > 0) {

			estadoClienteSeleccionado.setDescripcion(descripcionModificar);
			estadoClienteSeleccionado.setEstado(estadoModificar);

			dNEstadoCliente.modificarEstadoCliente(estadoClienteSeleccionado);

			mensajes.mostrarMensaje("Registro Moficado Exitosamente ", 1);
			listaEstadoCliente = dNEstadoCliente.listaEstadoCliente();
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

	public DNEstadoCliente getdNEstadoCliente() {
		return dNEstadoCliente;
	}

	public void setdNEstadoCliente(DNEstadoCliente dNEstadoCliente) {
		this.dNEstadoCliente = dNEstadoCliente;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<EstadoCliente> getListaEstadoCliente() {
		return listaEstadoCliente;
	}

	public void setListaEstadoCliente(List<EstadoCliente> listaEstadoCliente) {
		this.listaEstadoCliente = listaEstadoCliente;
	}

	public EstadoCliente getEstadoClienteSeleccionado() {
		return estadoClienteSeleccionado;
	}

	public void setEstadoClienteSeleccionado(EstadoCliente estadoClienteSeleccionado) {
		this.estadoClienteSeleccionado = estadoClienteSeleccionado;
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
