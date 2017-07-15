package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.TipoCita;

/**
 * Session Bean implementation class SBTipoCita
 */
@Stateless
public class SBTipoCita implements SBTipoCitaLocal {

    /**
     * Default constructor. 
     */
    public SBTipoCita() {
        // TODO Auto-generated constructor stub
    }
    
    
    @EJB
	SBFacadeProcesosLocal sbFacade;

	
	@Override
	public TipoCita guardarTipoCita(
			TipoCita tipoCitaGuardar) throws Exception {
		// TODO Auto-generated method stub

		TipoCita entity = (TipoCita) sbFacade
				.insertEntity(tipoCitaGuardar);
		return entity;

	}
	
	
	
	@Override
	public TipoCita modificarTipoCita(
			TipoCita tipoCitaGuardar) throws Exception {
		// TODO Auto-generated method stub

		TipoCita entity = (TipoCita) sbFacade
				.updateEntity(tipoCitaGuardar);
		return entity;

	}
	

	@Override
	public List<TipoCita> listaTipoCita() throws Exception {
		String query = "select o from TipoCita o";
		List<TipoCita> lista = sbFacade.executeQuery(query,
				null);
		return lista;

	}
	
	@Override
	public List<TipoCita> listaTipoCitaActivos() throws Exception {
		String query = "select o from TipoCita o where o.estado='ACTIVO'";
		List<TipoCita> lista = sbFacade.executeQuery(query,
				null);
		return lista;

	}

	@Override
	public TipoCita buscarTipoCita(Object id) throws Exception {

		TipoCita entity = (TipoCita) sbFacade.findByPrimaryKey(
				TipoCita.class, id);
		return entity;
	}
	
    

}
