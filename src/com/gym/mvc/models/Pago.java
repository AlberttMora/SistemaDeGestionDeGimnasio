package com.gym.mvc.models;

import java.util.Date;

public class Pago {
	private int idPago;
	private int idInscripcion;
	private double monto;
	private Date fechaPago;
	private String metodoPago;

	public Pago() {
		// TODO Auto-generated constructor stub
	}

	public Pago(int idPago, int idInscripcion, double monto, Date fechaPago, String metodoPago) {
		this.idPago = idPago;
		this.idInscripcion = idInscripcion;
		this.monto = monto;
		this.fechaPago = fechaPago;
		this.metodoPago = metodoPago;
	}

	public int getIdPago() {
		return idPago;
	}

	public void setIdPago(int idPago) {
		this.idPago = idPago;
	}

	public int getIdInscripcion() {
		return idInscripcion;
	}

	public void setIdInscripcion(int idInscripcion) {
		this.idInscripcion = idInscripcion;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}
	

}
