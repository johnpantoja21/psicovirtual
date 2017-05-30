package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Cita;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.ClientesPsicologo;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Servicio;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Usuario;
import com.psicovirtual.procesos.modelo.ejb.session.SBUsuariosLocal;

@ManagedBean(value = "DNUsuarios")
@ApplicationScoped
public class DNUsuarios {

	SBUsuariosLocal sBUsuariosLocal;

	public DNUsuarios() throws Exception {
		sBUsuariosLocal = ServiceLocator.getInstance().obtenerServicio(
				Parametros.PREFIJO_JNDI + "SBUsuarios" + Parametros.PREFIJO_ADICIONAL_JNDI + "SBUsuariosLocal",
				SBUsuariosLocal.class);
	}

	public Usuario crearUsuario(Usuario nuevo) throws Exception {
		return sBUsuariosLocal.crearUsuario(nuevo);
	}

	public Usuario actualizarUsuario(Usuario update) throws Exception {
		return sBUsuariosLocal.actualizarUsuario(update);
	}

	public int validarInicioSesion(Usuario user) throws Exception {
		return sBUsuariosLocal.consultarUsuarioInicio(user);
	}

	public Usuario consultarDetalleUsuario(int id) throws Exception {
		return sBUsuariosLocal.consultarDetalleUsuario(id);
	}

	public Usuario consultarDetalleUsuarioByUsuario(String usuario) throws Exception {
		return sBUsuariosLocal.consultarDetalleUsuarioByUsuario(usuario);
	}

	public int consultarUsuarioInicio(Usuario user) throws Exception {
		return sBUsuariosLocal.consultarUsuarioInicio(user);
	}

	public int consultarUsuarioRepetido(Usuario user) throws Exception {
		return sBUsuariosLocal.consultarUsuarioRepetido(user);
	}

	public List<ClientesPsicologo> listaClientePsicologo(String id) throws Exception {
		return sBUsuariosLocal.listaClientePsicologo(id);
	}

	public List<Servicio> listaServiciosPsicologo(String id) throws Exception {
		return sBUsuariosLocal.listaServiciosPsicologo(id);
	}

	public Cita guardarCita(Cita nuevo) throws Exception {
		return sBUsuariosLocal.guardarCita(nuevo);
	}

	public List<Usuario> listarPsicologos() throws Exception {
		return sBUsuariosLocal.listarPsicologos();
	}
	
	

}
