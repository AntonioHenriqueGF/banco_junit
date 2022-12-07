package br.com.cefet.banco.negocio;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.cefet.banco.exceptions.DepositoInvalidoException;

public class ContaPoupancaTest {

	@Test
	public void testDepositar_100deSaldoDeposita50_retorna150Saldo() throws DepositoInvalidoException {
		double saldoInicial = 100.00f;
		double deposito = 50.00f;
		Conta c = new ContaPoupanca(saldoInicial);
		
		c.depositar(deposito);
		
		assertEquals(saldoInicial + deposito, c.getSaldo(), 0);
	}

	@Test
	public void testAtualiza_100deSaldo10DeTaxa_retorna80DeSaldo() {
		double saldoInicial = 100.00f;
		double taxa = 10.00f;
		Conta c = new ContaPoupanca(saldoInicial);
		
		c.atualiza(taxa);
		
		assertEquals(90.00f, c.getSaldo(), 0);
	}
	
	@Test
	public void testContaCorrente_cira100Saldo_retorna100Saldo() {
		double saldo = 100.00f;
		
		Conta c = new ContaPoupanca(saldo);
		
		assertEquals(saldo, c.getSaldo(), 0);
	}
	
	@Test
	public void testContaCorrenteDoubleDouble_cira100Saldo_retorna100Saldo() {
		double saldo = 100.00f;
		double limite = 0;
		
		Conta c = new ContaPoupanca(saldo, limite);
		
		assertEquals(saldo, c.getSaldo(), 0);
	}

}
