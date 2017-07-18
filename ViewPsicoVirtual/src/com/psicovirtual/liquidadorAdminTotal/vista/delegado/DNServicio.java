package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Servicio;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.TiposServicio;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Usuario;
import com.psicovirtual.procesos.modelo.ejb.session.SBServicioLocal;
import com.psicovirtual.procesos.modelo.ejb.session.SBTiposServicioLocal;

@ManagedBean(value = "DNServicio")
@ApplicationScoped
public class DNServicio {

	SBServicioLocal sBServicioLocal;

	public DNServicio() throws Exception {
		sBServicioLocal = ServiceLocator.getInstance().obtenerServicio(
				Parametros.PREFIJO_JNDI + "SBServicio" + Parametros.PREFIJO_ADICIONAL_JNDI + "SBServicioLocal",
				SBServicioLocal.class);
	}

	public Servicio guardarServicio(Servicio ServicioGuardar) throws Exception {
		return sBServicioLocal.guardarServicio(ServicioGuardar);
	}

	public List<Servicio> listaServicio() throws Exception {
		return sBServicioLocal.listaServicio();
	}

	public List<Servicio> listaServicioPsicologo(Usuario user) throws Exception {
		return sBServicioLocal.listaServicioPsicologo(user);
	}

	public Servicio buscarServicio(Object id) throws Exception {
		return sBServicioLocal.buscarServicio(id);
	}

	public Servicio modificarServicio(Servicio ServicioGuardar) throws Exception {
		return sBServicioLocal.modificarServicio(ServicioGuardar);
	}

}
