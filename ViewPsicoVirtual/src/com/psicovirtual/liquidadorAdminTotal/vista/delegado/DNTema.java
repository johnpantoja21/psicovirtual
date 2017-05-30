package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Tema;
import com.psicovirtual.procesos.modelo.ejb.session.SBTemaLocal;

@ManagedBean(value = "DNTema")
@ApplicationScoped
public class DNTema {

	SBTemaLocal sBTemaLocal;

	public DNTema() throws Exception {
		sBTemaLocal = ServiceLocator.getInstance().obtenerServicio(Parametros.PREFIJO_JNDI + "SBTema"
				+ Parametros.PREFIJO_ADICIONAL_JNDI + "SBTemaLocal", SBTemaLocal.class);
	}

	public Tema guardarTema(Tema temaGuardar) throws Exception {
		return sBTemaLocal.guardarTema(temaGuardar);
	}

	public Tema modificarTema(Tema temaGuardar) throws Exception {
		return sBTemaLocal.modificarTema(temaGuardar);
	}

	public List<Tema> listaTema() throws Exception {
		return sBTemaLocal.listaTema();
	}

	public Tema buscarTema(Object id) throws Exception {
		return sBTemaLocal.buscarTema(id);
	}

	
	
	
	

}
