package com.psicovirtual.estandar.modelo.ejb.session;


import java.util.List;

import javax.ejb.Local;

import com.psicovirtual.estandar.modelo.excepciones.BaseException;


@Local
public interface SBEjecutarQueryProcesosLocal {

 public List ejecutarQuery(String sql)throws BaseException;

}
