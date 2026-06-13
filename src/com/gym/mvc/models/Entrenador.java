package com.gym.mvc.models;

public class Entrenador extends Personal{
	private String especialidad;
	private String certificacion;

	public Entrenador() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Entrenador(int idPersonal, String nombre, String apellido, String email, String telefono, String tipoRol,
			String contrasena,String especialidad,String certificacion) {
		super(idPersonal, nombre, apellido, email, telefono, tipoRol, contrasena);
		this.especialidad=especialidad;
		this.certificacion=certificacion;
		// TODO Auto-generated constructor stub
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public String getCertificacion() {
		return certificacion;
	}

	public void setCertificacion(String certificacion) {
		this.certificacion = certificacion;
	}	
}