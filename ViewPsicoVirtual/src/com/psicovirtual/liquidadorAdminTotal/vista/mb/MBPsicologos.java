package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
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

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
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

@ManagedBean(name = "MBPsicologos")
@SessionScoped
public class MBPsicologos implements Serializable {
	MBMensajes mensajes = new MBMensajes();
	DNEstadoCita dNEstadoCita;
	DNUsuarios dNUsuario;
	DNTipoCita dNTipoCita;

	DNEstadoCliente dNEstadoCliente;
	private List<Usuario> listaPsicologos;
	private List<Usuario> listaPsicologosFiltrada;
	private String nombreBusqueda;
	private String servicioBusqueda;

	private Usuario psicologoSelecionado;
	private Usuario psicologoSelecionadoInfo;

	private Date fechaInicial;
	private Date fechaFinal;

	private List<Horario> listaHorarios;
	private List<Cita> listaPendientes;

	private ScheduleModel eventModel;
	private ScheduleEvent event = new DefaultScheduleEvent();

	public Date getRandomDate(Date base) {
		Calendar date = Calendar.getInstance();
		date.setTime(base);
		date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1); // set random
																	// day of
																	// month

		return date.getTime();
	}

	public void guardarHorario(String user) throws Exception {

		if (dNUsuario == null) {
			dNUsuario = new DNUsuarios();
		}

		Usuario usuarioModificar = dNUsuario.consultarDetalleUsuarioByUsuario(user);
		Horario insert = new Horario();

		insert.setUsuario(usuarioModificar);

		if (fechaInicial != null || fechaFinal != null) {
			int diferenciaHora = fechaInicial.getHours() - fechaFinal.getHours();
			int diferenciaMinutos = fechaInicial.getMinutes() + fechaFinal.getMinutes();
			System.out.println("Diferencia minutos " + diferenciaMinutos);

			if (diferenciaHora == 0) {
				mensajes.mostrarMensaje("No se encuentra una hora definida. valide de nuevo", 3);
			} else {
				if (diferenciaMinutos == 0 || diferenciaMinutos == 60) {
					insert.setFechaInicial(fechaInicial);
					insert.setFechaFinal(fechaFinal);
					insert.setEstado("ACTIVO");

					dNUsuario.guardarHorario(insert);
					mensajes.mostrarMensaje("Registro exitoso", 1);
					listaHorarios = dNUsuario.listaHorarioPsicologo(usuarioModificar);
				} else {
					mensajes.mostrarMensaje("Valide los minutos entre rango de 1 hora", 3);
				}
			}

		} else {
			mensajes.mostrarMensaje("Debe ingresar una hora inicial y hora final", 3);
		}

	}

	public void cargarHorario(String user) throws Exception {
		if (dNUsuario == null) {
			dNUsuario = new DNUsuarios();
		}

		Usuario usuarioModificar = dNUsuario.consultarDetalleUsuarioByUsuario(user);

		listaHorarios = dNUsuario.listaHorarioPsicologo(usuarioModificar);
	}

	public void cargarUsuario(String user) throws Exception {
		if (dNUsuario == null) {
			dNUsuario = new DNUsuarios();
		}
	
		Usuario usuarioModificar = dNUsuario.consultarDetalleUsuarioByUsuario(user);
		
		psicologoSelecionado = usuarioModificar;
		
		listaPendientes = dNUsuario.listaCitasPendientes(psicologoSelecionado);
		
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

	public void onEventSelect(SelectEvent selectEvent) {
		event = (ScheduleEvent) selectEvent.getObject();
	}

	public void inactivar(Horario horario) throws Exception {

		if (dNUsuario == null) {
			dNUsuario = new DNUsuarios();
		}

		if (horario.getEstado().equalsIgnoreCase("ACTIVO")) {
			horario.setEstado("INACTIVO");
			dNUsuario.modificarHorario(horario);
		} else {
			horario.setEstado("ACTIVO");
			dNUsuario.modificarHorario(horario);

		}
	}

	public MBPsicologos() throws Exception {

		if (dNUsuario == null) {
			dNUsuario = new DNUsuarios();
		}

		listaPsicologos = dNUsuario.listarPsicologos();
		for (int i = 0; i < listaPsicologos.size(); i++) {

			if (listaPsicologos.get(i).getDescripcionPerfil().length() > 80) {
				listaPsicologos.get(i)
						.setDescripcionPerfil(listaPsicologos.get(i).getDescripcionPerfil().substring(0, 80) + "...");
			}

			List<Servicio> lista = new ArrayList<Servicio>();

			for (int j = 0; j < listaPsicologos.get(i).getServicios().size(); j++) {
				lista.add(listaPsicologos.get(i).getServicios().get(j));
				if (j == 2) {
					break;
				}
			}
			listaPsicologos.get(i).setServicios(lista);
		}
	}

	public void asignarPsicologoUsuario(Usuario parametroUsuario, String userCliente) throws Exception {
		MBClientePsicologo mbClientePsicologo = new MBClientePsicologo();
		ClientesPsicologo relacion = new ClientesPsicologo();

		Usuario usuarioPsicologo = dNUsuario.consultarDetalleUsuarioByUsuario(parametroUsuario.getUsuario()); // psico
		Usuario usuarioCliente = dNUsuario.consultarDetalleUsuarioByUsuario(userCliente);

		relacion.setUsuario1(usuarioCliente);
		relacion.setUsuario2(usuarioPsicologo);

		if (mbClientePsicologo.verificarRelacionExistente(relacion) <= 0) {
			if (mbClientePsicologo.guardarAsignacion(relacion) != null) {
				mensajes.mostrarMensaje("Asignacion exitosa", 1);
			}
		} else {
			mensajes.mostrarMensaje("El psicologo ya se encuentra asignado", 3);
		}
	}

	public void masInformacion(Usuario parameterUsuario) {
		// psicologoSelecionado = null;
		
		System.out.println("Nombre "+parameterUsuario.getNombre());
		
		 psicologoSelecionadoInfo = parameterUsuario;

		try {
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext extContext = context.getExternalContext();
			String url2 = extContext.encodeActionURL(context.getApplication().getViewHandler().getActionURL(context,
					"/view/gestion/informacionPsicologos.xhtml"));
			extContext.redirect(url2);
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}

	}

	public void regresarPsicologos() {
		try {

			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext extContext = context.getExternalContext();
			String url2 = extContext.encodeActionURL(context.getApplication().getViewHandler().getActionURL(context,
					"/view/gestion/busquedaPsicologos.xhtml"));
			extContext.redirect(url2);
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}

	}

	public void iniciar() throws Exception {

		if (dNUsuario == null) {
			dNUsuario = new DNUsuarios();
		}

		listaPsicologos = dNUsuario.listarPsicologos();
		for (int i = 0; i < listaPsicologos.size(); i++) {
			if (listaPsicologos.get(i).getDescripcionPerfil().length() > 80) {
				listaPsicologos.get(i)
						.setDescripcionPerfil(listaPsicologos.get(i).getDescripcionPerfil().substring(0, 80) + "...");
			}

			List<Servicio> lista = new ArrayList<Servicio>();

			for (int j = 0; j < listaPsicologos.get(i).getServicios().size(); j++) {
				lista.add(listaPsicologos.get(i).getServicios().get(j));
				if (j == 2) {
					break;
				}
			}
			listaPsicologos.get(i).setServicios(lista);

		}
	}

	public void buscar() throws Exception {

		listaPsicologos = dNUsuario.listarPsicologos();
		listaPsicologosFiltrada = new ArrayList<Usuario>();
		System.out.println("pso cargar");

		if (nombreBusqueda.length() > 0 && servicioBusqueda.length() > 0) {

			mensajes.mostrarMensaje("Solo Puede Buscar Por Nombre de Psicologo o Servicio", 2);

		} else {

			if (nombreBusqueda.length() > 0) {

				for (int i = 0; i < listaPsicologos.size(); i++) {
					String nombre = listaPsicologos.get(i).getNombre() + listaPsicologos.get(i).getApellidos();

					System.out.println("nombre " + nombre.toUpperCase());

					System.out.println("nombreBusqueda " + nombreBusqueda.toUpperCase());
					if (nombre.toUpperCase().contains(nombreBusqueda.toUpperCase())) {
						listaPsicologosFiltrada.add(listaPsicologos.get(i));
					}

				}

			}
			if (servicioBusqueda.length() > 0) {

				for (int i = 0; i < listaPsicologos.size(); i++) {

					for (int j = 0; j < listaPsicologos.get(i).getServicios().size(); j++) {
						String nombreServicio = listaPsicologos.get(i).getServicios().get(j).getNombreServicio();

						if (nombreServicio.toUpperCase().contains(servicioBusqueda.toUpperCase())) {
							listaPsicologosFiltrada.add(listaPsicologos.get(i));
							break;
						}

					}

				}

			}

			listaPsicologos = listaPsicologosFiltrada;
			if (nombreBusqueda.length() == 0 && servicioBusqueda.length() == 0) {
				listaPsicologos = dNUsuario.listarPsicologos();
			}

		}
		for (int l = 0; l < listaPsicologos.size(); l++) {

			if (listaPsicologos.get(l).getDescripcionPerfil().length() > 80) {
				listaPsicologos.get(l)
						.setDescripcionPerfil(listaPsicologos.get(l).getDescripcionPerfil().substring(0, 80) + "...");
			}

			List<Servicio> lista = new ArrayList<Servicio>();

			for (int j = 0; j < listaPsicologos.get(l).getServicios().size(); j++) {
				lista.add(listaPsicologos.get(l).getServicios().get(j));
				if (j == 2) {
					break;
				}
			}
			listaPsicologos.get(l).setServicios(lista);

		}

	}
	
	

	public Usuario getPsicologoSelecionadoInfo() {
		return psicologoSelecionadoInfo;
	}

	public void setPsicologoSelecionadoInfo(Usuario psicologoSelecionadoInfo) {
		this.psicologoSelecionadoInfo = psicologoSelecionadoInfo;
	}

	public List<Cita> getListaPendientes() {
		return listaPendientes;
	}

	public void setListaPendientes(List<Cita> listaPendientes) {
		this.listaPendientes = listaPendientes;
	}

	public MBMensajes getMensajes() {
		return mensajes;
	}

	public void setMensajes(MBMensajes mensajes) {
		this.mensajes = mensajes;
	}

	public DNEstadoCita getdNEstadoCita() {
		return dNEstadoCita;
	}

	public void setdNEstadoCita(DNEstadoCita dNEstadoCita) {
		this.dNEstadoCita = dNEstadoCita;
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

	public List<Usuario> getListaPsicologos() {
		return listaPsicologos;
	}

	public String getNombreBusqueda() {
		return nombreBusqueda;
	}

	public void setNombreBusqueda(String nombreBusqueda) {
		this.nombreBusqueda = nombreBusqueda;
	}

	public String getServicioBusqueda() {
		return servicioBusqueda;
	}

	public void setServicioBusqueda(String servicioBusqueda) {
		this.servicioBusqueda = servicioBusqueda;
	}

	public void setListaPsicologos(List<Usuario> listaPsicologos) {
		this.listaPsicologos = listaPsicologos;
	}

	public List<Usuario> getListaPsicologosFiltrada() {
		return listaPsicologosFiltrada;
	}

	public void setListaPsicologosFiltrada(List<Usuario> listaPsicologosFiltrada) {
		this.listaPsicologosFiltrada = listaPsicologosFiltrada;
	}

	public Usuario getPsicologoSelecionado() {
		return psicologoSelecionado;
	}

	public void setPsicologoSelecionado(Usuario psicologoSelecionado) {
		this.psicologoSelecionado = psicologoSelecionado;
	}

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public List<Horario> getListaHorarios() {
		return listaHorarios;
	}

	public void setListaHorarios(List<Horario> listaHorarios) {
		this.listaHorarios = listaHorarios;
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

}
