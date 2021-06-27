package com.pru.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pru.dao.TipoCambioDAO;
import com.pru.dto.CambioMoneda;
import com.pru.model.TipoCambio;
import com.pru.service.IMonedaService;

@Service
public class IMonedaServiceImpl  implements IMonedaService{
	@Autowired
	private TipoCambioDAO dao;
	
	@Override
	public CambioMoneda cambioMoneda(CambioMoneda cambio) {
		
		CambioMoneda salida = new CambioMoneda();
		// Añadiendo tipos de cambio
		List<TipoCambio> listaTipoCambio = TipoCambioDisponible();
		
		//Buscamos el tipo de cambio que trabaje con los datos ingresados
		String ingMonOriginal = cambio.getMonedaOrigen().trim().toLowerCase();
		String ingMonDestino  = cambio.getMonedaDestino().trim().toLowerCase();
		listaTipoCambio = buscarTc(listaTipoCambio, ingMonOriginal, ingMonDestino);
		
		
		//Seteamos algunos valores salida
		salida.setMonto(cambio.getMonto());
		salida.setMonedaOrigen(ingMonOriginal);
		salida.setMonedaDestino(ingMonDestino);
		
		//validamos que el cambio no se haga sobre el mismo tipo de moneda
		if(listaTipoCambio.size()== 0 || listaTipoCambio==null) {
			salida.setMontoCambiado(cambio.getMonto());
			salida.setTipoCambio(new Double("1"));	
		}
		else {
			double nuevoMonto = new Double("0.00");
			double tasaCambio =  listaTipoCambio.get(0).getTasaCambio();
			
			if(ingMonDestino.equals(listaTipoCambio.get(0).getPrimeraMoneda())) {
				nuevoMonto = cambio.getMonto()/tasaCambio;
			} else {
				nuevoMonto = tasaCambio*cambio.getMonto();
			}
			
			// seteamos la salida
			salida.setMontoCambiado(nuevoMonto);
			salida.setTipoCambio(tasaCambio);	
		}
		
		
		return salida;
	}
	
	@Override
	public TipoCambio actualizarTipoCambio(TipoCambio tipo) {
		
		TipoCambio objSalida = new TipoCambio();
		
		//Buscamos el tipo de cambio que trabaje con los datos ingresados
		String monOriginal = tipo.getPrimeraMoneda().trim().toLowerCase();
		String monDestino  = tipo.getSegundaMoneda().trim().toLowerCase();
		
		//listamos todos lo tipos de cambio registrados
		List<TipoCambio> listaTipoCambio = dao.findAll();
		
		//Buscamos el tipo de cambio que se quiere
		listaTipoCambio = buscarTc(listaTipoCambio,monOriginal, monDestino);
		
		//Seteamos los valores finales
		objSalida = listaTipoCambio.get(0);
		if(listaTipoCambio.size()!= 0 || listaTipoCambio!=null) {
			objSalida.setTasaCambio(tipo.getTasaCambio());
		}
		return dao.save(objSalida);
	}
	
	@Override
	public List<TipoCambio>listarTipoCambio(){
		return dao.findAll();
	}
	
	//Registrando tipos de cambio
	private List<TipoCambio>TipoCambioDisponible() {
		
		List<TipoCambio> listaSalida = dao.findAll();
		TipoCambio obj = new TipoCambio();
		
		if(listaSalida.size()==0) {
			// Llenamos y actualizamos los tipos de cambios que queremos (dependiendo del dia)
			// dolares a soles
			obj.setPrimeraMoneda("dolar");
			obj.setTasaCambio(new Double("3.99"));
			obj.setSegundaMoneda("sol");
			listaSalida.add(obj);

			// euros a soles
			obj = new TipoCambio();
			obj.setPrimeraMoneda("euro");
			obj.setTasaCambio(new Double("4.75"));
			obj.setSegundaMoneda("sol");
			listaSalida.add(obj);
		
			//Registramos los tipos de cambio ingresados
			listaSalida.stream().forEach(tc -> dao.save(tc));
		}
		return listaSalida;
	}
	
	//Buscando tipo de cambio
	private List<TipoCambio> buscarTc (List<TipoCambio> lista,String monOriginal, String monDestino) {
		return lista.stream()
				.filter(tc -> tc.getPrimeraMoneda().equals(monOriginal) && tc.getSegundaMoneda().equals(monDestino) ||
						tc.getPrimeraMoneda().equals(monDestino) && tc.getSegundaMoneda().equals(monOriginal))
				.collect(Collectors.toList());

	}
}
