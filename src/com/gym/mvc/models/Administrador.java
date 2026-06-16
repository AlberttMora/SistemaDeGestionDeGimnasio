package com.gym.mvc.models;

public class Administrador extends Personal {
	private String nivelAcceso;

	public Administrador() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Administrador(int idPersonal, String nombre, String apellido, String email, String telefono,
			String contrasena, String nivelAcceso) {
		super(idPersonal, nombre, apellido, email, telefono, contrasena);
		this.nivelAcceso = nivelAcceso;
		// TODO Auto-generated constructor stub
	}

	public String getNivelAcceso() {
		return nivelAcceso;
	}

	public void setNivelAcceso(String nivelAcceso) {
		this.nivelAcceso = nivelAcceso;
	}

}
