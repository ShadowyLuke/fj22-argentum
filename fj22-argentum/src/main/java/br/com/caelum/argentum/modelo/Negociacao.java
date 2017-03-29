package br.com.caelum.argentum.modelo;

import java.util.Calendar;

public final class Negociacao {
	// Variables
	private final double preco;
	private final int quantidade;
	private final Calendar data;
	
	// Constructor
	public Negociacao(double preco, int quantidade, Calendar data) {
		if(data == null) {
			throw new IllegalArgumentException("Data nao pode ser nula!");
		}
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = data;
	}

	// Getters
	public double getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Calendar getData() {
		return (Calendar) this.data.clone();
	}
	
	// Methods
	public double getVolume() {
		return preco * quantidade;
	}
	
	
}
