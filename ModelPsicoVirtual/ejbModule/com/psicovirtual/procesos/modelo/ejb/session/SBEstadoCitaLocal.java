package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.procesos.EstadoCita;

@Local
public interface SBEstadoCitaLocal {

	
	
	

	public EstadoCita guardarEstadoCita(EstadoCita estadoCitaGuardar) throws Exception;

	public EstadoCita modificarEstadoCita(EstadoCita estadoCitaGuardar) throws Exception;

	public List<EstadoCita> listaEstadoCita() throws Exception;

	public EstadoCita buscarEstadoCita(Object id) throws Exception;
}
