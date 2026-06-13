package com.gym.mvc.models;

import java.util.Date;

public class BitacoraMantenimiento {
	private int idBitacora;
	private int idEquipo;
	private int idPersonal;
	private Date fecha;
	private String descripcion;

	public BitacoraMantenimiento() {
		// TODO Auto-generated constructor stub
	}

	public BitacoraMantenimiento(int idBitacora, int idEquipo, int idPersonal, Date fecha, String descripcion) {
		this.idBitacora = idBitacora;
		this.idEquipo = idEquipo;
		this.idPersonal = idPersonal;
		this.fecha = fecha;
		this.descripcion = descripcion;
	}

	public int getIdBitacora() {
		return idBitacora;
	}

	public void setIdBitacora(int idBitacora) {
		this.idBitacora = idBitacora;
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public int getIdPersonal() {
		return idPersonal;
	}

	public void setIdPersonal(int idPersonal) {
		this.idPersonal = idPersonal;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

}
