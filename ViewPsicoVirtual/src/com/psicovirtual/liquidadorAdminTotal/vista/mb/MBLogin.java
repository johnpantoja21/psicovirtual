package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNEstadoCita;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.EstadoCita;

@ManagedBean(name = "MBLogin")
@SessionScoped
public class MBLogin {
	MBMensajes mensajes = new MBMensajes();
	private String user;
	private String pass;
	

	public MBLogin() throws Exception {

		
	}

	public void navegarControl() {

		try {
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext extContext = context.getExternalContext();
			String url2 = extContext.encodeActionURL(
					context.getApplication().getViewHandler().getActionURL(context, "/view/gestion/bienvenido.xhtml"));
			extContext.redirect(url2);
		} catch (Exception exception) {
			// TODO: Add catch code
			exception.printStackTrace();
		}
	}

	public void cerrarSesion() {
System.out.println("paso por cerra");
		try {
			
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext extContext = context.getExternalContext();
			String url2 = extContext
					.encodeActionURL(context.getApplication().getViewHandler().getActionURL(context, "/view/login.xhtml"));
			extContext.redirect(url2);

		} catch (Exception e) {
			// TODO: Add catch code
			e.printStackTrace();
			System.out.println("***ERROR INVALIDANDO LA SESSION ACTIVA");
		}

	}

	public MBMensajes getMensajes() {
		return mensajes;
	}

	public void setMensajes(MBMensajes mensajes) {
		this.mensajes = mensajes;
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


}
