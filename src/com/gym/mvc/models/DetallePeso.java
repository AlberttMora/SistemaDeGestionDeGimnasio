package com.gym.mvc.models;

public class DetallePeso {
	private int idEquipo;
	private double pesoKg;
	

	public DetallePeso() {
		// TODO Auto-generated constructor stub
	}


	public DetallePeso(int idEquipo, double pesoKg) {
		this.idEquipo = idEquipo;
		this.pesoKg = pesoKg;
	}


	public int getIdEquipo() {
		return idEquipo;
	}


	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}


	public double getPesoKg() {
		return pesoKg;
	}


	public void setPesoKg(double pesoKg) {
		this.pesoKg = pesoKg;
	}
	

}
