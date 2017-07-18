package com.psicovirtual.liquidadorAdminTotal.vista.delegado;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.psicovirtual.estandar.modelo.utilidades.Parametros;
import com.psicovirtual.estandar.vista.utilidades.ServiceLocator;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.ClientesPsicologo;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Tema;
import com.psicovirtual.procesos.modelo.ejb.session.SBClientePsicologoLocal;
import com.psicovirtual.procesos.modelo.ejb.session.SBTemaLocal;

@ManagedBean(value = "DNClientePsicologo")
@ApplicationScoped
public class DNClientePsicologo {

	SBClientePsicologoLocal sBClientePsicologoLocal;

	public DNClientePsicologo() throws Exception {
		sBClientePsicologoLocal = ServiceLocator.getInstance().obtenerServicio(Parametros.PREFIJO_JNDI
				+ "SBClientePsicologo" + Parametros.PREFIJO_ADICIONAL_JNDI + "SBClientePsicologoLocal",
				SBClientePsicologoLocal.class);
	}

	public ClientesPsicologo crearRelacionClientePsicologo(ClientesPsicologo nuevo) throws Exception {
		ClientesPsicologo x = new ClientesPsicologo();

		return sBClientePsicologoLocal.crearRelacionClientePsicologo(nuevo);
	}

	public int verificarRelacionExistente(ClientesPsicologo relacion) throws Exception {
		return sBClientePsicologoLocal.verificarRelacionExistente(relacion);
	}

	
	public int consultarRelacionClientePsicologo(String usuarioPsicologo, String usuarioCliente) throws Exception {
		return sBClientePsicologoLocal.consultarRelacionClientePsicologo(usuarioPsicologo, usuarioCliente);
	}

	public ClientesPsicologo buscarClientesPsicologo(Object id) throws Exception {
		return sBClientePsicologoLocal.buscarClientesPsicologo(id);
	}

	
}
