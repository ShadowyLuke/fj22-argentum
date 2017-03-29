package br.com.caelum.argentum.modelo;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Test;

public class CandlestickFactoryTest {

	@Test
	public void sequenciaSimplesDeNegociacoes() {
		Calendar hoje = Calendar.getInstance();
		
		Negociacao n1 = new Negociacao(40.5, 100, hoje);
		Negociacao n2 = new Negociacao(45.0, 100, hoje);
		Negociacao n3 = new Negociacao(39.8, 100, hoje);
		Negociacao n4 = new Negociacao(42.3, 100, hoje);
		
		List<Negociacao> negociacoes = Arrays.asList(n1, n2, n3, n4);
		
		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negociacoes);
		
		assertEquals(40.5, candle.getAbertura(), 0.00001);
		assertEquals(42.3, candle.getFechamento(), 0.00001);
		assertEquals(39.8, candle.getMinimo(), 0.00001);
		assertEquals(45.0, candle.getMaximo(), 0.00001);
		assertEquals(16760.0, candle.getVolume(), 0.00001);
	}
	
	@Test
	public void semNegociacoesGeraCandleComZeros() {
		Calendar hoje = Calendar.getInstance();
		
		List<Negociacao> negociacoes = Arrays.asList();
		
		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negociacoes);
		
		assertEquals(0, candle.getAbertura(), 0.00001);
		assertEquals(0, candle.getFechamento(), 0.00001);
		assertEquals(0, candle.getMinimo(), 0.00001);
		assertEquals(0, candle.getMaximo(), 0.00001);
		assertEquals(0.0, candle.getVolume(), 0.00001);
	}
	
	@Test
	public void apenasUmaNegociacaoGeraCandleComValoresIguais() {
		Calendar hoje = Calendar.getInstance();
		
		Negociacao n = new Negociacao(40.5, 100, hoje);
		
		List<Negociacao> negociacoes = Arrays.asList(n);
		
		CandlestickFactory factory = new CandlestickFactory();
		Candlestick candle = factory.constroiCandleParaData(hoje, negociacoes);
		
		assertEquals(40.5, candle.getAbertura(), 0.00001);
		assertEquals(40.5, candle.getFechamento(), 0.00001);
		assertEquals(40.5, candle.getMinimo(), 0.00001);
		assertEquals(40.5, candle.getMaximo(), 0.00001);
		assertEquals(4050.0, candle.getVolume(), 0.00001);
	}
	
	@Test
	public void negociacoesEmOrdemCrescenteDeValor() {
		Calendar hoje = Calendar.getInstance();
		
		Negociacao n1 = new Negociacao(35.0, 100, hoje);
		Negociacao n2 = new Negociacao(40.0, 100, hoje);
		Negociacao n3 = new Negociacao(45.0, 100, hoje);
		Negociacao n4 = new Negociacao(50.0, 100, hoje);
		
		List<Negociacao> negociacoes = Arrays.asList(n1, n2, n3, n4);
		
		CandlestickFactory factory = new CandlestickFactory();
		Candlestick candle = factory.constroiCandleParaData(hoje, negociacoes);
		
		assertEquals(35.0, candle.getAbertura(), 0.00001);
		assertEquals(50.0, candle.getFechamento(), 0.00001);
		assertEquals(35.0, candle.getMinimo(), 0.00001);
		assertEquals(50.0, candle.getMaximo(), 0.00001);
		assertEquals(17000.0, candle.getVolume(), 0.00001);
	}
	
	@Test
	public void negociacoesEmOrdemDecrescenteDeValor() {
		Calendar hoje = Calendar.getInstance();
		
		Negociacao n1 = new Negociacao(35.0, 100, hoje);
		Negociacao n2 = new Negociacao(40.0, 100, hoje);
		Negociacao n3 = new Negociacao(45.0, 100, hoje);
		Negociacao n4 = new Negociacao(50.0, 100, hoje);
		
		List<Negociacao> negociacoes = Arrays.asList(n4, n3, n2, n1);
	
		CandlestickFactory factory = new CandlestickFactory();
		Candlestick candle = factory.constroiCandleParaData(hoje, negociacoes);
		
		assertEquals(50.0, candle.getAbertura(), 0.00001);
		assertEquals(35.0, candle.getFechamento(), 0.00001);
		assertEquals(35.0, candle.getMinimo(), 0.00001);
		assertEquals(50.0, candle.getMaximo(), 0.00001);
		assertEquals(17000.0, candle.getVolume(), 0.00001);
	}

}
