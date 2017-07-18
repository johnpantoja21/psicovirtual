package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Cita;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.SeccionHistorial;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Tema;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Usuario;
import com.psicovirtual.procesos.modelo.ejb.session.SBHistoriaClinicaLocal;
import com.psicovirtual.procesos.modelo.ejb.session.SBTemaLocal;

@ManagedBean(value = "DNHistoriaClinica")
@ApplicationScoped
public class DNHistoriaClinica {

	SBHistoriaClinicaLocal sBHistoriaClinicaLocal;

	public DNHistoriaClinica() throws Exception {
		sBHistoriaClinicaLocal = ServiceLocator.getInstance().obtenerServicio(Parametros.PREFIJO_JNDI
				+ "SBHistoriaClinica" + Parametros.PREFIJO_ADICIONAL_JNDI + "SBHistoriaClinicaLocal",
				SBHistoriaClinicaLocal.class);
	}

	public SeccionHistorial guardarHistoria(SeccionHistorial HistoriaClinicaGuardar) throws Exception {
		return sBHistoriaClinicaLocal.guardarHistoria(HistoriaClinicaGuardar);
	}

	public SeccionHistorial modificarHistoriaClinica(SeccionHistorial HistoriaClinicaGuardar) throws Exception {
		return sBHistoriaClinicaLocal.modificarHistoriaClinica(HistoriaClinicaGuardar);
	}

	public List<SeccionHistorial> buscarHistoriaClinicaCiente(Usuario user) throws Exception {
		return sBHistoriaClinicaLocal.buscarHistoriaClinicaCiente(user);
	}

	public List<Cita> consultarCitasPagadasClientePsico(Usuario user) throws Exception {
		return sBHistoriaClinicaLocal.consultarCitasPagadasClientePsico(user);
	}

}
