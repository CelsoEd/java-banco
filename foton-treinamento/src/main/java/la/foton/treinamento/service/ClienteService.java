package la.foton.treinamento.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import la.foton.treinamento.dao.ClienteDAO;
import la.foton.treinamento.entity.Cliente;
import la.foton.treinamento.entity.SituacaoDoCliente;
import la.foton.treinamento.util.Mensagem;
import la.foton.treinamento.util.NegocioException;

@Stateless
public class ClienteService {

	@Inject
	private ClienteDAO dao;

	public void validaSituacaoCliente(Cliente cliente) throws NegocioException {
		if (cliente.getSituacao() == SituacaoDoCliente.PENDENTE) {
			throw new NegocioException(Mensagem.SITUACAO_CLIENTE_PEDENTE);
		}
	}

	public Cliente consultaPorCPF(String cpf) throws NegocioException {
		Cliente cliente = dao.consultaPorCPF(cpf);
		if (cliente == null) {
			throw new NegocioException(Mensagem.CONTA_NAO_ENCONTRADA);
		}
		return cliente;
	}

	public List<Cliente> listaTodos() throws NegocioException {
		List<Cliente> clientes = dao.consultaTodos();
		if (clientes.isEmpty())
			throw new NegocioException(Mensagem.NAO_EXISTEM_CLIENTES);
		return clientes;
	}

	public Cliente cadastraCliente(String cpf, String nome) throws NegocioException {
		Cliente cliente = new Cliente(cpf, nome);
		if (cpf == null || cpf.isEmpty() || nome == null || nome.isEmpty()) {
			throw new NegocioException(Mensagem.CLIENTE_NAO_PODE_SER_CADASTRADOS);
		}
		dao.insere(cliente);
		return cliente;
	}
}
