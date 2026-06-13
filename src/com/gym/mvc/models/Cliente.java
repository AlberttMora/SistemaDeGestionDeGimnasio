package com.gym.mvc.models;

import java.util.Date;

public class Cliente {
	private int idCliente;
	private String nombre;
	 private String apellido;
	 private String email;
	 private String telefono;
	 private Date fechaNac;
	 private String fotoRuta;
	

	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public Cliente(int idCliente, String nombre, String apellido, String email, String telefono, Date fechaNac,
			String fotoRuta) {
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.fechaNac = fechaNac;
		this.fotoRuta = fotoRuta;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
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

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getFotoRuta() {
		return fotoRuta;
	}

	public void setFotoRuta(String fotoRuta) {
		this.fotoRuta = fotoRuta;
	}
	 
	

}
