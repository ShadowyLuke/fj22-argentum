package br.com.caelum.argentum.modelo;

import java.util.Calendar;

public final class Negociacao {
	// Variables
	private final double preco;
	private final int quantidade;
	private final Calendar data;
	
	// Constructor
	public Negociacao(double preco, int quantidade, Calendar data) {
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
		return data;
	}
	
	// Methods
	public double getVolume() {
		return preco * quantidade;
	}
	
	
}
