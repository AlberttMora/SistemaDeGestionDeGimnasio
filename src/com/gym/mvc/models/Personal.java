package com.gym.mvc.models;

public abstract class Personal {
	private int idPersonal;
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private String tipoRol;
	private String contrasena;
	
	public Personal() {
		
	}
	
	public Personal(int idPersonal, String nombre, String apellido, String email, String telefono, String tipoRol,
			String contrasena) {
		this.idPersonal = idPersonal;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.tipoRol = tipoRol;
		this.contrasena = contrasena;
	}

	public int getIdPersonal() {
		return idPersonal;
	}

	public void setIdPersonal(int idPersonal) {
		this.idPersonal = idPersonal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTipoRol() {
		return tipoRol;
	}

	public void setTipoRol(String tipoRol) {
		this.tipoRol = tipoRol;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	

}
