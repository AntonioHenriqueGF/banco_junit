package br.com.cefet.banco.negocio;

import static org.junit.Assert.*;

import org.junit.Test;

public class BancoTest {

	@Test
	public void testCalcularLimiteMaximo_saldoNegativoLimite100_retornaLimiteMaximo100() {
		Banco banco = new Banco();
		Conta conta = new ContaCorrente(100.00f); 
		double saldo = -100.0f; 
		double limite = 100.0f; 
		double gastoTotalBanco = 0.0f; 
		double saldoTotalBanco = 0.0f;
		int numeroClientes = 0;
		int numeroFuncionarios = 0;
		
		double limiteMaximo = banco.calcularLimiteMaximo(conta, saldo, limite, gastoTotalBanco, saldoTotalBanco, numeroClientes, numeroFuncionarios);
		
		assertEquals(limite, limiteMaximo, 0);
	}

	@Test
	public void testCalcularLimiteMaximo_tudoZero_retornaLimMax100() {
		Banco banco = new Banco();
		Conta conta = new ContaCorrente(100.00f); 
		double saldo = 0.0f; 
		double limite = 0.0f; 
		double gastoTotalBanco = 0.0f; 
		double saldoTotalBanco = 0.0f;
		int numeroClientes = 0;
		int numeroFuncionarios = 0;
		
		double limiteMaximo = banco.calcularLimiteMaximo(conta, saldo, limite, gastoTotalBanco, saldoTotalBanco, numeroClientes, numeroFuncionarios);
		
		assertEquals(100.0f, limiteMaximo, 0);
	}

	@Test
	public void testCalcularLimiteMaximo_Lim1000GastoCliSaldoMaior_retornaSaldoTotalBanco() {
		Banco banco = new Banco();
		Conta conta = new ContaCorrente(100.00f); 
		double saldo = 0.0f; 
		double limite = 1000.0f; 
		double gastoTotalBanco = 100.0f; 
		double saldoTotalBanco = 110.0f;
		int numeroClientes = 5;
		int numeroFuncionarios = 11;
		
		double limiteMaximo = banco.calcularLimiteMaximo(conta, saldo, limite, gastoTotalBanco, saldoTotalBanco, numeroClientes, numeroFuncionarios);
		
		assertEquals(saldoTotalBanco, limiteMaximo, 0);
	}

	@Test
	public void testCalcularLimiteMaximo_saldoMaiorQue10SaldoTotPorCli_retorna100LimMax() {
		Banco banco = new Banco();
		Conta conta = new ContaCorrente(100.00f); 
		double saldo = 10000.0f; 
		double limite = 10.0f; 
		double gastoTotalBanco = 100.0f; 
		double saldoTotalBanco = 110.0f;
		int numeroClientes = 5;
		int numeroFuncionarios = 11;
		
		double limiteMaximo = banco.calcularLimiteMaximo(conta, saldo, limite, gastoTotalBanco, saldoTotalBanco, numeroClientes, numeroFuncionarios);
		
		assertEquals(100.00f, limiteMaximo, 0);
	}

	@Test
	public void testCalcularLimiteMaximo_saldoMaiorQue5SaldoTotPorCli_retorna50LimMax() {
		Banco banco = new Banco();
		Conta conta = new ContaCorrente(100.00f); 
		double saldo = 120.0f; 
		double limite = 10.0f; 
		double gastoTotalBanco = 100.0f; 
		double saldoTotalBanco = 110.0f;
		int numeroClientes = 5;
		int numeroFuncionarios = 11;
		
		double limiteMaximo = banco.calcularLimiteMaximo(conta, saldo, limite, gastoTotalBanco, saldoTotalBanco, numeroClientes, numeroFuncionarios);
		
		assertEquals(50.00f, limiteMaximo, 0);
	}

	@Test
	public void testCalcularLimiteMaximo_saldoMaiorQueSaldoTotPorCli_retorna20LimMax() {
		Banco banco = new Banco();
		Conta conta = new ContaCorrente(100.00f); 
		double saldo = 100.0f; 
		double limite = 10.0f; 
		double gastoTotalBanco = 100.0f; 
		double saldoTotalBanco = 110.0f;
		int numeroClientes = 5;
		int numeroFuncionarios = 11;
		
		double limiteMaximo = banco.calcularLimiteMaximo(conta, saldo, limite, gastoTotalBanco, saldoTotalBanco, numeroClientes, numeroFuncionarios);
		
		assertEquals(20.00f, limiteMaximo, 0);
	}

	
	@Test
	public void testCalcularLimiteMaximo_saldoMenorQueSaldoTotPorCli_retorna11LimMax() {
		Banco banco = new Banco();
		Conta conta = new ContaCorrente(100.00f); 
		double saldo = 1.0f; 
		double limite = 10.0f; 
		double gastoTotalBanco = 100.0f; 
		double saldoTotalBanco = 110.0f;
		int numeroClientes = 5;
		int numeroFuncionarios = 11;
		
		double limiteMaximo = banco.calcularLimiteMaximo(conta, saldo, limite, gastoTotalBanco, saldoTotalBanco, numeroClientes, numeroFuncionarios);
		
		assertEquals(11.00f, limiteMaximo, 0);
	}

	@Test
	public void testCalcularLimiteMaximo_limiteMaisSaldoMaiorQue2Limites_retorna20LimMax() {
		Banco banco = new Banco();
		Conta conta = new ContaCorrente(100.00f); 
		double saldo = 11.0f; 
		double limite = 10.0f; 
		double gastoTotalBanco = 100.0f; 
		double saldoTotalBanco = 110.0f;
		int numeroClientes = 5;
		int numeroFuncionarios = 11;
		
		double limiteMaximo = banco.calcularLimiteMaximo(conta, saldo, limite, gastoTotalBanco, saldoTotalBanco, numeroClientes, numeroFuncionarios);
		
		assertEquals(20.00f, limiteMaximo, 0);
	}

}
