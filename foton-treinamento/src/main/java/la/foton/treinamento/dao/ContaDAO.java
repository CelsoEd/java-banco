package la.foton.treinamento.dao;

import la.foton.treinamento.entity.Conta;

public interface ContaDAO {
	
	public int geraNumero();
	public void insere(Conta conta);
	public Conta consultaPorNumero(int numero);
	public void atualiza(Conta conta);

}
