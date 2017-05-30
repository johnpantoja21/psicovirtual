package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.EstadoCita;

/**
 * Session Bean implementation class SBEstadoCita
 */
@Stateless
@LocalBean
public class SBEstadoCita implements SBEstadoCitaLocal {

    /**
     * Default constructor. 
     */
    public SBEstadoCita() {
        // TODO Auto-generated constructor stub
    }

    
    
    @EJB
  	SBFacadeProcesosLocal sbFacade;

  	
  	@Override
  	public EstadoCita guardarEstadoCita(
  			EstadoCita estadoCitaGuardar) throws Exception {
  		// TODO Auto-generated method stub

  		EstadoCita entity = (EstadoCita) sbFacade
  				.insertEntity(estadoCitaGuardar);
  		return entity;

  	}
  	
  	
  	
  	@Override
  	public EstadoCita modificarEstadoCita(
  			EstadoCita estadoCitaGuardar) throws Exception {
  		// TODO Auto-generated method stub

  		EstadoCita entity = (EstadoCita) sbFacade
  				.updateEntity(estadoCitaGuardar);
  		return entity;

  	}
  	

  	@Override
  	public List<EstadoCita> listaEstadoCita() throws Exception {
  		String query = "select o from EstadoCita o ";
  		List<EstadoCita> lista = sbFacade.executeQuery(query,
  				null);
  		return lista;

  	}

  	@Override
  	public EstadoCita buscarEstadoCita(Object id) throws Exception {

  		EstadoCita entity = (EstadoCita) sbFacade.findByPrimaryKey(
  				EstadoCita.class, id);
  		return entity;
  	}
    
}
