package com.gym.mvc.models;

import java.util.Date;

public class AsignacionEntrenador {
	private int idAsignacion;
	private int idEntrenador;
	private int  idCliente;
	private Date fechaInicio;
	private Date fechaFin;
	private String observaciones;
	

	public AsignacionEntrenador() {
		// TODO Auto-generated constructor stub
	}


	public AsignacionEntrenador(int idAsignacion, int idEntrenador, int idCliente, Date fechaInicio ,Date fechaFin, 
			String observaciones) {
		this.idAsignacion = idAsignacion;
		this.idEntrenador = idEntrenador;
		this.idCliente = idCliente;
		this.fechaFin = fechaFin;
		this.fechaInicio = fechaInicio;
		this.observaciones = observaciones;
	}


	public int getIdAsignacion() {
		return idAsignacion;
	}


	public void setIdAsignacion(int idAsignacion) {
		this.idAsignacion = idAsignacion;
	}


	public int getIdEntrenador() {
		return idEntrenador;
	}


	public void setIdEntrenador(int idEntrenador) {
		this.idEntrenador = idEntrenador;
	}


	public int getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}


	public Date getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	public String getObservaciones() {
		return observaciones;
	}


	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}


	public Date getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	

}
