package br.com.caelum.argentum.modelo;

import java.util.Calendar;

@XmlRootElement
public final class Negociacao implements Comparable<Negociacao> {
	// Variables
	private final double preco;
	private final int quantidade;
	private final Calendar data;
	
	// Constructor
	public Negociacao(double preco, int quantidade, Calendar data) {
		if(preco < 0) {
			throw new IllegalArgumentException("Negociacao(): Preco nao pode ser menor que zero!");
		}
		if(quantidade < 0) {
			throw new IllegalArgumentException("Negociacao(): Quantidade nao pode ser menor que zero!");
		}
		if(data == null) {
			throw new IllegalArgumentException("Negociacao(): Data nao pode ser nula!");
		}
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = (Calendar) data.clone();
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

	public boolean isMesmoDia(Calendar otherData) {
		return this.data.get(Calendar.DAY_OF_MONTH) == otherData.get(Calendar.DAY_OF_MONTH) 
			   && this.data.get(Calendar.MONTH) == otherData.get(Calendar.MONTH) 
			   && this.data.get(Calendar.YEAR) == otherData.get(Calendar.YEAR);
	}

	@Override
	public int compareTo(Negociacao other) {
		return this.data.compareTo(other.getData());
	}
	
}
