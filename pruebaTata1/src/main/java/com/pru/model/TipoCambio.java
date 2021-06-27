package com.pru.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoCambio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String primeraMoneda;
	private double tasaCambio;
	private String segundaMoneda;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getPrimeraMoneda() {
		return primeraMoneda;
	}

	public void setPrimeraMoneda(String primeraMoneda) {
		this.primeraMoneda = primeraMoneda;
	}

	public String getSegundaMoneda() {
		return segundaMoneda;
	}

	public void setSegundaMoneda(String segundaMoneda) {
		this.segundaMoneda = segundaMoneda;
	}

	public double getTasaCambio() {
		return tasaCambio;
	}
	public void setTasaCambio(double tasaCambio) {
		this.tasaCambio = tasaCambio;
	}
}
