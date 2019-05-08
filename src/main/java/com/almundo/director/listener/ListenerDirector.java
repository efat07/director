package com.almundo.director.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.almundo.director.constantes.Constante;
import com.almundo.director.model.Llamada;
import com.almundo.director.model.Director;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author https://github.com/efat07 - Eyner Arias - efat07@gmail.com
 *
 */

@Component
public class ListenerDirector {
	
	ObjectMapper mapper = new ObjectMapper();
	Llamada llamada = null;
	
	@JmsListener(destination = Constante.nombreColaDirector, containerFactory = Constante.nombreFactory)
	public void onMessage( final Message msgLlamadaIn ) throws JMSException {
		
		try {
			System.out.println("Llamada Recibida por el Director: ");
			TextMessage msgLlamada = (TextMessage) msgLlamadaIn;
			System.out.println( msgLlamada.getText() );
			
			llamada = mapper.readValue(msgLlamada.getText(), Llamada.class);
			Director director = Director.getInstanciaSingleton(1, llamada);
			director.correrTiempo();
			System.out.println("Pasaron " + director.verificaDisponibilidadSegundo() + " segundos");
			System.out.println("________________________________________________");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
