package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.EstadoCliente;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Tema;
import com.psicovirtual.procesos.modelo.ejb.session.SBEstadoClienteLocal;
import com.psicovirtual.procesos.modelo.ejb.session.SBTemaLocal;

@ManagedBean(value = "DNEstadoCliente")
@ApplicationScoped
public class DNEstadoCliente {

	SBEstadoClienteLocal sBEstadoCliente;

	public DNEstadoCliente() throws Exception {
		sBEstadoCliente = ServiceLocator.getInstance().obtenerServicio(Parametros.PREFIJO_JNDI + "SBEstadoCliente"
				+ Parametros.PREFIJO_ADICIONAL_JNDI + "SBEstadoClienteLocal", SBEstadoClienteLocal.class);
	}

	public EstadoCliente guardarEstadoCliente(EstadoCliente estadoClienteGuardar) throws Exception {
		return sBEstadoCliente.guardarEstadoCliente(estadoClienteGuardar);
	}

	public EstadoCliente modificarEstadoCliente(EstadoCliente estadoClienteGuardar) throws Exception {
		return sBEstadoCliente.modificarEstadoCliente(estadoClienteGuardar);
	}

	public List<EstadoCliente> listaEstadoCliente() throws Exception {
		return sBEstadoCliente.listaEstadoCliente();
	}

	public EstadoCliente buscarEstadoCliente(Object id) throws Exception {
		return sBEstadoCliente.buscarEstadoCliente(id);
	}

	
	
	
	

}
