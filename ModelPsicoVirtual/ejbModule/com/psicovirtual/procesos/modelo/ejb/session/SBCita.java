package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Cita;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Tema;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Usuario;

/**
 * Session Bean implementation class SBCita
 */
@Stateless
@LocalBean
public class SBCita implements SBCitaLocal {

	/**
	 * Default constructor.
	 */
	public SBCita() {
		// TODO Auto-generated constructor stub
	}

	@EJB
	SBFacadeProcesosLocal sbFacade;

	@Override
	public List<Cita> consultarCitaCliente(Usuario user) throws Exception {
		String query = "select o from Cita o where o.clientesPsicologo.usuario1.idUsuario='" + user.getIdUsuario()
				+ "' and o.estadoCita.idEstadoCita='1' ";
		List<Cita> lista = sbFacade.executeQuery(query, null);
		return lista;

	}

	@Override
	public Cita modificarCita(Cita update) throws Exception {
		Cita x = (Cita) sbFacade.updateEntity(update);
		return x;
	}
	
	


	
	

}
