package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNServicio;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNTiposServicio;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNUsuarios;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Servicio;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.TiposServicio;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Usuario;

@ManagedBean(name = "MBServicio")
@SessionScoped
public class MBServicio {
	MBMensajes mensajes = new MBMensajes();

	DNServicio dNServicio;
	DNTiposServicio dNTiposServicio;
	DNUsuarios dNUsuario;

	private String nombreServicio;
	private int precioServicio;
	private Servicio servicioSeleccionado;

	private List<Servicio> listaServicio;
	private TiposServicio tipoServicioSeleccionado;
	private List<TiposServicio> listaTipoServicio;
	private List<TiposServicio> listaFiltroTipoServicio;

	private String nombreServicioModificar;
	private int precioServicioModificar;

	public MBServicio() {

	}

	public void cargarDatos(String userPsicologo) throws Exception {

		if (dNServicio == null) {
			dNServicio = new DNServicio();
		}

		if (dNUsuario == null) {
			dNUsuario = new DNUsuarios();
		}

		if (dNTiposServicio == null) {
			dNTiposServicio = new DNTiposServicio();
		}

		Usuario x = dNUsuario.consultarDetalleUsuarioByUsuario(userPsicologo);

		listaServicio = dNServicio.listaServicioPsicologo(x);
		listaTipoServicio = dNTiposServicio.listaTiposServicioActivos();

		servicioSeleccionado = new Servicio();
	}

	public void guardar(String userPsicologo) throws Exception {
		System.out.println("Entro a limpiarg");
		if (dNServicio == null) {
			dNServicio = new DNServicio();
		}

		if (dNUsuario == null) {
			dNUsuario = new DNUsuarios();
		}

		Usuario x = dNUsuario.consultarDetalleUsuarioByUsuario(userPsicologo);

		Servicio insert = new Servicio();

		if (tipoServicioSeleccionado != null) {

			insert.setTiposServicio(tipoServicioSeleccionado);
			insert.setUsuario(x);

			insert.setNombreServicio(nombreServicio);
			insert.setPrecio(precioServicio);
			insert.setEstado("ACTIVO");

			dNServicio.guardarServicio(insert);
			mensajes.mostrarMensaje("Registro Exitoso", 1);

			listaServicio = dNServicio.listaServicio();
			limpiar();
		} else {
			mensajes.mostrarMensaje("Debe seleccionar el tipo de servicio", 2);
		}
	}

	public void limpiar() {

		nombreServicio = null;
		precioServicio = 0;
		servicioSeleccionado = new Servicio();
		tipoServicioSeleccionado = new TiposServicio();
	}

	public void limpiarModifica() {
		servicioSeleccionado = new Servicio();
		nombreServicioModificar = null;
		precioServicioModificar = 0;

	}

	public void cargarModificar() {
		nombreServicioModificar = servicioSeleccionado.getNombreServicio();
		precioServicioModificar = servicioSeleccionado.getPrecio();

	}

	public void prueba() {

		System.out.println("Probando");
	}

	public void guardarModifica() throws Exception {

		if (dNServicio == null) {
			dNServicio = new DNServicio();
		}
		
		servicioSeleccionado.setNombreServicio(nombreServicioModificar);
		servicioSeleccionado.setPrecio(precioServicioModificar);
		dNServicio.modificarServicio(servicioSeleccionado);

		mensajes.mostrarMensaje("Registro Moficado Exitosamente ", 1);
		listaServicio = dNServicio.listaServicioPsicologo(servicioSeleccionado.getUsuario());
		limpiarModifica();

	}

	public List<TiposServicio> getListaTipoServicio() {
		return listaTipoServicio;
	}

	public void setListaTipoServicio(List<TiposServicio> listaTipoServicio) {
		this.listaTipoServicio = listaTipoServicio;
	}

	public List<TiposServicio> getListaFiltroTipoServicio() {
		return listaFiltroTipoServicio;
	}

	public void setListaFiltroTipoServicio(List<TiposServicio> listaFiltroTipoServicio) {
		this.listaFiltroTipoServicio = listaFiltroTipoServicio;
	}

	public int getPrecioServicioModificar() {
		return precioServicioModificar;
	}

	public void setPrecioServicioModificar(int precioServicioModificar) {
		this.precioServicioModificar = precioServicioModificar;
	}

	public int getPrecioServicio() {
		return precioServicio;
	}

	public void setPrecioServicio(int precioServicio) {
		this.precioServicio = precioServicio;
	}

	public String getNombreServicioModificar() {
		return nombreServicioModificar;
	}

	public void setNombreServicioModificar(String nombreServicioModificar) {
		this.nombreServicioModificar = nombreServicioModificar;
	}

	public List<Servicio> getListaServicio() {
		return listaServicio;
	}

	public void setListaServicio(List<Servicio> listaServicio) {
		this.listaServicio = listaServicio;
	}

	public Servicio getServicioSeleccionado() {
		return servicioSeleccionado;
	}

	public void setServicioSeleccionado(Servicio servicioSeleccionado) {
		this.servicioSeleccionado = servicioSeleccionado;
	}

	public TiposServicio getTipoServicioSeleccionado() {
		return tipoServicioSeleccionado;
	}

	public void setTipoServicioSeleccionado(TiposServicio tipoServicioSeleccionado) {
		this.tipoServicioSeleccionado = tipoServicioSeleccionado;
	}

	public MBMensajes getMensajes() {
		return mensajes;
	}

	public void setMensajes(MBMensajes mensajes) {
		this.mensajes = mensajes;
	}

	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

}
