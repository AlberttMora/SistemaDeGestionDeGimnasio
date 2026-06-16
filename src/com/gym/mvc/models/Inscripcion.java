package com.gym.mvc.models;

import java.util.Date;

public class Inscripcion {
	private int idInscripcion;
	private int idCliente;
	private int idMembresia;
	private Date fechaInicio;
	private String estado;

	public Inscripcion() {
		// TODO Auto-generated constructor stub
	}

	public Inscripcion(int idInscripcion, int idCliente, int idMembresia, Date fechaInicio, String estado) {
		this.idInscripcion = idInscripcion;
		this.idCliente = idCliente;
		this.idMembresia = idMembresia;
		this.fechaInicio = fechaInicio;
		this.estado = estado;
	}

	public int getIdInscripcion() {
		return idInscripcion;
	}

	public void setIdInscripcion(int idInscripcion) {
		this.idInscripcion = idInscripcion;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdMembresia() {
		return idMembresia;
	}

	public void setIdMembresia(int idMembresia) {
		this.idMembresia = idMembresia;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
