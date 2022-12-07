package br.com.cefet.banco.negocio;

import static org.junit.Assert.*;

import org.junit.Test;

public class ContaCorrenteTest {

	@Test
	public void testAtualiza_100deSaldo10DeTaxa_retorna80DeSaldo() {
		double saldoInicial = 100.00f;
		double taxa = 10.00f;
		Conta c = new ContaCorrente(saldoInicial);
		
		c.atualiza(taxa);
		
		assertEquals(80.00f, c.getSaldo(), 0);
	}
	
	@Test
	public void testContaCorrente_cira100Saldo_retorna100Saldo() {
		double saldo = 100.00f;
		
		Conta c = new ContaCorrente(saldo);
		
		assertEquals(saldo, c.getSaldo(), 0);
	}
	
	@Test
	public void testContaCorrenteDoubleDouble_cira100Saldo_retorna100Saldo() {
		double saldo = 100.00f;
		double limite = 0;
		
		Conta c = new ContaCorrente(saldo, limite);
		
		assertEquals(saldo, c.getSaldo(), 0);
	}

}
