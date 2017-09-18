package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNHistoriaClinica;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNTema;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNUsuarios;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Cita;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.SeccionHistorial;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Tema;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Usuario;

@ManagedBean(name = "MBHistoriaClinica")
@SessionScoped
public class MBHistoriaClinica {

	MBMensajes mensajes = new MBMensajes();
	DNTema dNTema;
	private List<Tema> listaTema;
	private List<Tema> filterListaTema;
	private Tema temaSeleccionado;

	DNHistoriaClinica dNHistoriaClinica;
	private List<Cita> listCitasPagadas;
	private List<Cita> filterListCitasPagadas;
	private Cita seleccionCita;
	DNUsuarios dNUsuario;
	private String historiaClinica;
	private List<SeccionHistorial> listHistorial;
	private List<SeccionHistorial> filterListHistorial;
	
	
	public MBHistoriaClinica() {

	}

	public void cargarCitadasPagadas(String user) throws Exception {

		if (dNHistoriaClinica == null) {
			dNHistoriaClinica = new DNHistoriaClinica();
		}

		if (dNUsuario == null) {
			dNUsuario = new DNUsuarios();
		}

		if (dNTema == null) {
			dNTema = new DNTema();
		}

		Usuario usuarioModificar = dNUsuario.consultarDetalleUsuarioByUsuario(user);

		listCitasPagadas = dNHistoriaClinica.consultarCitasPagadasClientePsico(usuarioModificar);
		listaTema = dNTema.listaTema();
		listHistorial = dNHistoriaClinica.buscarHistoriaClinicaCiente(usuarioModificar);

	}

	public void guardarHistoria(String user) throws Exception {
		if (dNHistoriaClinica == null) {
			dNHistoriaClinica = new DNHistoriaClinica();
		}
		// Usuario usuarioModificar =
		// dNUsuario.consultarDetalleUsuarioByUsuario(user);

		if (seleccionCita != null) {
			if (temaSeleccionado != null) {
				if (historiaClinica != null) {
					SeccionHistorial historia = new SeccionHistorial();
					historia.setCita(seleccionCita);
					historia.setTema(temaSeleccionado);
					historia.setDescripcion(historiaClinica);
					Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
					Date date = new java.sql.Date(timeStamp.getTime());
					historia.setFechaHistorial(date);

					dNHistoriaClinica.guardarHistoria(historia);
					mensajes.mostrarMensaje("Registro con exito.", 1);
				} else {
					mensajes.mostrarMensaje("Debe establecer una historia clinica.", 3);
				}
			} else {
				mensajes.mostrarMensaje("Selecione un tema. Valide de nuevo.", 3);
			}
		} else {
			mensajes.mostrarMensaje("Seleccione un Cliente.", 3);
		}

	}
	
	
	public List<SeccionHistorial> getListHistorial() {
		return listHistorial;
	}

	public void setListHistorial(List<SeccionHistorial> listHistorial) {
		this.listHistorial = listHistorial;
	}

	public List<SeccionHistorial> getFilterListHistorial() {
		return filterListHistorial;
	}

	public void setFilterListHistorial(List<SeccionHistorial> filterListHistorial) {
		this.filterListHistorial = filterListHistorial;
	}

	public List<Tema> getFilterListaTema() {
		return filterListaTema;
	}

	public void setFilterListaTema(List<Tema> filterListaTema) {
		this.filterListaTema = filterListaTema;
	}

	public List<Cita> getFilterListCitasPagadas() {
		return filterListCitasPagadas;
	}

	public void setFilterListCitasPagadas(List<Cita> filterListCitasPagadas) {
		this.filterListCitasPagadas = filterListCitasPagadas;
	}

	public String getHistoriaClinica() {
		return historiaClinica;
	}

	public void setHistoriaClinica(String historiaClinica) {
		this.historiaClinica = historiaClinica;
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

	public Cita getSeleccionCita() {
		return seleccionCita;
	}

	public void setSeleccionCita(Cita seleccionCita) {
		this.seleccionCita = seleccionCita;
	}

	public MBMensajes getMensajes() {
		return mensajes;
	}

	public void setMensajes(MBMensajes mensajes) {
		this.mensajes = mensajes;
	}

	public List<Cita> getListCitasPagadas() {
		return listCitasPagadas;
	}

	public void setListCitasPagadas(List<Cita> listCitasPagadas) {
		this.listCitasPagadas = listCitasPagadas;
	}

}
