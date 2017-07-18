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
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.EstadoCita;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Horario;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Servicio;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.TipoUsuario;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Usuario;

/**
 * Session Bean implementation class SBClientePsicologo
 */
@Stateless
@LocalBean
public class SBClientePsicologo implements SBClientePsicologoLocal {

	@EJB
	SBFacadeProcesosLocal sbFacade;

	public SBClientePsicologo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ClientesPsicologo crearRelacionClientePsicologo(ClientesPsicologo nuevo) throws Exception {
		ClientesPsicologo entity = (ClientesPsicologo) sbFacade.insertEntity(nuevo);
		return entity;
	}

	@Override
	public int verificarRelacionExistente(ClientesPsicologo relacion) throws Exception {
		int retorna = 0;

		String query = "SELECT c.idCliePsico FROM ClientesPsicologo c where c.usuario1.idUsuario='"
				+ relacion.getUsuario1().getIdUsuario() + "' and c.usuario2.idUsuario='"
				+ relacion.getUsuario2().getIdUsuario() + "' ";

		List listRelacion = sbFacade.executeQuery(query, null);

		if (listRelacion.size() > 0) {
			retorna = listRelacion.size();
		}
		return retorna;
	}

	@Override
	public int consultarRelacionClientePsicologo(String usuarioPsicologo, String usuarioCliente) throws Exception {
		int r = 0;
		String query = "SELECT c.idCliePsico FROM ClientesPsicologo c where c.usuario1.idUsuario='" + usuarioCliente
				+ "' and c.usuario2.idUsuario='" + usuarioPsicologo + "' ";

		List listRelacion = sbFacade.executeQuery(query, null);

		for (int i = 0; i < listRelacion.size(); i++) {

			r=Integer.parseInt(listRelacion.get(i) + "");

		}

		return r;
	}


  	@Override
  	public ClientesPsicologo buscarClientesPsicologo(Object id) throws Exception {

  		ClientesPsicologo entity = (ClientesPsicologo) sbFacade.findByPrimaryKey(
  				ClientesPsicologo.class, id);
  		return entity;
  	}

}
