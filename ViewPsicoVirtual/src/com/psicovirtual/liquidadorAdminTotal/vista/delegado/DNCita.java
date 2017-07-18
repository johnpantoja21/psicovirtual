package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Cita;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Tema;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Usuario;
import com.psicovirtual.procesos.modelo.ejb.session.SBCitaLocal;
import com.psicovirtual.procesos.modelo.ejb.session.SBTemaLocal;

@ManagedBean(value = "DNCita")
@ApplicationScoped
public class DNCita {

	SBCitaLocal sBCitaLocal;

	public DNCita() throws Exception {
		sBCitaLocal = ServiceLocator.getInstance().obtenerServicio(
				Parametros.PREFIJO_JNDI + "SBCita" + Parametros.PREFIJO_ADICIONAL_JNDI + "SBCitaLocal",
				SBCitaLocal.class);
	}

	public List<Cita> consultarCitaClient(Usuario user) throws Exception {
		return sBCitaLocal.consultarCitaCliente(user);
	}
	
	public Cita modificarCita(Cita cita) throws Exception {
		return sBCitaLocal.modificarCita(cita);
	}

}
