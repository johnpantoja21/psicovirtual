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
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Comentario;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Horario;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Servicio;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.TipoUsuario;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Usuario;

/**
 * Session Bean implementation class SBComentarios
 */
@Stateless
@LocalBean
public class SBComentarios implements SBComentariosLocal {

	@EJB
	SBFacadeProcesosLocal sbFacade;

	public SBComentarios() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Comentario publicarComentario(Comentario nuevo) throws Exception {
		Comentario entity = (Comentario) sbFacade.insertEntity(nuevo);
		return entity;
	}
	
	
	@Override
	public List<Comentario> listarPsicologosBlogs() throws Exception {
		String query = "SELECT c FROM Comentario c where c.estado='ACTIVO' ";

		List<Comentario> listClientesPsicologo = sbFacade.executeQuery(query, null);

		
		return listClientesPsicologo;
	}

}
