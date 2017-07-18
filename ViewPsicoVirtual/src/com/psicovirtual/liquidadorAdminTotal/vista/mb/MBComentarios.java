package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNClientePsicologo;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNComentarios;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNEstadoCita;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNEstadoCliente;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNTipoCita;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNTipoUsuario;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNUsuarios;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Cita;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.ClientesPsicologo;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Comentario;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.EstadoCliente;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Servicio;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Tema;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.TipoCita;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Usuario;
import com.sun.el.parser.ParseException;

@ManagedBean(name = "MBComentarios")
@SessionScoped
public class MBComentarios {

	MBMensajes mensajes = new MBMensajes();

	DNUsuarios dNUsuario;
	DNComentarios dNComentarios;
	DNClientePsicologo dnClientePsico;

	private String usuarioCliente;
	private List<ClientesPsicologo> listaClientesPsicologo;
	private List<Comentario> listaComentarios;
	private List<ClientesPsicologo> listaFiltrosClientesPsicologo;
	private ClientesPsicologo clientesPsicologoSeleccionado;
	private Date fechaProgramada;
	private String testimonio;

	public MBComentarios() {

	}

	public void cargarListados(String userCliente) throws Exception {

		limpiarObjetos();
		if (dNUsuario == null) {
			dNUsuario = new DNUsuarios();
		}

		if (dNComentarios == null) {
			dNComentarios = new DNComentarios();
		}
		
		Usuario x = dNUsuario.consultarDetalleUsuarioByUsuario(userCliente);
		listaClientesPsicologo = dNUsuario.listaClientePsicologo(x.getIdUsuario() + "");
		listaComentarios = dNComentarios.listarPsicologosBlogs();
		
	}

	public void limpiarObjetos() {
		clientesPsicologoSeleccionado = new ClientesPsicologo();
		fechaProgramada = new Date();
		testimonio = "";
	}

	public void publicarExperiencia(String userCliente) throws Exception {

		if (dNComentarios == null) {
			dNComentarios = new DNComentarios();
		}

		if (dNUsuario == null) {
			dNUsuario = new DNUsuarios();
		}

		if (dnClientePsico == null) {
			dnClientePsico = new DNClientePsicologo();
		}

		Usuario x = dNUsuario.consultarDetalleUsuarioByUsuario(userCliente);
		
		int idClientePsico = dnClientePsico.consultarRelacionClientePsicologo(
				clientesPsicologoSeleccionado.getUsuario2().getIdUsuario() + "", x.getIdUsuario() + "");

		Comentario nuevo = new Comentario();
		
		
		ClientesPsicologo vat = dnClientePsico.buscarClientesPsicologo(idClientePsico);
		
		
		nuevo.setClientesPsicologo(vat);
		nuevo.setEstado("ACTIVO");

		fechaProgramada = new Date();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		Date date = formatter.parse(formatter.format(fechaProgramada));
		nuevo.setFechaPublicacion(date);
		nuevo.setTestimonio(testimonio);

		if (dNComentarios.publicarComentario(nuevo) != null) {
			cargarListados(userCliente);
			mensajes.mostrarMensaje("La publicacion se realizo con exito", 1);
		}

	}

	public List<Comentario> getListaComentarios() {
		return listaComentarios;
	}

	public void setListaComentarios(List<Comentario> listaComentarios) {
		this.listaComentarios = listaComentarios;
	}

	public String getTestimonio() {
		return testimonio;
	}

	public void setTestimonio(String testimonio) {
		this.testimonio = testimonio;
	}

	public List<ClientesPsicologo> getListaFiltrosClientesPsicologo() {
		return listaFiltrosClientesPsicologo;
	}

	public void setListaFiltrosClientesPsicologo(List<ClientesPsicologo> listaFiltrosClientesPsicologo) {
		this.listaFiltrosClientesPsicologo = listaFiltrosClientesPsicologo;
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

	public Date getFechaProgramada() {
		return fechaProgramada;
	}

	public void setFechaProgramada(Date fechaProgramada) {
		this.fechaProgramada = fechaProgramada;
	}

}
