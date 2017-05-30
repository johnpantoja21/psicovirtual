package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.EstadoCliente;

/**
 * Session Bean implementation class SBEstadoCliente
 */
@Stateless
@LocalBean
public class SBEstadoCliente implements SBEstadoClienteLocal {

    /**
     * Default constructor. 
     */
    public SBEstadoCliente() {
        // TODO Auto-generated constructor stub
    }

    
    
  
    
    @EJB
  	SBFacadeProcesosLocal sbFacade;

  	
  	@Override
  	public EstadoCliente guardarEstadoCliente(
  			EstadoCliente estadoClienteGuardar) throws Exception {
  		// TODO Auto-generated method stub

  		EstadoCliente entity = (EstadoCliente) sbFacade
  				.insertEntity(estadoClienteGuardar);
  		return entity;

  	}
  	
  	
  	
  	@Override
  	public EstadoCliente modificarEstadoCliente(
  			EstadoCliente estadoClienteGuardar) throws Exception {
  		// TODO Auto-generated method stub

  		EstadoCliente entity = (EstadoCliente) sbFacade
  				.updateEntity(estadoClienteGuardar);
  		return entity;

  	}
  	

  	@Override
  	public List<EstadoCliente> listaEstadoCliente() throws Exception {
  		String query = "select o from EstadoCliente o ";
  		List<EstadoCliente> lista = sbFacade.executeQuery(query,
  				null);
  		return lista;

  	}

  	@Override
  	public EstadoCliente buscarEstadoCliente(Object id) throws Exception {

  		EstadoCliente entity = (EstadoCliente) sbFacade.findByPrimaryKey(
  				EstadoCliente.class, id);
  		return entity;
  	}
    
}
