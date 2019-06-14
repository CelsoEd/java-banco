package la.foton.treinamento.dao;

import java.util.List;

import la.foton.treinamento.entity.Cliente;

public interface ClienteDAO {
	
	public void insere(Cliente cliente);
	
	public Cliente consultaPorCPF(String cpf);
	
	public List<Cliente> consultaTodos();

}
