package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.procesos.TipoUsuario;

@Local
public interface SBTipoUsuarioLocal {
	public TipoUsuario guardarTipoUsuario(TipoUsuario tipoUsuarioGuardar) throws Exception;

	public List<TipoUsuario> listaTipoUsuario() throws Exception;

	public TipoUsuario buscarTipoUsuario(Object id) throws Exception;

	public TipoUsuario modificarTipoUsuario(TipoUsuario tipoUsuarioGuardar) throws Exception;

}
