package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNCita;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNEstadoCita;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNTema;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNUsuarios;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Cita;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.EstadoCita;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Horario;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Tema;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Usuario;

@ManagedBean(name = "MBCita")
@SessionScoped
public class MBCita {
	MBMensajes mensajes = new MBMensajes();

	DNCita dNCita;
	DNUsuarios dNUsuario;

	List<Cita> listCitasPendientes;

	public MBCita() {

	}

	public void cargarCitasPendienteCliente(String user) throws Exception {
		if (dNCita == null) {
			dNCita = new DNCita();
		}

		if (dNUsuario == null) {
			dNUsuario = new DNUsuarios();
		}
		listCitasPendientes = new ArrayList<Cita>();

		Usuario usuarioModificar = dNUsuario.consultarDetalleUsuarioByUsuario(user);

		listCitasPendientes = dNCita.consultarCitaClient(usuarioModificar);
	}

	DNEstadoCita dNEstadoCita;

	public void cancelarCita(Cita cita) throws Exception {

		if (dNCita == null) {
			dNCita = new DNCita();
		}

		if (dNEstadoCita == null) {
			dNEstadoCita = new DNEstadoCita();
		}

		if (dNUsuario == null) {
			dNUsuario = new DNUsuarios();
		}
		System.out.println("Entrando" + cita.getIdCita());
		EstadoCita estadoCi =

				
				dNEstadoCita.buscarEstadoCita(new BigDecimal("3"));

		// cita.getEstadoCita();
		// estadoCi.setIdEstadoCita(new BigDecimal("3"));

		cita.setEstadoCita(estadoCi);

		Horario cancelado = dNUsuario.buscarHorario(cita.getHorario().getIdHorario());

		cancelado.setEstado("ACTIVO");

		dNUsuario.modificarHorario(cancelado);

		cita.setHorario(null);

		dNCita.modificarCita(cita);
		mensajes.mostrarMensaje("Cita Cancelada con exito", 1);
		cargarCitasPendienteCliente(cita.getClientesPsicologo().getUsuario1().getUsuario());
		System.out.println("Termino");
		// Enviar correo al psicologo
	}

	Cita citaSel;

	private String valorCita;

	public void pagarCita(Cita cita) {

		citaSel = cita;
		valorCita = cita.getValorPago() + "";
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('dlg2').show();");

	}

	public void paga() throws Exception {

		if (dNCita == null) {
			dNCita = new DNCita();
		}
		if (dNEstadoCita == null) {
			dNEstadoCita = new DNEstadoCita();
		}
		EstadoCita estadoCi =

				dNEstadoCita.buscarEstadoCita(new BigDecimal("2"));
		citaSel.setEstadoCita(estadoCi);
		citaSel.setEstadoPago("PAGADO");
		dNCita.modificarCita(citaSel);

	}

	public List<Cita> getListCitasPendientes() {
		return listCitasPendientes;
	}

	public void setListCitasPendientes(List<Cita> listCitasPendientes) {
		this.listCitasPendientes = listCitasPendientes;
	}

	public MBMensajes getMensajes() {
		return mensajes;
	}

	public void setMensajes(MBMensajes mensajes) {
		this.mensajes = mensajes;
	}

	public DNCita getdNCita() {
		return dNCita;
	}

	public void setdNCita(DNCita dNCita) {
		this.dNCita = dNCita;
	}

	public DNUsuarios getdNUsuario() {
		return dNUsuario;
	}

	public void setdNUsuario(DNUsuarios dNUsuario) {
		this.dNUsuario = dNUsuario;
	}

	public DNEstadoCita getdNEstadoCita() {
		return dNEstadoCita;
	}

	public void setdNEstadoCita(DNEstadoCita dNEstadoCita) {
		this.dNEstadoCita = dNEstadoCita;
	}

	public Cita getCitaSel() {
		return citaSel;
	}

	public void setCitaSel(Cita citaSel) {
		this.citaSel = citaSel;
	}

	public String getValorCita() {
		return valorCita;
	}

	public void setValorCita(String valorCita) {
		this.valorCita = valorCita;
	}

}
