package com.gym.mvc.models;

public class Limpieza extends Personal {
	private String turno;
	private String areaAsignada;
	

	public Limpieza() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Limpieza(int idPersonal, String nombre, String apellido, String email, String telefono, String tipoRol,
			String contrasena,String turno, String areaAsignada) {
		super(idPersonal, nombre, apellido, email, telefono, tipoRol, contrasena);
		this.turno=turno;
		this.areaAsignada=areaAsignada;
		// TODO Auto-generated constructor stub
	}


	public String getTurno() {
		return turno;
	}


	public void setTurno(String turno) {
		this.turno = turno;
	}


	public String getAreaAsignada() {
		return areaAsignada;
	}


	public void setAreaAsignada(String areaAsignada) {
		this.areaAsignada = areaAsignada;
	}
	

}
