package com.pru.service;

import java.util.List;

import com.pru.dto.CambioMoneda;
import com.pru.model.TipoCambio;

public interface IMonedaService {

	CambioMoneda cambioMoneda(CambioMoneda cambio);
	
	TipoCambio actualizarTipoCambio(TipoCambio tipo);
	
	List<TipoCambio>listarTipoCambio();
}
