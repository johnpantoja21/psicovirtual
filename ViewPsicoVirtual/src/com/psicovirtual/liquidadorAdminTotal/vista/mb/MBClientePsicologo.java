package com.psicovirtual.liquidadorAdminTotal.vista.mb;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.psicovirtual.estandar.vista.mb.MBMensajes;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNClientePsicologo;
import com.psicovirtual.liquidadorAdminTotal.vista.delegado.DNTema;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.ClientesPsicologo;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Tema;

@ManagedBean(name = "MBClientePsicologo")
@SessionScoped
public class MBClientePsicologo {
	MBMensajes mensajes = new MBMensajes();

	DNClientePsicologo dNClientePsicologo;

	public MBClientePsicologo() throws Exception {

		if (dNClientePsicologo == null) {
			dNClientePsicologo = new DNClientePsicologo();
		}

	}


	public ClientesPsicologo guardarAsignacion(ClientesPsicologo relacionNueva) throws Exception {
		if (dNClientePsicologo == null) {
			dNClientePsicologo = new DNClientePsicologo();
		}

		return dNClientePsicologo.crearRelacionClientePsicologo(relacionNueva);

	}

	public int verificarRelacionExistente(ClientesPsicologo relacion) throws Exception {
		if (dNClientePsicologo == null) {
			dNClientePsicologo = new DNClientePsicologo();
		}
		return dNClientePsicologo.verificarRelacionExistente(relacion);
	}

}
