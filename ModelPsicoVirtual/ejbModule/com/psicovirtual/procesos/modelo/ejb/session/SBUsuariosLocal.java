package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Cita;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.ClientesPsicologo;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Servicio;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Usuario;

@Local
public interface SBUsuariosLocal {
	
	public Usuario crearUsuario(Usuario nuevo) throws Exception;
	public Usuario actualizarUsuario(Usuario update) throws Exception;
	public Usuario consultarDetalleUsuario(int id) throws Exception;
	public int consultarUsuarioInicio(Usuario user) throws Exception;
	public int consultarUsuarioRepetido(Usuario user) throws Exception;
	public Usuario consultarDetalleUsuarioByUsuario(String usuario) throws Exception;
	public List<ClientesPsicologo> listaClientePsicologo(String id) throws Exception;
	public List<Servicio> listaServiciosPsicologo(String id) throws Exception;
	public Cita guardarCita(Cita nuevo) throws Exception;
	public List<Usuario> listarPsicologos() throws Exception;
	
}
