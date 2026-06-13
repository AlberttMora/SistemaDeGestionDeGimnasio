package com.gym.mvc.models;

public class Membresia {
	private int idMembresia;
	private String tipo;
	private double precio;
	private int duracion;

	public Membresia() {
		// TODO Auto-generated constructor stub
	}

	public Membresia(int idMembresia, String tipo, double precio, int duracion) {
		this.idMembresia = idMembresia;
		this.tipo = tipo;
		this.precio = precio;
		this.duracion = duracion;
	}

	public int getIdMembresia() {
		return idMembresia;
	}

	public void setIdMembresia(int idMembresia) {
		this.idMembresia = idMembresia;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	

}
