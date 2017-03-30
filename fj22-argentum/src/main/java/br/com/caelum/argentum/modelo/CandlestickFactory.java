package br.com.caelum.argentum.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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

	public List<Candlestick> constroiCandles(List<Negociacao> negociacoes) throws IllegalStateException {
		// Sorts given Negociacao List, to make sure its in order
		Collections.sort(negociacoes);
		
		// Sets up the Lists to be used to store both temporary Negociacoes and resulting Candlesticks
		List<Candlestick> candles = new ArrayList<>();	
		List<Negociacao> negociacoesDoDia = new ArrayList<>();
		Calendar dataAtual = negociacoes.get(0).getData();
		
		for (Negociacao n : negociacoes) {
			// primeiro verifica se a data nao occoreu antes da dataAtual (fora de ordem)
			if (n.getData().before(dataAtual)) {
				throw new IllegalStateException("CandlestickFactory.constroiCandles(): Lista de Negociacoes esta fora de ordem!");
			}
			// caso nao esteja fora de ordem e nao eh o mesmo dia, 
			// fecha a candle e reinicia variaveis
			if (!n.isMesmoDia(dataAtual)) {
				candles.add(constroiCandleParaData(dataAtual, negociacoesDoDia));
				negociacoesDoDia = new ArrayList<>();
				dataAtual = n.getData();
			}

			// adiciona a Negociacao atual para a lista
			negociacoesDoDia.add(n);
		}
		
		// adiciona o ultimo candle para a lista
		candles.add(constroiCandleParaData(dataAtual, negociacoesDoDia));
		
		return candles;
	}
	
	
}
