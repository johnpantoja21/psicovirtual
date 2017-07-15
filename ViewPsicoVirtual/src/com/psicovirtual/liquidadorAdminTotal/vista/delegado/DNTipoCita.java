package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.TipoCita;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.TiposServicio;
import com.psicovirtual.procesos.modelo.ejb.session.SBTipoCitaLocal;
import com.psicovirtual.procesos.modelo.ejb.session.SBTiposServicioLocal;

@ManagedBean(value = "DNTipoCita")
@ApplicationScoped
public class DNTipoCita {

	SBTipoCitaLocal sBTipoCitaLocal;

	public DNTipoCita() throws Exception {
		sBTipoCitaLocal = ServiceLocator.getInstance().obtenerServicio(Parametros.PREFIJO_JNDI +
				"SBTipoCita"
				+ Parametros.PREFIJO_ADICIONAL_JNDI + "SBTipoCitaLocal", SBTipoCitaLocal.class);
	}

	public TipoCita buscarTipoCita(Object id) throws Exception {
		return sBTipoCitaLocal.buscarTipoCita(id);
	}

	public List<TipoCita> listaTipoCita() throws Exception {
		return sBTipoCitaLocal.listaTipoCita();
	}

	public TipoCita modificarTipoCita(TipoCita tipoCitaGuardar) throws Exception {
		return sBTipoCitaLocal.modificarTipoCita(tipoCitaGuardar);
	}

	public TipoCita guardarTipoCita(TipoCita tipoCitaGuardar) throws Exception {
		return sBTipoCitaLocal.guardarTipoCita(tipoCitaGuardar);
	}
	
	public List<TipoCita> listaTipoCitaActivos() throws Exception{
		return sBTipoCitaLocal.listaTipoCitaActivos();
	}

}
