package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.TiposServicio;
import com.psicovirtual.procesos.modelo.ejb.session.SBTiposServicioLocal;

@ManagedBean(value = "DNTiposServicio")
@ApplicationScoped
public class DNTiposServicio {

	SBTiposServicioLocal sBTiposServicioLocal;

	public DNTiposServicio() throws Exception {
		sBTiposServicioLocal = ServiceLocator.getInstance().obtenerServicio(Parametros.PREFIJO_JNDI + "SBTiposServicio"
				+ Parametros.PREFIJO_ADICIONAL_JNDI + "SBTiposServicioLocal", SBTiposServicioLocal.class);
	}

	public TiposServicio guardarTiposServicio(TiposServicio tiposServicioGuardar) throws Exception {
		return sBTiposServicioLocal.guardarTiposServicio(tiposServicioGuardar);
	}

	public List<TiposServicio> listaTiposServicio() throws Exception {
		return sBTiposServicioLocal.listaTiposServicio();
	}

	public TiposServicio buscarTiposServicio(Object id) throws Exception {
		return sBTiposServicioLocal.buscarTiposServicio(id);
	}

	public TiposServicio modificarTiposServicio(TiposServicio tiposServicioGuardar) throws Exception {
		return sBTiposServicioLocal.modificarTiposServicio(tiposServicioGuardar);
	}

}
