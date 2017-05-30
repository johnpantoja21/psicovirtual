package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.psicovirtual.estandar.modelo.ejb.session.SBFacadeProcesosLocal;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Tema;

/**
 * Session Bean implementation class SBTema
 */
@Stateless
@LocalBean
public class SBTema implements SBTemaLocal {

    /**
     * Default constructor. 
     */
    public SBTema() {
        // TODO Auto-generated constructor stub
    }
    
    
    @EJB
	SBFacadeProcesosLocal sbFacade;

	
	@Override
	public Tema guardarTema(
			Tema temaGuardar) throws Exception {
		// TODO Auto-generated method stub

		Tema entity = (Tema) sbFacade
				.insertEntity(temaGuardar);
		return entity;

	}
	
	
	
	@Override
	public Tema modificarTema(
			Tema temaGuardar) throws Exception {
		// TODO Auto-generated method stub

		Tema entity = (Tema) sbFacade
				.updateEntity(temaGuardar);
		return entity;

	}
	

	@Override
	public List<Tema> listaTema() throws Exception {
		String query = "select o from Tema o ";
		List<Tema> lista = sbFacade.executeQuery(query,
				null);
		return lista;

	}

	@Override
	public Tema buscarTema(Object id) throws Exception {

		Tema entity = (Tema) sbFacade.findByPrimaryKey(
				Tema.class, id);
		return entity;
	}

}
