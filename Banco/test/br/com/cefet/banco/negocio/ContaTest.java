package br.com.cefet.banco.negocio;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.cefet.banco.exceptions.DepositoInvalidoException;
import br.com.cefet.banco.exceptions.SaldoInsuficienteException;

public class ContaTest {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalErr = System.err;
	
	@Mock
	private Cliente titular;
	
	@Before
	public void setUp() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		MockitoAnnotations.initMocks(this);
	}
	
	@After
	public void tearDown() {
		System.setOut(originalOut);
		System.setOut(originalErr);
	}

	@Test
	public void testDepositar_100deSaldoDeposita50_retorna150Saldo() throws DepositoInvalidoException {
		double saldoInicial = 100.00f;
		double deposito = 50.00f;
		Conta c = new ContaCorrente(saldoInicial);
		
		c.depositar(deposito);
		
		assertEquals(saldoInicial + deposito, c.getSaldo(), 0);
	}

	@Test
	public void testDepositar_100deSaldoDepositaMenos10_retornaErroAoSepositar() throws DepositoInvalidoException {
		double saldoInicial = 100.00f;
		double deposito = -10.00f;
		Conta c = new ContaCorrente(saldoInicial);
		
		try {
			c.depositar(deposito);
			fail();
		} catch (DepositoInvalidoException e) {
			assertEquals("Erro ao depositar", e.getMessage());
		}
	}

	@Test
	public void testSacar_100deSaldoSaca10_retorna90DeSaldo() throws SaldoInsuficienteException {
		double saldoInicial = 100.00f;
		double saque = 10.00f;
		Conta c = new ContaCorrente(saldoInicial, 0);
		
		c.sacar(saque);
		
		assertEquals(saldoInicial - saque, c.getSaldo(), 0);
	}

	@Test
	public void testSacar_100deSaldoSacar110_retornaSaldoInsuficiente() throws SaldoInsuficienteException {
		double saldoInicial = 100.00f;
		double saque = 110.00f;
		Conta c = new ContaCorrente(saldoInicial, 0);
		
		try {
			c.sacar(saque);
			fail();
		} catch (SaldoInsuficienteException e) {
			double saquePermitido = c.getSaldo() + c.getLimite();
			assertEquals("Saldo insuficiente. Voc� s� pode sacar " + saquePermitido, e.getMessage());
		}
	}
	
	@Test
	public void testTransferir_transferir10de100SaldoPara100Saldo_restorna90Saldo110Saldo() {
		double saldoInicial = 100.00f;
		double transferencia = 10.00f;
		Conta c = new ContaCorrente(saldoInicial);
		Conta c2 = new ContaCorrente(saldoInicial);
		
		c.transferir(c2, transferencia);
		
		assertEquals("90.0 110.0", c.getSaldo() + " " + c2.getSaldo());
	}
	
	@Test
	public void testTransferir_transferir110de100SaldoPara100Saldo_restorna90Saldo110Saldo() {
		double saldoInicial = 100.00f;
		double transferencia = 110.00f;
		Conta c = new ContaCorrente(saldoInicial);
		Conta c2 = new ContaCorrente(saldoInicial);
		
		try {
			c.transferir(c2, transferencia);
			fail();
		} catch (Exception e) {
			double saquePermitido = c.getSaldo() + c.getLimite();
			assertEquals("Saldo insuficiente. Voc� s� pode sacar " + saquePermitido, e.getMessage());
		}
		
		
		assertEquals("90.0 110.0", c.getSaldo() + " " + c2.getSaldo());
	}
	
	@Test
	public void testTransferir_transferirMenos10de100SaldoPara100Saldo_restorna90Saldo110Saldo() {
		double saldoInicial = 100.00f;
		double transferencia = -10.00f;
		Conta c = new ContaCorrente(saldoInicial);
		Conta c2 = new ContaCorrente(saldoInicial);
		
		
		try {
			c.transferir(c2, transferencia);
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testCompareTo_100SaldoCom100Saldo_retornaContasIguais0 () {
		double saldoInicial = 100.00f;
		Conta c = new ContaCorrente(saldoInicial);
		Conta c2 = new ContaCorrente(saldoInicial);
		
		int codigo = c.compareTo(c2);
		
		assertEquals(0, codigo);
	}
	
	@Test
	public void testCompareTo_100SaldoCom110Saldo_retornaContasDiferentes0 () {
		double saldoInicial = 100.00f;
		Conta c = new ContaCorrente(saldoInicial);
		Conta c2 = new ContaCorrente(saldoInicial + 10.00f);
		
		int codigo = c.compareTo(c2);
		
		assertEquals(-1, codigo);
	}
	
	@Test
	public void testCompareTo_110SaldoCom100Saldo_retornaContasDiferentes0 () {
		double saldoInicial = 100.00f;
		Conta c = new ContaCorrente(saldoInicial + 10.00f);
		Conta c2 = new ContaCorrente(saldoInicial);
		
		int codigo = c.compareTo(c2);
		
		assertEquals(-1, codigo);
	}
	
	@Test
	public void testGetTipoString_contaCorrente0_retornaContaCorrente () {
		double saldoInicial = 100.00f;
		Conta c = new ContaCorrente(saldoInicial);
		
		String tipo = c.getTipoStr();
		
		assertEquals("Conta corrente", tipo);
	}
	
	@Test
	public void testGetTipoString_contaPoupanca1_retornaContaPoupanca () {
		double saldoInicial = 100.00f;
		Conta c = new ContaPoupanca(saldoInicial);
		
		String tipo = c.getTipoStr();
		
		assertEquals("Conta poupan�a", tipo);
	}
	
	@Test
	public void testImprimirResumo_titularA100Saldo_PrintaMensagemSemErro() {
		double saldoInicial = 100.00f;
		String nomeTitular = "A";
		Conta c = new ContaCorrente(saldoInicial);
		
		Mockito.when(titular.getNome()).thenReturn(nomeTitular);
		c.setTitular(titular);
		
		c.imprimirResumo();

		assertTrue(errContent.toString().contains("Corrente"));
		assertTrue(outContent.toString().contains(nomeTitular));
		assertTrue(outContent.toString().contains(String.valueOf(saldoInicial)));

	}

}
