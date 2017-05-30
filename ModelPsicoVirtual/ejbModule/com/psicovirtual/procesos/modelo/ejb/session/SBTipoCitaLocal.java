package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.procesos.TipoCita;

@Local
public interface SBTipoCitaLocal {

	public TipoCita buscarTipoCita(Object id) throws Exception;

	public List<TipoCita> listaTipoCita() throws Exception;

	public TipoCita modificarTipoCita(TipoCita tipoCitaGuardar) throws Exception;

	public TipoCita guardarTipoCita(TipoCita tipoCitaGuardar) throws Exception;

}
