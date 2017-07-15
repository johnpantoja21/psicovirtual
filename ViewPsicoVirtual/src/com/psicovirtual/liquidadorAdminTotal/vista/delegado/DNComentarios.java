package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.ClientesPsicologo;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Comentario;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Tema;
import com.psicovirtual.procesos.modelo.ejb.session.SBComentariosLocal;
import com.psicovirtual.procesos.modelo.ejb.session.SBTemaLocal;

@ManagedBean(value = "DNComentarios")
@ApplicationScoped
public class DNComentarios {

	SBComentariosLocal sBComentariosLocal;

	public DNComentarios() throws Exception {
		sBComentariosLocal = ServiceLocator.getInstance().obtenerServicio(
				Parametros.PREFIJO_JNDI + "SBComentarios" + Parametros.PREFIJO_ADICIONAL_JNDI + "SBComentariosLocal",
				SBComentariosLocal.class);
	}

	public Comentario publicarComentario(Comentario nuevo) throws Exception {
		return sBComentariosLocal.publicarComentario(nuevo);
	}
	
	public List<Comentario> listarPsicologosBlogs()  throws Exception {
		return sBComentariosLocal.listarPsicologosBlogs();
	}
	
	

}
