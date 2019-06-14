package la.foton.treinamento.util;

public enum Mensagem {
	
	SALDO_INSUFICIENTE("Saldo insuficiente"),
	SITUACAO_CLIENTE_PEDENTE("Cliente com situacao pendente"),
	CONTA_JA_ENCERRADA("Conta já encerrada"), 
	CONTA_NAO_PODE_SER_ENCERRADA("A Conta não pode ser encerrada"), 
	CONTA_NAO_ENCONTRADA("Conta não encontrada"), 
	CLIENTE_NAO_ENCONTRADO("Cliente não encontrado"), 
	NAO_EXISTEM_CLIENTES("Nao existem clientes cadastrado"), 
	CLIENTE_NAO_PODE_SER_CADASTRADOS("Cliente nao pode ser cadastrado");
	
	private String descricao;
	
	private Mensagem(String descricao) {		
		this.descricao = descricao;
	}
	

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}

