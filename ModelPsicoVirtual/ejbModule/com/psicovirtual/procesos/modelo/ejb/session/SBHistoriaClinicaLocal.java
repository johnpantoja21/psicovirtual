package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Cita;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.SeccionHistorial;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Tema;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Usuario;

@Local
public interface SBHistoriaClinicaLocal {

	public SeccionHistorial guardarHistoria(SeccionHistorial HistoriaClinicaGuardar) throws Exception;

	public SeccionHistorial modificarHistoriaClinica(SeccionHistorial HistoriaClinicaGuardar) throws Exception;

	public List<SeccionHistorial> buscarHistoriaClinicaCiente(Usuario user) throws Exception;

	public List<Cita> consultarCitasPagadasClientePsico(Usuario user) throws Exception;
	
}
