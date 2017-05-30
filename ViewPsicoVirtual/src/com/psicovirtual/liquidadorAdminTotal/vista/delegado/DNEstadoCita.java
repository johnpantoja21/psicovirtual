package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.EstadoCita;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Tema;
import com.psicovirtual.procesos.modelo.ejb.session.SBEstadoCitaLocal;
import com.psicovirtual.procesos.modelo.ejb.session.SBTemaLocal;

@ManagedBean(value = "DNEstadoCita")
@ApplicationScoped
public class DNEstadoCita {

	SBEstadoCitaLocal sBEstadoCita;

	public DNEstadoCita() throws Exception {
		sBEstadoCita = ServiceLocator.getInstance().obtenerServicio(Parametros.PREFIJO_JNDI + "SBEstadoCita"
				+ Parametros.PREFIJO_ADICIONAL_JNDI + "SBEstadoCitaLocal", SBEstadoCitaLocal.class);
	}

	public EstadoCita guardarEstadoCita(EstadoCita estadoCitaGuardar) throws Exception {
		return sBEstadoCita.guardarEstadoCita(estadoCitaGuardar);
	}

	public EstadoCita modificarEstadoCita(EstadoCita estadoCitaGuardar) throws Exception {
		return sBEstadoCita.modificarEstadoCita(estadoCitaGuardar);
	}

	public List<EstadoCita> listaEstadoCita() throws Exception {
		return sBEstadoCita.listaEstadoCita();
	}

	public EstadoCita buscarEstadoCita(Object id) throws Exception {
		return sBEstadoCita.buscarEstadoCita(id);
	}

	
	
	
	
	

}
