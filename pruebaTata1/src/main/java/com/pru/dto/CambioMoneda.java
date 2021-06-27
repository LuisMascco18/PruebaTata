package com.pru.dto;

import com.sun.istack.NotNull;

public class CambioMoneda {
	
	@NotNull
	private String monedaOrigen;
	
	@NotNull
	private double monto;
	
	@NotNull
	private String monedaDestino;
	
	private double montoCambiado;

	private double tipoCambio;
	
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public double getMontoCambiado() {
		return montoCambiado;
	}
	public void setMontoCambiado(double montoCambiado) {
		this.montoCambiado = montoCambiado;
	}
	public String getMonedaOrigen() {
		return monedaOrigen;
	}
	public void setMonedaOrigen(String monedaOrigen) {
		this.monedaOrigen = monedaOrigen;
	}
	public String getMonedaDestino() {
		return monedaDestino;
	}
	public void setMonedaDestino(String monedaDestino) {
		this.monedaDestino = monedaDestino;
	}
	public double getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(double tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	
	
}
