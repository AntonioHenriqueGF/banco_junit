package br.com.cefet.banco.negocio;

import static org.junit.Assert.*;

import org.junit.Test;

public class AtualizadorDeContasTest {
	
	@Test
	public void roda_contaCorrenteCom100DeSaldo25Selic_devolve50DeSaldo() {
		// Preparação dos dados
		Conta c = new ContaCorrente(100.00f);
		double selic = 25.00f;
		AtualizadorDeContas atualizador = new AtualizadorDeContas(selic);
		
		// Roteiro
		atualizador.roda(c);
		
		// Verificação
		assertEquals(50.0f, c.getSaldo(), 0);
	}
	
	@Test
	public void roda_contaPoupancaCom100DeSaldo25Selic_devolve75DeSaldo() {
		// Preparação dos dados
		Conta c = new ContaPoupanca(100.00f);
		double selic = 25.00f;
		AtualizadorDeContas atualizador = new AtualizadorDeContas(selic);
		
		// Roteiro
		atualizador.roda(c);
		
		// Verificação
		assertEquals(75.0f, c.getSaldo(), 0);
	}
	
	@Test
	public void getSelic_AtualizadorDeContasCom25DeSelic_devolve25DeSelic() {
		// Preparação dos dados
		double selic = 25.00f;
		AtualizadorDeContas atualizador = new AtualizadorDeContas(selic);
		
		// Roteiro
		double selicRetornada = atualizador.getSelic();
		
		// Verificação
		assertEquals(selic, selicRetornada, 0);
	}

}
