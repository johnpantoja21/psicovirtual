package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Tema;

@Local
public interface SBTemaLocal {

	public Tema guardarTema(Tema temaGuardar) throws Exception;

	public Tema modificarTema(Tema temaGuardar) throws Exception;

	public List<Tema> listaTema() throws Exception;

	public Tema buscarTema(Object id) throws Exception;

}
