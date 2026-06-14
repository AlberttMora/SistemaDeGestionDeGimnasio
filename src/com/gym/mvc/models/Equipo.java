package com.gym.mvc.models;

import java.util.Date;

public class Equipo {
	private int idEquipo;
	private int idTipo;
	private String nombre;
	private String estado;
	private String fotoRuta;
	private Date fechaAdquisicion;

	public Equipo() {
		// TODO Auto-generated constructor stub
	}

	public Equipo(int idEquipo, int idTipo, String nombre, String estado, String fotoRuta, Date fechaAdquisicion) {
		this.idEquipo = idEquipo;
		this.idTipo = idTipo;
		this.nombre = nombre;
		this.estado = estado;
		this.fotoRuta = fotoRuta;
		this.fechaAdquisicion = fechaAdquisicion;
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public int getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFotoRuta() {
		return fotoRuta;
	}

	public void setFotoRuta(String fotoRuta) {
		this.fotoRuta = fotoRuta;
	}

	public Date getFechaAdquisicion() {
		return fechaAdquisicion;
	}

	public void setFechaAdquisicion(Date fechaAdquisicion) {
		this.fechaAdquisicion = fechaAdquisicion;
	}
	

}
