package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Servicio;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.TiposServicio;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Usuario;

@Local
public interface SBServicioLocal {

	public Servicio guardarServicio(Servicio ServicioGuardar) throws Exception;

	public List<Servicio> listaServicio() throws Exception;

	public Servicio buscarServicio(Object id) throws Exception;

	public Servicio modificarServicio(Servicio ServicioGuardar) throws Exception;
	
	public List<Servicio> listaServicioPsicologo(Usuario user) throws Exception;

}
