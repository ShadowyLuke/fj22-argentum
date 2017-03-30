package br.com.caelum.argentum.modelo;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;
import org.junit.Test;

public class NegociacaoTest {

	@Test
	public void dataDaNegociacaoEhImutavel() {
		// se criar um negocio no dia 15...
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 15);
		Negociacao n = new Negociacao(10, 5, c);
		
		// ainda que eu tente mudar a data para 20...
		n.getData().set(Calendar.DAY_OF_MONTH, 20);
		
		// ele continua no dia 15
		assertEquals(15, n.getData().get(Calendar.DAY_OF_MONTH));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoCriaNegociacaoComPrecoNegativo() {
		new Negociacao(-10, 5, Calendar.getInstance());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoCriaNegociacaoComQuantidadeNegativa() {
		new Negociacao(10, -5, Calendar.getInstance());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoCriaNegociacaoComDataNula() {
		new Negociacao(10, 5, null);
	}
	
	@Test
	public void comparaDatasExatamenteIguais() {
		Calendar agora = Calendar.getInstance();
		Calendar mesmoMomento = (Calendar) agora.clone();
		
		Negociacao n1 = new Negociacao(10, 10, agora);
		Negociacao n2 = new Negociacao(10, 10, mesmoMomento);
		
		boolean resultado = n1.isMesmoDia(n2.getData());
		
		assertTrue(resultado);
	}
	
	@Test
	public void comparaDatasMesmoDia() {
		Calendar data1 = new GregorianCalendar(29, 03, 2017, 8, 0, 0);
		Negociacao n1 = new Negociacao(10, 10, data1);
		Calendar data2 = new GregorianCalendar(29, 03, 2017, 10, 15, 12);
		Negociacao n2 = new Negociacao(10, 10, data2);
		
		boolean resultado = n1.isMesmoDia(n2.getData());
		
		assertTrue(resultado);
	}
	
	@Test
	public void comparaDatasMesmoDiaComMesDiferente() {
		Calendar data1 = new GregorianCalendar(29, 03, 2017, 8, 0, 0);
		Negociacao n1 = new Negociacao(10, 10, data1);
		Calendar data2 = new GregorianCalendar(29, 04, 2017, 10, 15, 12);
		Negociacao n2 = new Negociacao(10, 10, data2);
		
		boolean resultado = n1.isMesmoDia(n2.getData());
		
		assertFalse(resultado);
	}
	
	@Test
	public void comparaDatasMesmoDiaComAnoDiferente() {
		Calendar data1 = new GregorianCalendar(29, 03, 2017, 8, 0, 0);
		Negociacao n1 = new Negociacao(10, 10, data1);
		Calendar data2 = new GregorianCalendar(29, 03, 2016, 10, 15, 12);
		Negociacao n2 = new Negociacao(10, 10, data2);
		
		boolean resultado = n1.isMesmoDia(n2.getData());
		
		assertFalse(resultado);
	}

}
