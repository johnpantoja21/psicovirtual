package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNTipoUsuario;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.TipoUsuario;

@ManagedBean(name = "MBTipoUsuario")
@SessionScoped
public class MBTipoUsuario {
	MBMensajes mensajes = new MBMensajes();

	DNTipoUsuario dNTipoUsuario;

	private String nombreTipo;

	private List<TipoUsuario> listaTipoUsuario;
	private TipoUsuario tipoUsuarioSeleccionado;

	private String nombreTipoModificar;
	private String estadoModificar;
	private String descripcionModificar;

	private String descripcion;
	
	
	
	public MBTipoUsuario() throws Exception {

		if (dNTipoUsuario == null) {
			dNTipoUsuario = new DNTipoUsuario();
		}
		listaTipoUsuario = dNTipoUsuario.listaTipoUsuario();
		tipoUsuarioSeleccionado = new TipoUsuario();
	}

	public void guardar() throws Exception {
		if (dNTipoUsuario == null) {
			dNTipoUsuario = new DNTipoUsuario();
		}
		System.out.println("paso");
		if (nombreTipo.length() > 0) {

			TipoUsuario insert = new TipoUsuario();
			insert.setNombre(nombreTipo);
			insert.setDescripcion(descripcion);
			insert.setEstado("ACTIVO");
			dNTipoUsuario.guardarTipoUsuario(insert);
			mensajes.mostrarMensaje("Registro Exitoso", 1);
			listaTipoUsuario = dNTipoUsuario.listaTipoUsuario();
			limpiar();

		} else {
			mensajes.mostrarMensaje("Debe ingresar el nombre del tipo de usuario", 2);
		}

	}

	public void limpiar() {

		nombreTipo = null;
		descripcion=null;
	}

	public void limpiarModificar() {
		tipoUsuarioSeleccionado = null;
		descripcionModificar=null;
		nombreTipoModificar = null;
		estadoModificar = null;
	}

	public void cargarModificar() {

		nombreTipoModificar = tipoUsuarioSeleccionado.getNombre();
		estadoModificar = tipoUsuarioSeleccionado.getEstado();
		descripcionModificar=tipoUsuarioSeleccionado.getDescripcion();
	}

	public void guardarModificar() throws Exception {
		if (dNTipoUsuario == null) {
			dNTipoUsuario = new DNTipoUsuario();
		}

		if (tipoUsuarioSeleccionado != null && nombreTipoModificar.length() > 0 ) {

			tipoUsuarioSeleccionado.setNombre(nombreTipoModificar);
			tipoUsuarioSeleccionado.setEstado(estadoModificar);
			tipoUsuarioSeleccionado.setDescripcion(descripcionModificar);
			dNTipoUsuario.modificarTipoUsuario(tipoUsuarioSeleccionado);
			mensajes.mostrarMensaje("Registro Moficado Exitosamente ", 1);
			listaTipoUsuario = dNTipoUsuario.listaTipoUsuario();
			limpiarModificar();

		} else {
			mensajes.mostrarMensaje("Debe ingresar el nombre del tipo de usuario", 2);
		}

	}

	public MBMensajes getMensajes() {
		return mensajes;
	}

	public void setMensajes(MBMensajes mensajes) {
		this.mensajes = mensajes;
	}

	public DNTipoUsuario getdNTipoUsuario() {
		return dNTipoUsuario;
	}

	public void setdNTipoUsuario(DNTipoUsuario dNTipoUsuario) {
		this.dNTipoUsuario = dNTipoUsuario;
	}

	public String getNombreTipo() {
		return nombreTipo;
	}

	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}

	public List<TipoUsuario> getListaTipoUsuario() {
		return listaTipoUsuario;
	}

	public void setListaTipoUsuario(List<TipoUsuario> listaTipoUsuario) {
		this.listaTipoUsuario = listaTipoUsuario;
	}

	public TipoUsuario getTipoUsuarioSeleccionado() {
		return tipoUsuarioSeleccionado;
	}

	public void setTipoUsuarioSeleccionado(TipoUsuario tipoUsuarioSeleccionado) {
		this.tipoUsuarioSeleccionado = tipoUsuarioSeleccionado;
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

	public String getDescripcionModificar() {
		return descripcionModificar;
	}

	public void setDescripcionModificar(String descripcionModificar) {
		this.descripcionModificar = descripcionModificar;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	
}
