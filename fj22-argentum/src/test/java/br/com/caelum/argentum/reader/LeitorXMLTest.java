package br.com.caelum.argentum.reader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Test;

import br.com.caelum.argentum.modelo.Candlestick;
import br.com.caelum.argentum.modelo.CandlestickFactory;
import br.com.caelum.argentum.modelo.Negociacao;

public class LeitorXMLTest {

	@Test
	public void carregaXmlComUmaNegociacaoEmListaUnitaria() {
		String xmlDeTeste = "<list>"
				+ "<Negociacao>"
				+ "<preco>43.5</preco>"
				+ "<quantidade>1000</quantidade>"
				+ "<data><time>1322233344455</time></data>"
				+ "</Negociacao>"
				+ "</list>";
		
		LeitorXML leitor = new LeitorXML();
		
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		
		List<Negociacao> negociacoes = leitor.carrega(xml);
		
		assertEquals(1, negociacoes.size());
		assertEquals(43.5, negociacoes.get(0).getPreco(), 0.01);
		assertEquals(1000, negociacoes.get(0).getQuantidade());
	}
	
	@Test
	public void carregaXmlComNenhumaNegociacaoEmListaUnitaria() {
		String xmlDeTeste = "<list></list>";
		
		LeitorXML leitor = new LeitorXML();
		
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		
		List<Negociacao> negociacoes = leitor.carrega(xml);
		
		assertEquals(0, negociacoes.size());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void carregaXmlComNegociacaoInvalida() {
		// Note1: Cannot find how to validate Schema using XStream
		// Note2: Have to use JAXB for Schema Validation
		
		String xmlDeTeste = "<list>"
				+ "<Negociacao>"
				+ "<quantidade>1000</quantidade>"
				+ "<data><time>243142351454</time></data>"
				+ "</Negociacao>"
				+ "</list>";
		
		LeitorXML leitor = new LeitorXML();
		
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		
		List<Negociacao> negociacoes = leitor.carrega(xml);

		// System.out.println(negociacoes.get(0).getPreco());
	}
	
	@Test
	public void carregaXmlComMultiplasNegociacoes() {
		String xmlN1 = "<Negociacao>"
				+ "<preco>45.0</preco>"
				+ "<quantidade>1000</quantidade>"
				+ "<data><time>" + Calendar.getInstance().getTimeInMillis() + "</time></data>"
				+ "</Negociacao>"; 
		String xmlN2 = "<Negociacao>"
				+ "<preco>35.0</preco>"
				+ "<quantidade>800</quantidade>"
				+ "<data><time>" + Calendar.getInstance().getTimeInMillis() + "</time></data>"
				+ "</Negociacao>"; 
		String xmlN3 = "<Negociacao>"
				+ "<preco>40.0</preco>"
				+ "<quantidade>1200</quantidade>"
				+ "<data><time>" + Calendar.getInstance().getTimeInMillis() + "</time></data>"
				+ "</Negociacao>"; 
		String xmlDeTeste = "<list>" + xmlN1 + xmlN2 + xmlN3 + "</list>";
		
		LeitorXML leitor = new LeitorXML();
		
		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());
		
		Calendar hoje = Calendar.getInstance();
		
		List<Negociacao> negociacoes = leitor.carrega(xml);
		
		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negociacoes);
		
		assertEquals(45.0, candle.getAbertura(), 0.00001);
		assertEquals(40.0, candle.getFechamento(), 0.00001);
		assertEquals(35.0, candle.getMinimo(), 0.00001);
		assertEquals(45.0, candle.getMaximo(), 0.00001);
		assertEquals(121000.0, candle.getVolume(), 0.00001);
	}

}
