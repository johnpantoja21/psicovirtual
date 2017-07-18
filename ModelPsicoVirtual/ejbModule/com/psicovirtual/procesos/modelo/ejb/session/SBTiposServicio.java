package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.TiposServicio;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Usuario;

/**
 * Session Bean implementation class SBTiposServicio
 */
@Stateless
@LocalBean
public class SBTiposServicio implements SBTiposServicioLocal {

    /**
     * Default constructor. 
     */
    public SBTiposServicio() {
        // TODO Auto-generated constructor stub
    }

    

	@EJB
	SBFacadeProcesosLocal sbFacade;

	
	@Override
	public TiposServicio guardarTiposServicio(
			TiposServicio tiposServicioGuardar) throws Exception {
		// TODO Auto-generated method stub

		TiposServicio entity = (TiposServicio) sbFacade
				.insertEntity(tiposServicioGuardar);
		return entity;

	}
	
	
	
	@Override
	public TiposServicio modificarTiposServicio(
			TiposServicio tiposServicioGuardar) throws Exception {
		// TODO Auto-generated method stub

		TiposServicio entity = (TiposServicio) sbFacade
				.updateEntity(tiposServicioGuardar);
		return entity;

	}
	

	@Override
	public List<TiposServicio> listaTiposServicio() throws Exception {
		String query = "select o from TiposServicio o ";
		List<TiposServicio> lista = sbFacade.executeQuery(query,
				null);
		return lista;

	}

	@Override
	public TiposServicio buscarTiposServicio(Object id) throws Exception {

		TiposServicio entity = (TiposServicio) sbFacade.findByPrimaryKey(
				TiposServicio.class, id);
		return entity;
	}
	
	

	@Override
	public List<TiposServicio> listaTiposServicioActivos() throws Exception {
		String query = "select o from TiposServicio o where o.estado='ACTIVO'";
		List<TiposServicio> lista = sbFacade.executeQuery(query,
				null);
		return lista;

	}
	
    
    
}
