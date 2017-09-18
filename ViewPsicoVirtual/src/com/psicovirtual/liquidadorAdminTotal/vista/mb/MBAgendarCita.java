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

@ManagedBean(name = "MBAgendarCita")
@SessionScoped
public class MBAgendarCita {
	MBMensajes mensajes = new MBMensajes();
	DNEstadoCita dNEstadoCita;
	DNUsuarios dNUsuario;
	DNTipoCita dNTipoCita;
	DNEstadoCliente dNEstadoCliente;

	
	private String usuarioCliente;

	private List<ClientesPsicologo> listaClientesPsicologo;

	private ClientesPsicologo clientesPsicologoSeleccionado;

	private List<Servicio> listaServicio;

	private Servicio servicioSeleccionado;

	private ArrayList<SelectItem> listaTipoCita;

	private ArrayList<SelectItem> listaEstadoCliente;

	private String tipoCitaSeleccionado;

	private String estadoClienteSeleccionado;

	private Date fechaProgramada;
	private Horario fechaSeleccionada;

	private List<Horario> listaHorarios;
	private List<Cita> listaPendientes;

	private int cantidadHora;
	private int precioHora;

	private ScheduleModel eventModel;
	private ScheduleEvent event = new DefaultScheduleEvent();

	public Date getRandomDate(Date base) {
		Calendar date = Calendar.getInstance();
		date.setTime(base);
		date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1);
		return date.getTime();
	}

	public MBAgendarCita() throws Exception {
		
	}

	public void cargar(String user) throws Exception {

		limpiarObjetos();

		if (dNUsuario == null) {
			dNUsuario = new DNUsuarios();
		}

		if (dNTipoCita == null) {
			dNTipoCita = new DNTipoCita();
		}

		if (dNEstadoCliente == null) {
			dNEstadoCliente = new DNEstadoCliente();
		}

		Usuario usuarioModificar = dNUsuario.consultarDetalleUsuarioByUsuario(user);

		listaClientesPsicologo = dNUsuario.listaClientePsicologo(usuarioModificar.getIdUsuario() + "");

		listaTipoCita = llenarItemsTipoCita(dNTipoCita.listaTipoCitaActivos());
		listaEstadoCliente = llenarItemsEstadoCliente(dNEstadoCliente.listaEstadoClienteActivos());

	}

	public void limpiarObjetos() {
		usuarioCliente = "";

		listaClientesPsicologo = new ArrayList<ClientesPsicologo>();

		clientesPsicologoSeleccionado = new ClientesPsicologo();

		listaServicio = new ArrayList<Servicio>();

		servicioSeleccionado = new Servicio();

		listaTipoCita = new ArrayList<SelectItem>();

		listaEstadoCliente = new ArrayList<SelectItem>();

		tipoCitaSeleccionado = "";

		estadoClienteSeleccionado = "";

		fechaProgramada = new Date();
		fechaSeleccionada = new Horario();

		listaHorarios = new ArrayList<Horario>();
	}

	public void guardarCita(String user) throws Exception {
		if (dNUsuario == null) {
			dNUsuario = new DNUsuarios();
		}

		if (dNTipoCita == null) {
			dNTipoCita = new DNTipoCita();
		}

		if (dNEstadoCliente == null) {
			dNEstadoCliente = new DNEstadoCliente();
		}
		if (dNEstadoCita == null) {
			dNEstadoCita = new DNEstadoCita();
		}
		if (clientesPsicologoSeleccionado != null) {

			if (servicioSeleccionado != null) {

				if (tipoCitaSeleccionado != null) {

					// if (estadoClienteSeleccionado != null) {

					if (fechaSeleccionada != null) {

						Cita insert = new Cita();
						insert.setClientesPsicologo(clientesPsicologoSeleccionado);
						insert.setServicio(servicioSeleccionado);

						insert.setTipoCita(dNTipoCita.buscarTipoCita(new BigDecimal(tipoCitaSeleccionado)));
						// insert.setEstadoCliente(
						// dNEstadoCliente.buscarEstadoCliente(new
						// BigDecimal(estadoClienteSeleccionado)));
						insert.setHorario(fechaSeleccionada);
						insert.setEstadoCita(dNEstadoCita.buscarEstadoCita(new BigDecimal("1")));
						insert.setValorPago(precioHora);
						insert.setEstadoPago("PENDIENTE");
						fechaSeleccionada.setEstado("ASIGNADO");
						dNUsuario.modificarHorario(fechaSeleccionada);

						dNUsuario.guardarCita(insert);
						mensajes.mostrarMensaje("Cita Agendada Con Exito", 1);
						cargar(user);

					} else {
						mensajes.mostrarMensaje("Debe seleccionar La Fecha De La Cita", 2);
					}

					// } else {
					// mensajes.mostrarMensaje("Debe Seleccionar El Estado
					// Cliente", 2);
					// }

				} else {
					mensajes.mostrarMensaje("Debe Seleccionar El Tipo Cita", 2);
				}

			} else {
				mensajes.mostrarMensaje("Debe Seleccionar El Servicio", 2);
			}

		} else {
			mensajes.mostrarMensaje("Debe Seleccionar El Psicologo", 2);
		}

	}

	public void onEventSelect(SelectEvent selectEvent) {
		event = (ScheduleEvent) selectEvent.getObject();
	}

	public void cargarUsuario(String user) throws Exception {

		if (dNUsuario == null) {
			dNUsuario = new DNUsuarios();
		}

		Usuario usuarioModificar = dNUsuario.consultarDetalleUsuarioByUsuario(user);

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

	public void setToBean() {

	}

	public void cargarHora() {
		int diferenciaHora = fechaSeleccionada.getFechaInicial().getHours()
				- fechaSeleccionada.getFechaFinal().getHours();
		cantidadHora = (diferenciaHora * -1);
		precioHora = servicioSeleccionado.getPrecio() * cantidadHora;

	}

	public ArrayList<SelectItem> llenarItemsEstadoCliente(List<EstadoCliente> estadoCliente) throws Exception {

		ArrayList<SelectItem> itemsMedida = new ArrayList<SelectItem>();
		for (EstadoCliente lista : estadoCliente) {
			itemsMedida.add(new SelectItem(lista.getIdEstadoClie(), lista.getDescripcion()));
		}
		return itemsMedida;
	}

	public ArrayList<SelectItem> llenarItemsTipoCita(List<TipoCita> tipoCita) throws Exception {

		ArrayList<SelectItem> itemsMedida = new ArrayList<SelectItem>();
		for (TipoCita lista : tipoCita) {
			itemsMedida.add(new SelectItem(lista.getIdTipoCita(), lista.getDescripcion()));
		}
		return itemsMedida;
	}

	public void cargarServicios() throws Exception {
		if (dNUsuario == null) {
			dNUsuario = new DNUsuarios();
		}

		if (clientesPsicologoSeleccionado != null) {

			listaServicio = dNUsuario
					.listaServiciosPsicologo(clientesPsicologoSeleccionado.getUsuario2().getIdUsuario() + "");

			listaHorarios = dNUsuario.listaHorarioPsicologoDisponible(clientesPsicologoSeleccionado.getUsuario2());
		}

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

	public int getCantidadHora() {
		return cantidadHora;
	}

	public void setCantidadHora(int cantidadHora) {
		this.cantidadHora = cantidadHora;
	}

	public int getPrecioHora() {
		return precioHora;
	}

	public void setPrecioHora(int precioHora) {
		this.precioHora = precioHora;
	}

	public Horario getFechaSeleccionada() {
		return fechaSeleccionada;
	}

	public void setFechaSeleccionada(Horario fechaSeleccionada) {
		this.fechaSeleccionada = fechaSeleccionada;
	}

	public List<Horario> getListaHorarios() {
		return listaHorarios;
	}

	public void setListaHorarios(List<Horario> listaHorarios) {
		this.listaHorarios = listaHorarios;
	}

	public String getUsuarioCliente() {
		return usuarioCliente;
	}

	public void setUsuarioCliente(String usuarioCliente) {
		this.usuarioCliente = usuarioCliente;
	}

	public List<ClientesPsicologo> getListaClientesPsicologo() {
		return listaClientesPsicologo;
	}

	public void setListaClientesPsicologo(List<ClientesPsicologo> listaClientesPsicologo) {
		this.listaClientesPsicologo = listaClientesPsicologo;
	}

	public ClientesPsicologo getClientesPsicologoSeleccionado() {
		return clientesPsicologoSeleccionado;
	}

	public void setClientesPsicologoSeleccionado(ClientesPsicologo clientesPsicologoSeleccionado) {
		this.clientesPsicologoSeleccionado = clientesPsicologoSeleccionado;
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

	public ArrayList<SelectItem> getListaTipoCita() {
		return listaTipoCita;
	}

	public void setListaTipoCita(ArrayList<SelectItem> listaTipoCita) {
		this.listaTipoCita = listaTipoCita;
	}

	public ArrayList<SelectItem> getListaEstadoCliente() {
		return listaEstadoCliente;
	}

	public void setListaEstadoCliente(ArrayList<SelectItem> listaEstadoCliente) {
		this.listaEstadoCliente = listaEstadoCliente;
	}

	public String getTipoCitaSeleccionado() {
		return tipoCitaSeleccionado;
	}

	public void setTipoCitaSeleccionado(String tipoCitaSeleccionado) {
		this.tipoCitaSeleccionado = tipoCitaSeleccionado;
	}

	public String getEstadoClienteSeleccionado() {
		return estadoClienteSeleccionado;
	}

	public void setEstadoClienteSeleccionado(String estadoClienteSeleccionado) {
		this.estadoClienteSeleccionado = estadoClienteSeleccionado;
	}

	public Date getFechaProgramada() {
		return fechaProgramada;
	}

	public void setFechaProgramada(Date fechaProgramada) {
		this.fechaProgramada = fechaProgramada;
	}

}
