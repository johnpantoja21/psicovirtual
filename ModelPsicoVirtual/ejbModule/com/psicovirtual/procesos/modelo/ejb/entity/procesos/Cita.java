package com.psicovirtual.procesos.modelo.ejb.entity.procesos;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cita database table.
 * 
 */
@Entity
@NamedQuery(name="Cita.findAll", query="SELECT c FROM Cita c")
public class Cita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_CITA")
	private int idCita;

	@Column(name="ESTADO_PAGO")
	private String estadoPago;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ASIGNADA")
	private Date fechaAsignada;

	@Column(name="HORA_ASIGNADA")
	private Time horaAsignada;

	@Column(name="HORA_CANTIDAD")
	private int horaCantidad;

	@Column(name="VALOR_PAGO")
	private int valorPago;

	//bi-directional many-to-one association to ClientesPsicologo
	@ManyToOne
	@JoinColumn(name="ID_CLIE_PSICO")
	private ClientesPsicologo clientesPsicologo;

	//bi-directional many-to-one association to EstadoCita
	@ManyToOne
	@JoinColumn(name="ID_ESTADO_CITA")
	private EstadoCita estadoCita;

	//bi-directional many-to-one association to EstadoCliente
	@ManyToOne
	@JoinColumn(name="ID_ESTADO_CLIE")
	private EstadoCliente estadoCliente;

	//bi-directional many-to-one association to Servicio
	@ManyToOne
	@JoinColumn(name="ID_SERVICIO")
	private Servicio servicio;

	//bi-directional many-to-one association to TipoCita
	@ManyToOne
	@JoinColumn(name="ID_TIPO_CITA")
	private TipoCita tipoCita;

	//bi-directional many-to-one association to SeccionHistorial
	@OneToMany(mappedBy="cita")
	private List<SeccionHistorial> seccionHistorials;

	public Cita() {
	}

	public int getIdCita() {
		return this.idCita;
	}

	public void setIdCita(int idCita) {
		this.idCita = idCita;
	}

	public String getEstadoPago() {
		return this.estadoPago;
	}

	public void setEstadoPago(String estadoPago) {
		this.estadoPago = estadoPago;
	}

	public Date getFechaAsignada() {
		return this.fechaAsignada;
	}

	public void setFechaAsignada(Date fechaAsignada) {
		this.fechaAsignada = fechaAsignada;
	}

	public Time getHoraAsignada() {
		return this.horaAsignada;
	}

	public void setHoraAsignada(Time horaAsignada) {
		this.horaAsignada = horaAsignada;
	}

	public int getHoraCantidad() {
		return this.horaCantidad;
	}

	public void setHoraCantidad(int horaCantidad) {
		this.horaCantidad = horaCantidad;
	}

	public int getValorPago() {
		return this.valorPago;
	}

	public void setValorPago(int valorPago) {
		this.valorPago = valorPago;
	}

	public ClientesPsicologo getClientesPsicologo() {
		return this.clientesPsicologo;
	}

	public void setClientesPsicologo(ClientesPsicologo clientesPsicologo) {
		this.clientesPsicologo = clientesPsicologo;
	}

	public EstadoCita getEstadoCita() {
		return this.estadoCita;
	}

	public void setEstadoCita(EstadoCita estadoCita) {
		this.estadoCita = estadoCita;
	}

	public EstadoCliente getEstadoCliente() {
		return this.estadoCliente;
	}

	public void setEstadoCliente(EstadoCliente estadoCliente) {
		this.estadoCliente = estadoCliente;
	}

	public Servicio getServicio() {
		return this.servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public TipoCita getTipoCita() {
		return this.tipoCita;
	}

	public void setTipoCita(TipoCita tipoCita) {
		this.tipoCita = tipoCita;
	}

	public List<SeccionHistorial> getSeccionHistorials() {
		return this.seccionHistorials;
	}

	public void setSeccionHistorials(List<SeccionHistorial> seccionHistorials) {
		this.seccionHistorials = seccionHistorials;
	}

	public SeccionHistorial addSeccionHistorial(SeccionHistorial seccionHistorial) {
		getSeccionHistorials().add(seccionHistorial);
		seccionHistorial.setCita(this);

		return seccionHistorial;
	}

	public SeccionHistorial removeSeccionHistorial(SeccionHistorial seccionHistorial) {
		getSeccionHistorials().remove(seccionHistorial);
		seccionHistorial.setCita(null);

		return seccionHistorial;
	}

}