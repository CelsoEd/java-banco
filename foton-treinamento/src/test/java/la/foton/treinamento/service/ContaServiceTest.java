package la.foton.treinamento.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import la.foton.treinamento.dao.ContaDAO;
import la.foton.treinamento.entity.Cliente;
import la.foton.treinamento.entity.Conta;
import la.foton.treinamento.entity.ContaCorrente;
import la.foton.treinamento.entity.ContaPoupanca;
import la.foton.treinamento.entity.TipoDaConta;
import la.foton.treinamento.util.NegocioException;

public class ContaServiceTest {

	private Cliente titular;
	private ContaDAO dao;
	private ContaService service;

	@Before
	public void setUp() {
		titular = new Cliente();
	}

	@Test
	public void deveAbrirContaCorrente() {
		try {
			Conta conta = service.abreConta(titular, TipoDaConta.CORRENTE);

			Conta contaConsultada = dao.consultaPorNumero(conta.getNumero());

			assertNotNull(contaConsultada);
			assertEquals(conta.getSaldo(), contaConsultada.getSaldo(), 0.0);
			assertEquals(500.00, ((ContaCorrente) conta).getLimiteChequeEspecial(), 0.0);
		} catch (NegocioException e) {
			fail();
		}
	}

	@Test
	public void deveAbrirUmaContaPoupanca() {
		try {
			Conta conta = service.abreConta(titular, TipoDaConta.POUPANCA);
			assertEquals(1, ((ContaPoupanca) conta).getDiaAniversario());
		} catch (NegocioException e) {
			fail();
		}
	}

}
