package com.almundo.director.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.almundo.director.model.Director;
import com.almundo.director.payload.DirectorResponse;

/**
 * @author https://github.com/efat07 - Eyner Arias - efat07@gmail.com
 *
 */

@RestController
@CrossOrigin(origins = "*")
public class DirectorController {

    @GetMapping("/validarDispDirector")
    public ResponseEntity<?> validarDisponibilidad() {
        int segundosDemora = 0;
    	Director director = Director.getInstanciaSingleton();
    	segundosDemora = director.verificaDisponibilidadSegundo();
    	int tiempoLlamada = Integer.parseInt(director.getTiempoLlamada().toString());
        if(segundosDemora >= tiempoLlamada) {
        	segundosDemora = 0;
        	director.setTiempoInicio(0L);
        	director.setTiempoFinal(0L);
        }else {
        	segundosDemora = tiempoLlamada - segundosDemora;
        	segundosDemora = Math.abs(segundosDemora);
        }
    	DirectorResponse directorResponse = new DirectorResponse();
    	if(director.getLlamada() != null) {
    		directorResponse.setIdLlamada(director.getLlamada().getLlamadaId());
    	}
    	directorResponse.setDuracionAtendiendo(segundosDemora);
    	directorResponse.setCantidadEnCola(0);
    	return ResponseEntity.ok(directorResponse);
    }
}