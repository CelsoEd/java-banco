package la.foton.treinamento.service;

import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;

import la.foton.treinamento.entity.Cliente;
import la.foton.treinamento.util.Mensagem;
import la.foton.treinamento.util.NegocioException;

public class ClienteServiceTest {
	
	private Cliente cliente;
	private ClienteService service;
	
	@Before
	public void setUp() {
		cliente = new Cliente();
	}	
	
	@Test
	public void deveAbrirUmaContaPoupanca() {
		try {
			service.validaSituacaoCliente(cliente);
			
		} catch (NegocioException e) {
			assertEquals(Mensagem.SITUACAO_CLIENTE_PEDENTE, e.getMessage());
		}
	}

}
