package com.pru.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pru.dto.CambioMoneda;
import com.pru.model.TipoCambio;
import com.pru.service.IMonedaService;

@RestController
@RequestMapping("/moneda")
public class MonedaController {

	@Autowired
	private IMonedaService service;
	
    @PostMapping(value = "/cambiarMoneda", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> cambiarMoneda(@Validated @RequestBody CambioMoneda cambio){
        CambioMoneda salida = service.cambioMoneda(cambio);
        return new ResponseEntity<Object>(salida, HttpStatus.OK);
    }
    
    @PostMapping(value = "/actualizarTipoCambio", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> actualizarTipoCambio(@Validated @RequestBody TipoCambio tipo){
        TipoCambio salida = service.actualizarTipoCambio(tipo);
        return new ResponseEntity<Object>(salida, HttpStatus.OK);
    }
    
    @GetMapping(value = "/listarTipoCambio")
	public ResponseEntity<List<TipoCambio>> listarTipoCambio(){
    	List<TipoCambio> salida =service.listarTipoCambio();
		 return new ResponseEntity<List<TipoCambio>>(salida, HttpStatus.OK);
	}
}
