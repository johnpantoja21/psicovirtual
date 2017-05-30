package com.psicovirtual.procesos.modelo.ejb.session;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Cita;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.ClientesPsicologo;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Servicio;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.TipoUsuario;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Usuario;

/**
 * Session Bean implementation class SBUsuarios
 */
@Stateless
@LocalBean
public class SBUsuarios implements SBUsuariosLocal {

	@EJB
	SBFacadeProcesosLocal sbFacade;

	public SBUsuarios() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Usuario crearUsuario(Usuario nuevo) throws Exception {
		Usuario entity = (Usuario) sbFacade.insertEntity(nuevo);
		return entity;
	}

	@Override
	public int consultarUsuarioInicio(Usuario user) throws Exception {
		String dato = "";
		int retorna = 0;

		String query = "SELECT count(u.idUsuario) FROM Usuario u where u.usuario='" + user.getUsuario() + "' "
				+ "and u.password='" + user.getPassword() + "' ";

		List listUsuario = sbFacade.executeQuery(query, null);

		if (listUsuario.size() > 0) {
			dato = listUsuario.get(0) + "";
			retorna = Integer.parseInt(dato);
		}

		return retorna;
	}

	@Override
	public int consultarUsuarioRepetido(Usuario user) throws Exception {
		String query = "SELECT count(u.idUsuario) FROM Usuario u where u.usuario='" + user.getUsuario() + "' ";

		List listUsuario = sbFacade.executeQuery(query, null);

		if (listUsuario.size() > 0) {
			return 1;
		}
		return 0;
	}

	@Override
	public Usuario actualizarUsuario(Usuario update) throws Exception {
		Usuario x = (Usuario) sbFacade.updateEntity(update);
		return x;
	}

	@Override
	public Usuario consultarDetalleUsuario(int id) throws Exception {
		String query = "SELECT u FROM Usuario u where u.idUsuario='" + id + "' ";

		List<Usuario> listUsuario = sbFacade.executeQuery(query, null);
		Usuario temp=new Usuario();;

		for (int i = 0; i < listUsuario.size(); i++) {
			temp = listUsuario.get(i);
		}
		return temp;
	}
	
	@Override
	public Usuario consultarDetalleUsuarioByUsuario(String usuario) throws Exception {
		String query = "SELECT u FROM Usuario u where u.usuario='" + usuario + "' ";

		List<Usuario> listUsuario = sbFacade.executeQuery(query, null);
		Usuario temp=new Usuario();;

		for (int i = 0; i < listUsuario.size(); i++) {
			temp = listUsuario.get(i);
		}
		return temp;
	}
	
	
	
	
	@Override
	public List<ClientesPsicologo> listaClientePsicologo(String id) throws Exception {
		
		
		HashMap parametros= new HashMap();
		parametros.put("id", new BigDecimal(id));
		String query = "select o from ClientesPsicologo o where o.usuario1.idUsuario = :id";
		List<ClientesPsicologo> lista = sbFacade.executeQuery(query,
				parametros);
		return lista;

	}
	
	
	
	
	@Override
	public List<Servicio> listaServiciosPsicologo(String id) throws Exception {
		
		
		HashMap parametros= new HashMap();
		parametros.put("id", new BigDecimal(id));
		String query = "select o from Servicio o where o.usuario.idUsuario = :id";
		List<Servicio> lista = sbFacade.executeQuery(query,
				parametros);
		return lista;

	}
	
	
	@Override
	public Cita guardarCita(Cita nuevo) throws Exception {
		Cita entity = (Cita) sbFacade.insertEntity(nuevo);
		return entity;
	}
	
	@Override
	public List<Usuario> listarPsicologos() throws Exception {
		
		
	
		String query = "select o from Usuario o where o.tipoUsuario.idTipoUsu = '2'";
		List<Usuario> lista = sbFacade.executeQuery(query,
				null);
		return lista;

	}
	

}
