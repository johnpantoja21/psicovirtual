package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNTipoCita;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Tema;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.TipoCita;

@ManagedBean(name = "MBTipoCita")
@SessionScoped
public class MBTipoCita {
	MBMensajes mensajes = new MBMensajes();

	DNTipoCita dNTipoCita;

	private String descripcion;

	private List<TipoCita> listaTipoCita;
	private TipoCita tipoCitaSeleccionado;


	private String estadoModificar;
	private String descripcionModificar;

	
	public MBTipoCita() throws Exception {

		if (dNTipoCita == null) {
			dNTipoCita = new DNTipoCita();
		}
		tipoCitaSeleccionado= new  TipoCita();
		listaTipoCita = dNTipoCita.listaTipoCita();
	}

	public void guardar() throws Exception {
		if (dNTipoCita == null) {
			dNTipoCita = new DNTipoCita();
		}
	
		if (descripcion.length() > 0) {

			TipoCita insert = new TipoCita();
			insert.setDescripcion(descripcion);
			insert.setEstado("ACTIVO");
			dNTipoCita.guardarTipoCita(insert);
			mensajes.mostrarMensaje("Registro Exitoso", 1);
			listaTipoCita = dNTipoCita.listaTipoCita();
			limpiar();

		} else {
			mensajes.mostrarMensaje("Debe ingresar la descripción", 2);
		}

	}

	public void limpiar() {

		descripcion = null;
	}

	public void limpiarModificar() {
		tipoCitaSeleccionado = null;

		descripcionModificar = null;
		estadoModificar = null;
	}

	public void cargarModificar() {

		descripcionModificar = tipoCitaSeleccionado.getDescripcion();
		estadoModificar = tipoCitaSeleccionado.getEstado();
	}

	public void guardarModificar() throws Exception {
		if (dNTipoCita == null) {
			dNTipoCita = new DNTipoCita();
		}
		if (tipoCitaSeleccionado!=null && descripcionModificar.length() > 0) {

			tipoCitaSeleccionado.setDescripcion(descripcionModificar);
			tipoCitaSeleccionado.setEstado(estadoModificar);
	
			dNTipoCita.modificarTipoCita(tipoCitaSeleccionado);
			mensajes.mostrarMensaje("Registro Moficado Exitosamente ", 1);
			listaTipoCita = dNTipoCita.listaTipoCita();
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

	
	
	

	public DNTipoCita getdNTipoCita() {
		return dNTipoCita;
	}

	public void setdNTipoCita(DNTipoCita dNTipoCita) {
		this.dNTipoCita = dNTipoCita;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<TipoCita> getListaTipoCita() {
		return listaTipoCita;
	}

	public void setListaTipoCita(List<TipoCita> listaTipoCita) {
		this.listaTipoCita = listaTipoCita;
	}

	public TipoCita getTipoCitaSeleccionado() {
		return tipoCitaSeleccionado;
	}

	public void setTipoCitaSeleccionado(TipoCita tipoCitaSeleccionado) {
		this.tipoCitaSeleccionado = tipoCitaSeleccionado;
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




	

}
