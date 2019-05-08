package com.almundo.director.model;

import java.util.concurrent.TimeUnit;

/**
 * @author https://github.com/efat07 - Eyner Arias - efat07@gmail.com
 *
 */

public class Director {

	private Integer directorId = null;
	private Long tiempoLlamada = 0L;
	private Long tiempoInicio = 0L;
	private Long tiempoFinal = 0L;
	private Llamada llamada = null;
	private static Director director;
	
	private Director() {
	}
	
	private Director(Integer directorId, Llamada llamada) {
		this.directorId = directorId;
		this.llamada = llamada;
	}
	
	public static Director getInstanciaSingleton() {
		if(director == null) {
			director = new Director();
		}
		return director;
	}
	
	public static Director getInstanciaSingleton(Integer directorId, Llamada llamada) {
		if(director == null) {
			director = new Director(directorId,llamada);
		}
		return director;
	}
	
	public void correrTiempo() {
		try {
			tiempoInicio = System.nanoTime();
			tiempoFinal  = 0L;
			tiempoLlamada = getNumeroAleatorioEntreDosNumeros(5,10);
			TimeUnit.SECONDS.sleep(tiempoLlamada);
			tiempoFinal = System.nanoTime();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int verificaDisponibilidadSegundo() {
		long output = tiempoFinal - tiempoInicio;
		return (int) ((output / 1000000)/1000);
	}
	
	private long getNumeroAleatorioEntreDosNumeros(int min, int max) {
		max=(max)+1;
        long resultado = ((long)(Math.random()*(max-min))+min);
		return resultado;
	}

	public Integer getOperadorId() {
		return directorId;
	}
	public void setOperadorId(Integer directorId) {
		this.directorId = directorId;
	}
	public Llamada getLlamada() {
		return llamada;
	}
	public void setLlamada(Llamada llamada) {
		this.llamada = llamada;
	}

	public Long getTiempoLlamada() {
		return tiempoLlamada;
	}

	public void setTiempoLlamada(Long tiempoLlamada) {
		this.tiempoLlamada = tiempoLlamada;
	}
	
	public Long getTiempoInicio() {
		return tiempoInicio;
	}
	
	public void setTiempoInicio(Long tiempoInicio) {
		this.tiempoInicio = tiempoInicio;
	}
	public Long getTiempoFinal() {
		return tiempoFinal;
	}
	
	public void setTiempoFinal(Long tiempoFinal) {
		this.tiempoFinal = tiempoFinal;
	}
}
