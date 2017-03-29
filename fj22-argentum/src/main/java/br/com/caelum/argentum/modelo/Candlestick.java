package br.com.caelum.argentum.modelo;

import java.util.Calendar;

public final class Candlestick {
	// Variables
	private final double abertura;
	private final double fechamento;
	private final double minimo;
	private final double maximo;
	private final double volume;
	private final Calendar data;
	
	// Constructor
	public Candlestick(double abertura, double fechamento, double minimo, double maximo, double volume, Calendar data) {
		if (abertura < 0 || fechamento < 0 || minimo < 0 || maximo < 0 || volume < 0) {
			throw new IllegalArgumentException("Nenhum valor de Candlestick pode ser menor que zero!");
		}
		if (data == null) {
			throw new IllegalArgumentException("Data nao pode ser nula!");
		}
		if (minimo > maximo) {
			throw new IllegalArgumentException("Valor Minimo nao deve ser maior que valor Maximo!");
		}
		this.abertura = abertura;
		this.fechamento = fechamento;
		this.minimo = minimo;
		this.maximo = maximo;
		this.volume = volume;
		this.data = data;
	}

	// Getters
	public double getAbertura() {
		return abertura;
	}

	public double getFechamento() {
		return fechamento;
	}

	public double getMinimo() {
		return minimo;
	}

	public double getMaximo() {
		return maximo;
	}

	public double getVolume() {
		return volume;
	}

	public Calendar getData() {
		return data;
	}
	
	// Methods
	public boolean isAlta() {
		return this.abertura <= this.fechamento;
	}
	
	public boolean isBaixa() {
		return this.abertura > this.fechamento;
	}
	
	@Override
	public String toString() {
		String s = "[Abertura " + this.abertura + 
					", Fechamento " + this.fechamento +
					", Maximo " + this.maximo + 
					", Minimo " + this.minimo +
					", Volume " + this.volume + 
					", Data " + this.data.getTime() + "]"; 
					
		return s;
	}
}
