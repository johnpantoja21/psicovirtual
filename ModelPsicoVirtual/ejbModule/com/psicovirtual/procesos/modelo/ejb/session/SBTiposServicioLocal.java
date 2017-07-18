package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.procesos.TiposServicio;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Usuario;

@Local
public interface SBTiposServicioLocal {

	public TiposServicio guardarTiposServicio(TiposServicio tiposServicioGuardar) throws Exception;

	public List<TiposServicio> listaTiposServicio() throws Exception;

	public TiposServicio buscarTiposServicio(Object id) throws Exception;

	public TiposServicio modificarTiposServicio(TiposServicio tiposServicioGuardar) throws Exception;
	
	public List<TiposServicio> listaTiposServicioActivos() throws Exception;

}
