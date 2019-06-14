package la.foton.treinamento.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import la.foton.treinamento.dao.ContaDAO;
import la.foton.treinamento.entity.Cliente;
import la.foton.treinamento.entity.Conta;
import la.foton.treinamento.entity.ContaCorrente;
import la.foton.treinamento.entity.ContaPoupanca;
import la.foton.treinamento.entity.TipoDaConta;
import la.foton.treinamento.util.Mensagem;
import la.foton.treinamento.util.NegocioException;

@Stateless
public class ContaService {


	@Inject
	private ContaDAO dao;
	
	@Inject 
	ClienteService clienteService;

	public Conta abreConta(Cliente titular, TipoDaConta tipo) throws NegocioException {
		titular.ativa();
		clienteService.validaSituacaoCliente(titular);
		Conta conta = criaConta(tipo);
		conta.setTitular(titular);
		conta.setAgencia(1234);
		int numero = dao.geraNumero();
		conta.setNumero(numero);
		dao.insere(conta);

		return conta;
	}

	public void encerraConta(Conta conta) throws NegocioException {
		conta.encerra();
		dao.atualiza(conta);
	}

	public Conta consultaPorNumero(int numero) throws NegocioException {
		Conta conta = dao.consultaPorNumero(numero);

		if (conta == null) {
			throw new NegocioException(Mensagem.CONTA_NAO_ENCONTRADA);
		}
		return conta;
	}


	//Metodo para saque em conta passando o numero da conta de origem e o valor

	public Conta sacaEmConta(int numeroConta, double valor) throws NegocioException  {
		Conta conta= null;
		conta = dao.consultaPorNumero(numeroConta);
		conta.debita(valor);				
		return conta;
	}
	
	public Conta depositaEmConta(int numeroConta, double valor) {
		Conta conta=null;
		conta = dao.consultaPorNumero(numeroConta);
		conta.credita(valor);		
		return conta;
	}

	public void transfereEntreContas(int numContaDeDebito,int numContaDeCredito, double valor) throws NegocioException {
		Conta contaDeCredito= null; 
		Conta contaDeDebito = null;
		contaDeDebito = dao.consultaPorNumero(numContaDeDebito);
		contaDeCredito= dao.consultaPorNumero(numContaDeCredito);
		contaDeDebito.debita(valor);
		contaDeCredito.credita(valor);
	}

	private Conta criaConta(TipoDaConta tipo) {
		Conta conta = null;
		if (TipoDaConta.CORRENTE.equals(tipo)) {
			conta = new ContaCorrente();
			((ContaCorrente) conta).setLimiteChequeEspecial(500.00);

		} else if (TipoDaConta.POUPANCA.equals(tipo)) {
			conta = new ContaPoupanca();
			((ContaPoupanca) conta).setDiaAniversario(1);
		}
		return conta;
	}

}
