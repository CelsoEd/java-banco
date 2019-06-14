package la.foton.treinamento.dao;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import la.foton.treinamento.entity.Cliente;
import la.foton.treinamento.entity.Conta;
import la.foton.treinamento.entity.ContaCorrente;
import la.foton.treinamento.entity.ContaPoupanca;

@Singleton
@Startup
public class ContaDAOMap implements ContaDAO {
	
	private Map<Integer, Conta> contas = new HashMap<>();	
	
	@PostConstruct
	public void init(){
		contas = new HashMap<>();
		
		ContaCorrente conta = new ContaCorrente();
		conta.setAgencia(1234);
		conta.setNumero(1);
		conta.credita(17.01);
		
		conta.setTitular(new Cliente("45646546","Flavio"));
		
		ContaPoupanca conta2 = new ContaPoupanca();
		conta2.setAgencia(4321);
		conta2.setNumero(2);
		conta2.credita(107.01);
		
		conta2.setTitular(new Cliente("65498731","Pedro"));
		
		contas.put(1, conta);
		contas.put(2, conta2);
	}
	
	@Override
	public int geraNumero() {
		return contas.size() +1 ;
	}

	@Override
	public void insere(Conta conta) {	
		contas.put(geraNumero(), conta);
	}

	@Override
	public Conta consultaPorNumero(int numero) {
		if (contas.containsKey(numero)) {
			return contas.get(numero);
		}
		return null;
	}

	@Override
	public void atualiza(Conta conta) {
		contas.put(conta.getNumero(), conta);
	}

}
