package com.psicovirtual.procesos.modelo.ejb.session;

import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Cita;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.ClientesPsicologo;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Horario;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Servicio;
import com.psicovirtual.procesos.modelo.ejb.entity.procesos.Usuario;

@Local
public interface SBClientePsicologoLocal {
	
	public ClientesPsicologo crearRelacionClientePsicologo(ClientesPsicologo nuevo) throws Exception;
	public int verificarRelacionExistente(ClientesPsicologo relacion) throws Exception;
	public int consultarRelacionClientePsicologo(String usuarioPsicologo, String usuarioCliente) throws Exception;
	
  	public ClientesPsicologo buscarClientesPsicologo(Object id) throws Exception;
}
