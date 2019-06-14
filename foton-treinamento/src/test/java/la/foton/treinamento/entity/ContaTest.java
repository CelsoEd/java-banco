package la.foton.treinamento.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class ContaTest {
	
	private Conta conta;
	
	@Before
	public void setUp() {
		conta = new ContaCorrente();
		conta.credita(500.0);
	}
	
	@Test
	public void deveCreditarValorNaConta() {
		
		conta.credita(100);
		
		assertEquals(600.0, conta.getSaldo(), 0.0);
	}
	
	@Test
	public void deveDebitarValorNaContaQuePossiSaldoInsuficiente() {
		
		try {
			conta.debita(251.0);
			
			assertEquals(249.0, conta.getSaldo(), 0.0);
		} catch (Exception e) {
			fail();
		}	
	}
	
	
	@Test
	public void naoDeveDebitarValorNaContaQuePossiSaldoInsuficiente() {
		
		try {
			conta.debita(500.1);
			
			fail();
		} catch (Exception e) {
			assertEquals("Saldo insuficiente", e.getMessage());	
			
			assertEquals(500.00, conta.getSaldo(), 0.0);
		}	
	}
	
	@Test
	public void deveTransferirValorEntreContas() {
		
		Conta contaDeCredito = new ContaPoupanca();
		
		try {
			conta.transfere(contaDeCredito, 499.0);		
			assertEquals(1.0, conta.getSaldo(), 0.0);
			assertEquals(499.0, contaDeCredito.getSaldo(), 0.0);			
		}catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void deveEnncerrarContaSemSaldo() {
		try {
			conta.debita(500.0);
			conta.encerra();
			
			assertEquals(EstadoDaConta.ENCERRADA, conta.getEstado());
		} catch (Exception e) {
			fail();
		}
		
	}
	
	@Test
	public void naoDeveEnncerrarContaJaEncerrada() {
		
		try {
			conta.encerrada();;
			conta.encerra();;
			fail();
		} catch (Exception e) {
			assertEquals("Conta já encerrada", e.getMessage());
		}
	}
	
	@Test
	public void naoDeveSerEncerradaSeElaPossuiSaldo() {
		try {
			conta.encerra();
			fail();
		} catch (Exception e) {
			assertEquals("Conta possui saldo", e.getMessage());
		}
	}

}
