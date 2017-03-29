package br.com.caelum.argentum.modelo;

import java.util.Calendar;
import java.util.List;

public class CandlestickFactory {
	public Candlestick constroiCandleParaData(Calendar data, List<Negociacao> negociacoes) {
		double abertura = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
		double fechamento = negociacoes.isEmpty() ? 0 : negociacoes.get(negociacoes.size()-1).getPreco();
		
		double maximo = 0;
		double minimo = abertura;
		double volume = 0;
		
		// digite foreach e de um ctrl + escpaco para ajudar a
		// criar o bloco abaixo
		for (Negociacao n : negociacoes) {
			volume += n.getVolume();
			
			if(n.getPreco() > maximo) {
				maximo = n.getPreco();
			} 
			if (n.getPreco() < minimo) {
				minimo = n.getPreco();
			}
		}
		
		return new Candlestick(abertura, fechamento, minimo, maximo, volume, data);
	}
}
