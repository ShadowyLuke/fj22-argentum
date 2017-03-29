package br.com.caelum.argentum.modelo;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Test;

public class CandlestickTest {

	@Test(expected=IllegalArgumentException.class)
	public void precoMaximoNaoPodeSerMenorQueMinimo () {
		new Candlestick(10, 20, 20, 10, 10000, Calendar.getInstance());	
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void precoAberturaNaoPodeSerMenorQueZero () {
		new Candlestick(-10, 10, 10, 20, 10000, Calendar.getInstance());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void precoFechamentoNaoPodeSerMenorQueZero () {
		new Candlestick(10, -10, 10, 20, 10000, Calendar.getInstance());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void precoMinimoNaoPodeSerMenorQueZero () {
		new Candlestick(10, 20, -10, 20, 10000, Calendar.getInstance());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void precoMaximoNaoPodeSerMenorQueZero () {
		new Candlestick(10, 20, 10, -20, 10000, Calendar.getInstance());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void volumeTotalNaoPodeSerMenorQueZero () {
		new Candlestick(10, 20, 10, 20, -10000, Calendar.getInstance());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoCriaCandlestickComDataNula() {
		new Candlestick(10, 20, 10, 20, 10000, null);
	}
	
	@Test
	public void quandoAberturaIgualFechamentoEhAlta() {
		Calendar hoje = Calendar.getInstance();
		
		Negociacao n1 = new Negociacao(40.5, 100, hoje);
		Negociacao n2 = new Negociacao(37.3, 100, hoje);
		Negociacao n3 = new Negociacao(42.4, 100, hoje);
		Negociacao n4 = new Negociacao(40.5, 100, hoje);
		
		List<Negociacao> negociacoes = Arrays.asList(n1, n2, n3, n4);
		
		CandlestickFactory factory = new CandlestickFactory();
		Candlestick candle = factory.constroiCandleParaData(hoje, negociacoes);
				
		assertEquals(true, candle.isAlta());
	}

}
