package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.TipoUsuario;
import com.psicovirtual.procesos.modelo.ejb.session.SBTipoUsuarioLocal;

@ManagedBean(value = "DNTipoUsuario")
@ApplicationScoped
public class DNTipoUsuario {

	SBTipoUsuarioLocal sBTipoUsuarioLocal;

	public DNTipoUsuario() throws Exception {
		sBTipoUsuarioLocal = ServiceLocator.getInstance().obtenerServicio(Parametros.PREFIJO_JNDI + "SBTipoUsuario"
				+ Parametros.PREFIJO_ADICIONAL_JNDI + "SBTipoUsuarioLocal", SBTipoUsuarioLocal.class);
	}

	public TipoUsuario guardarTipoUsuario(TipoUsuario tipoUsuarioGuardar) throws Exception {
		return sBTipoUsuarioLocal.guardarTipoUsuario(tipoUsuarioGuardar);
	}

	public List<TipoUsuario> listaTipoUsuario() throws Exception {
		return sBTipoUsuarioLocal.listaTipoUsuario();
	}

	public TipoUsuario buscarTipoUsuario(int id) throws Exception {
		return sBTipoUsuarioLocal.buscarTipoUsuario(id);
	}

	public TipoUsuario modificarTipoUsuario(TipoUsuario tipoUsuarioGuardar) throws Exception {
		return sBTipoUsuarioLocal.modificarTipoUsuario(tipoUsuarioGuardar);
	}

	

}
