package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Servicio;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.TiposServicio;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Usuario;

/**
 * Session Bean implementation class SBServicio
 */
@Stateless
@LocalBean
public class SBServicio implements SBServicioLocal {

	/**
	 * Default constructor.
	 */
	public SBServicio() {
		// TODO Auto-generated constructor stub
	}

	@EJB
	SBFacadeProcesosLocal sbFacade;

	@Override
	public Servicio guardarServicio(Servicio ServicioGuardar) throws Exception {
		// TODO Auto-generated method stub

		Servicio entity = (Servicio) sbFacade.insertEntity(ServicioGuardar);
		return entity;
	}

	@Override
	public Servicio modificarServicio(Servicio ServicioGuardar) throws Exception {
		// TODO Auto-generated method stub

		Servicio entity = (Servicio) sbFacade.updateEntity(ServicioGuardar);
		return entity;

	}

	@Override
	public List<Servicio> listaServicio() throws Exception {
		String query = "select o from Servicio o where o.estado='ACTIVO' ";
		List<Servicio> lista = sbFacade.executeQuery(query, null);
		return lista;

	}

	@Override
	public Servicio buscarServicio(Object id) throws Exception {

		Servicio entity = (Servicio) sbFacade.findByPrimaryKey(TiposServicio.class, id);
		return entity;
	}

	public List<Servicio> listaServicioPsicologo(Usuario user) throws Exception {
		String query = "select o from Servicio o where o.estado='ACTIVO' and o.usuario.idUsuario='"+user.getIdUsuario()+"'";
		List<Servicio> lista = sbFacade.executeQuery(query, null);
		return lista;
	}

}
