package la.foton.treinamento.entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ContaPoupancaTest {
	
	private ContaPoupanca contaPoupanca;
	
	@Before
	public void setUp() {
		contaPoupanca = new ContaPoupanca();
		contaPoupanca.credita(200.00);
	}
	

	@Test
	public void deveDebitarValorNaContaPoupancaComSaldo() {
		try {
			contaPoupanca.debita(199.99);
			
			Assert.assertEquals(0.01,  contaPoupanca.getSaldo(), 0.001);
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	@Test
	public void NaoDeveDebitarValorNaContaPoupancaComSaldo() {
		try {
			contaPoupanca.debita(200.01);
			
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Saldo insuficiente", e.getMessage());
		}
	}
}
