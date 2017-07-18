package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNComentarios;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNEstadoCita;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNTipoUsuario;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNUsuarios;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Cita;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.EstadoCita;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Usuario;

@ManagedBean(name = "MBLogin")
@SessionScoped
public class MBLogin {
	MBMensajes mensajes = new MBMensajes();
	private String user;
	private String pass;
	DNUsuarios dnUsuarios;

	private boolean gestion;
	private boolean usuarios;
	private boolean psicologos;

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

	public MBLogin() throws Exception {

	}

	public void navegarControl() throws Exception {
		if (dnUsuarios == null) {
			dnUsuarios = new DNUsuarios();
		}
		Usuario usuario = new Usuario();
		usuario.setUsuario(user);
		usuario.setPassword(pass);

		if (dnUsuarios.consultarUsuarioInicio(usuario) > 0) {
			// if (user.length() > 0) {
			user = user.toUpperCase();

			Usuario x = dnUsuarios.consultarDetalleUsuarioByUsuario(user);

			if (x.getTipoUsuario().getIdTipoUsu() == 1) {// ADMIN

				gestion = true;
				usuarios = false;
				psicologos = false;

			}

			if (x.getTipoUsuario().getIdTipoUsu() == 2) {// PSICLO

				gestion = false;
				usuarios = false;
				psicologos = true;

			}

			if (x.getTipoUsuario().getIdTipoUsu() == 3) {// CLEINTE

				gestion = false;
				usuarios = true;
				psicologos = false;

			}
			cargarUsuario();
			try {
				FacesContext context = FacesContext.getCurrentInstance();
				ExternalContext extContext = context.getExternalContext();
				String url2 = extContext.encodeActionURL(context.getApplication().getViewHandler().getActionURL(context,
						"/view/gestion/bienvenido.xhtml"));
				extContext.redirect(url2);
			} catch (Exception exception) {
				// TODO: Add catch code
				exception.printStackTrace();
			}
			// }
		} else {
			mensajes.mostrarMensaje("Usuario o Contraseña incorrecto. Vuelve a intentarlo", 3);
		}
	}

	public void cerrarSesion() {

		try {

			FacesContext context = FacesContext.getCurrentInstance();

			ExternalContext externalContext = context.getExternalContext();

			Object session = externalContext.getSession(false);

			HttpSession httpSession = (HttpSession) session;

			httpSession.invalidate();

			String url2 = externalContext.encodeActionURL(
					context.getApplication().getViewHandler().getActionURL(context, "/view/login.xhtml"));
			externalContext.redirect(url2);

		} catch (Exception e) {
			// TODO: Add catch code
			e.printStackTrace();
			System.out.println("***ERROR INVALIDANDO LA SESSION ACTIVA");
		}

	}

	public void onEventSelect(SelectEvent selectEvent) {
		event = (ScheduleEvent) selectEvent.getObject();
	}

	public void cargarUsuario() throws Exception {

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
		
		if (usuarioModificar.getTipoUsuario().getIdTipoUsu() == 1) {
			eventModel = new DefaultScheduleModel();
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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public DNUsuarios getDnUsuarios() {
		return dnUsuarios;
	}

	public void setDnUsuarios(DNUsuarios dnUsuarios) {
		this.dnUsuarios = dnUsuarios;
	}

	public boolean isGestion() {
		return gestion;
	}

	public void setGestion(boolean gestion) {
		this.gestion = gestion;
	}

	public boolean isUsuarios() {
		return usuarios;
	}

	public void setUsuarios(boolean usuarios) {
		this.usuarios = usuarios;
	}

	public boolean isPsicologos() {
		return psicologos;
	}

	public void setPsicologos(boolean psicologos) {
		this.psicologos = psicologos;
	}

}
