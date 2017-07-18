package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Cita;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.SeccionHistorial;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Tema;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Usuario;

/**
 * Session Bean implementation class SBHistoriaClinica
 */
@Stateless
@LocalBean
public class SBHistoriaClinica implements SBHistoriaClinicaLocal {

	/**
	 * Default constructor.
	 */
	public SBHistoriaClinica() {
		// TODO Auto-generated constructor stub
	}

	@EJB
	SBFacadeProcesosLocal sbFacade;

	@Override
	public SeccionHistorial guardarHistoria(SeccionHistorial HistoriaClinicaGuardar) throws Exception {
		SeccionHistorial entity = (SeccionHistorial) sbFacade.insertEntity(HistoriaClinicaGuardar);
		return entity;
	}

	@Override
	public SeccionHistorial modificarHistoriaClinica(SeccionHistorial HistoriaClinicaGuardar) throws Exception {
		SeccionHistorial entity = (SeccionHistorial) sbFacade.updateEntity(HistoriaClinicaGuardar);
		return entity;

	}

	@Override
	public List<SeccionHistorial> buscarHistoriaClinicaCiente(Usuario user) throws Exception {
		String query = "select o from SeccionHistorial o where o.cita.clientesPsicologo.usuario1.idUsuario='"
				+ user.getIdUsuario() + "' ";
		List<SeccionHistorial> lista = sbFacade.executeQuery(query, null);
		return lista;
	}

	@Override
	public List<Cita> consultarCitasPagadasClientePsico(Usuario user) throws Exception {
		Date date = new Date();
		String query = "select o from Cita o where o.clientesPsicologo.usuario2.idUsuario='" + user.getIdUsuario()
				+ "' and o.estadoCita.idEstadoCita='2' " + "and o.horario.fechaInicial<='" + date.getDate() + "' ";

		System.out.println("Sql -->> " + query);
		List<Cita> lista = sbFacade.executeQuery(query, null);
		return lista;
	}

}
