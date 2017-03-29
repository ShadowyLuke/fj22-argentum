package br.com.caelum.argentum.modelo;

import java.util.Calendar;

public class CandlestickBuilder {
	// Variables
	private double abertura;
	private double fechamento;
	private double minimo;
	private double maximo;
	private double volume;
	private Calendar data;
	
	private boolean temAbertura;
	private boolean temFechamento;
	private boolean temMinimo;
	private boolean temMaximo;
	private boolean temVolume;
	private boolean temData;
	
	// Constructors
	public CandlestickBuilder() {
		this.temAbertura = false;
		this.temFechamento = false;
		this.temMinimo = false;
		this.temMaximo = false;
		this.temVolume = false;
		this.temData = false;
	}
	
	// Setters
	public CandlestickBuilder comAbertura(double abertura) {
		this.abertura = abertura;
		return this;
	}
	
	public CandlestickBuilder comFechamento(double fechamento) {
		this.fechamento = fechamento;
		return this;
	}
	
	public CandlestickBuilder comMinimo(double minimo) {
		this.minimo = minimo;
		return this;
	}
	
	public CandlestickBuilder comMaximo(double maximo) {
		this.maximo = maximo;
		return this;
	}
	
	public CandlestickBuilder comVolume(double volume) {
		this.volume = volume;
		return this;
	}
	
	public CandlestickBuilder comData(Calendar data) {
		this.data = data;
		return this;
	}
	
	public Candlestick geraCandle() {
		if (!temAbertura || !temFechamento || !temMinimo || !temMaximo || !temVolume || !temData) {
			throw new IllegalStateException("Nao todos os valores do CandlestickBuilder foram inseridos!");
		}
		return new Candlestick(this.abertura, this.fechamento, this.minimo, this.maximo, this.volume, this.data);
	}
}
