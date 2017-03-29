package br.com.caelum.argentum.modelo;

import org.junit.Test;

public class CandlestickBuilderTest {

	@Test(expected=IllegalStateException.class)
	public void geracaoDeCandleDeveTerTodosOsDadosNecessarios() {
		CandlestickBuilder builder = new CandlestickBuilder();
		
		builder.comAbertura(10).comMinimo(10).comVolume(4000).geraCandle();
	}

}
