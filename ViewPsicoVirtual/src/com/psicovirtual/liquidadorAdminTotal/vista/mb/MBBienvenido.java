package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.UploadedFile;

import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNEstadoCita;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNEstadoCliente;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNTipoCita;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNTipoUsuario;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNUsuarios;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Cita;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.ClientesPsicologo;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.EstadoCliente;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Horario;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Servicio;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Tema;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.TipoCita;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Usuario;

@ManagedBean(name = "MBBienvenido")
@SessionScoped
public class MBBienvenido {
	MBMensajes mensajes = new MBMensajes();
	DNEstadoCita dNEstadoCita;
	
	DNTipoCita dNTipoCita;
	DNEstadoCliente dNEstadoCliente;
	
	DNUsuarios dNUsuario;
	private List<Cita> listaPendientes;

	private ScheduleModel eventModel;
	private ScheduleEvent event = new DefaultScheduleEvent();

	public Date getRandomDate(Date base) {
		Calendar date = Calendar.getInstance();
		date.setTime(base);
		date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1);
		return date.getTime();
	}

	public MBBienvenido()  {
		
	}

	public void onEventSelect(SelectEvent selectEvent) {
		event = (ScheduleEvent) selectEvent.getObject();
	}

	public void cargarUsuario(String user) throws Exception {
		System.out.println("Entro");
		if (dNUsuario == null) {
			dNUsuario = new DNUsuarios();
		}

		Usuario usuarioModificar = dNUsuario.consultarDetalleUsuarioByUsuario(user);

		if (usuarioModificar.getTipoUsuario().getIdTipoUsu() == 3) {

			listaPendientes = dNUsuario.listaCitasPendientesCliente(usuarioModificar);

			eventModel = new DefaultScheduleModel();
			for (int i = 0; i < listaPendientes.size(); i++) {

				eventModel.addEvent(new DefaultScheduleEvent(
						"Cita: " + listaPendientes.get(i).getTipoCita().getDescripcion() + "\n Nombre Psicologo: "
								+ listaPendientes.get(i).getClientesPsicologo().getUsuario2().getNombre() + " "
								+ listaPendientes.get(i).getClientesPsicologo().getUsuario2().getApellidos(),
						listaPendientes.get(i).getHorario().getFechaInicial(),
						listaPendientes.get(i).getHorario().getFechaFinal()));

			}
		}

		if (usuarioModificar.getTipoUsuario().getIdTipoUsu() == 2) {

			listaPendientes = dNUsuario.listaCitasPendientes(usuarioModificar);
			eventModel = new DefaultScheduleModel();
			for (int i = 0; i < listaPendientes.size(); i++) {

				eventModel.addEvent(new DefaultScheduleEvent(
						"Cita: " + listaPendientes.get(i).getTipoCita().getDescripcion() + "\n Nombre: "
								+ listaPendientes.get(i).getClientesPsicologo().getUsuario1().getNombre() + " "
								+ listaPendientes.get(i).getClientesPsicologo().getUsuario1().getApellidos(),
						listaPendientes.get(i).getHorario().getFechaInicial(),
						listaPendientes.get(i).getHorario().getFechaFinal()));

			}
		}

	}

	public void setToBean() {

	}

	public List<Cita> getListaPendientes() {
		return listaPendientes;
	}

	public void setListaPendientes(List<Cita> listaPendientes) {
		this.listaPendientes = listaPendientes;
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	public MBMensajes getMensajes() {
		return mensajes;
	}

	public void setMensajes(MBMensajes mensajes) {
		this.mensajes = mensajes;
	}

	public DNUsuarios getdNUsuario() {
		return dNUsuario;
	}

	public void setdNUsuario(DNUsuarios dNUsuario) {
		this.dNUsuario = dNUsuario;
	}

	public DNTipoCita getdNTipoCita() {
		return dNTipoCita;
	}

	public void setdNTipoCita(DNTipoCita dNTipoCita) {
		this.dNTipoCita = dNTipoCita;
	}

	public DNEstadoCliente getdNEstadoCliente() {
		return dNEstadoCliente;
	}

	public void setdNEstadoCliente(DNEstadoCliente dNEstadoCliente) {
		this.dNEstadoCliente = dNEstadoCliente;
	}

}
