package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.procesos.EstadoCliente;

@Local
public interface SBEstadoClienteLocal {

	
	
	
	
	public EstadoCliente guardarEstadoCliente(EstadoCliente estadoClienteGuardar) throws Exception;

	public EstadoCliente modificarEstadoCliente(EstadoCliente estadoClienteGuardar) throws Exception;

	public List<EstadoCliente> listaEstadoCliente() throws Exception;

	public EstadoCliente buscarEstadoCliente(Object id) throws Exception;
}
