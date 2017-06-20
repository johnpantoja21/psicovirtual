package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Horario;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Tema;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.TipoUsuario;

/**
 * Session Bean implementation class SBTipoUsuario
 */
@Stateless
@LocalBean
public class SBTipoUsuario implements SBTipoUsuarioLocal {

    /**
     * Default constructor. 
     */
    public SBTipoUsuario() {
        // TODO Auto-generated constructor stub
    }

	@EJB
	SBFacadeProcesosLocal sbFacade;

	
	@Override
	public TipoUsuario guardarTipoUsuario(
			TipoUsuario tipoUsuarioGuardar) throws Exception {
		// TODO Auto-generated method stub

		TipoUsuario entity = (TipoUsuario) sbFacade
				.insertEntity(tipoUsuarioGuardar);
		return entity;

	}
	
	
	
	@Override
	public TipoUsuario modificarTipoUsuario(
			TipoUsuario tipoUsuarioGuardar) throws Exception {
		// TODO Auto-generated method stub

		TipoUsuario entity = (TipoUsuario) sbFacade
				.updateEntity(tipoUsuarioGuardar);
		return entity;

	}
	

	@Override
	public List<TipoUsuario> listaTipoUsuario() throws Exception {
		String query = "select o from TipoUsuario o ";
		List<TipoUsuario> lista = sbFacade.executeQuery(query,
				null);
		return lista;

	}

	@Override
	public TipoUsuario buscarTipoUsuario(Object id) throws Exception {

		TipoUsuario entity = (TipoUsuario) sbFacade.findByPrimaryKey(
				TipoUsuario.class, id);
		return entity;
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
