package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNHistoriaClinica;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNTema;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNUsuarios;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Cita;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Tema;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Usuario;

@ManagedBean(name = "MBHistoriaClinica")
@SessionScoped
public class MBHistoriaClinica {

	MBMensajes mensajes = new MBMensajes();

	DNHistoriaClinica dNHistoriaClinica;
	private List<Cita> listCitasPagadas;
	private Cita seleccionCita;
	DNUsuarios dNUsuario;

	public MBHistoriaClinica() {

	}

	public void cargarCitadasPagadas(String user) throws Exception {

		if (dNHistoriaClinica == null) {
			dNHistoriaClinica = new DNHistoriaClinica();
		}

		if (dNUsuario == null) {
			dNUsuario = new DNUsuarios();
		}

		Usuario usuarioModificar = dNUsuario.consultarDetalleUsuarioByUsuario(user);

		listCitasPagadas = dNHistoriaClinica.consultarCitasPagadasClientePsico(usuarioModificar);

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
