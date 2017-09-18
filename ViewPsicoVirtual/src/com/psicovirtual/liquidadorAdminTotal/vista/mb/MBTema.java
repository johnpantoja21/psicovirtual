package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNTema;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Tema;

@ManagedBean(name = "MBTema")
@SessionScoped
public class MBTema {
	MBMensajes mensajes = new MBMensajes();



	private String descripcion;

	private String descripcionModificar;
	DNTema dNTema;
	private List<Tema> listaTema;
	private Tema temaSeleccionado;
	private String estadoModificar;

	public MBTema() throws Exception {

		if (dNTema == null) {
			dNTema = new DNTema();
		}
		temaSeleccionado = new Tema();
		listaTema = dNTema.listaTema();
	}

	public void guardar() throws Exception {
		if (dNTema == null) {
			dNTema = new DNTema();
		}

		if (descripcion.length() > 0) {

			Tema insert = new Tema();
			insert.setDescripcion(descripcion);
			insert.setEstado("ACTIVO");
			dNTema.guardarTema(insert);

			mensajes.mostrarMensaje("Registro Exitoso", 1);
			listaTema = dNTema.listaTema();
			limpiar();

		} else {
			mensajes.mostrarMensaje("Debe ingresar la descripción", 2);
		}

	}

	public void limpiar() {

		descripcion = null;
	}

	public void limpiarModificar() {
		temaSeleccionado = null;
		descripcionModificar = null;
		estadoModificar = null;
	}

	public void cargarModificar() {

		descripcionModificar = temaSeleccionado.getDescripcion();
		estadoModificar = temaSeleccionado.getEstado();
	}

	public void guardarModificar() throws Exception {
		if (dNTema == null) {
			dNTema = new DNTema();
		}
		if (temaSeleccionado != null && descripcionModificar.length() > 0) {

			temaSeleccionado.setDescripcion(descripcionModificar);
			temaSeleccionado.setEstado(estadoModificar);
			dNTema.modificarTema(temaSeleccionado);

			mensajes.mostrarMensaje("Registro Moficado Exitosamente ", 1);
			listaTema = dNTema.listaTema();
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

	public DNTema getdNTema() {
		return dNTema;
	}

	public void setdNTema(DNTema dNTema) {
		this.dNTema = dNTema;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Tema> getListaTema() {
		return listaTema;
	}

	public void setListaTema(List<Tema> listaTema) {
		this.listaTema = listaTema;
	}

	public Tema getTemaSeleccionado() {
		return temaSeleccionado;
	}

	public void setTemaSeleccionado(Tema temaSeleccionado) {
		this.temaSeleccionado = temaSeleccionado;
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
