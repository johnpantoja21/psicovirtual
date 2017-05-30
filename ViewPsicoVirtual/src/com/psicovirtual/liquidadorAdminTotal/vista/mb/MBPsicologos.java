package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.math.BigDecimal;
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
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNEstadoCita;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNEstadoCliente;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNTipoCita;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNTipoUsuario;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNUsuarios;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Cita;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.ClientesPsicologo;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.EstadoCliente;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Servicio;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Tema;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.TipoCita;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Usuario;

@ManagedBean(name = "MBPsicologos")
@SessionScoped
public class MBPsicologos {
	MBMensajes mensajes = new MBMensajes();
	DNEstadoCita dNEstadoCita;
	DNUsuarios dNUsuario;
	DNTipoCita dNTipoCita;

	DNEstadoCliente dNEstadoCliente;
	private List<Usuario> listaPsicologos;
	private List<Usuario> listaPsicologosFiltrada;
	private String nombreBusqueda;
	private String servicioBusqueda;

	public MBPsicologos() throws Exception {

		if (dNUsuario == null) {
			dNUsuario = new DNUsuarios();
		}

		listaPsicologos = dNUsuario.listarPsicologos();
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

}
