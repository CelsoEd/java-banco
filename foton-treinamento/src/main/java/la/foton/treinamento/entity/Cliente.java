package la.foton.treinamento.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Cliente {
	@Id
	private String cpf;
	
	@Column
	private String nome;
	
	@Column
	@Enumerated(EnumType.ORDINAL)
	private SituacaoDoCliente situacao;
	
	
	public Cliente() {
		this.situacao = SituacaoDoCliente.PENDENTE;
	}
	
	public SituacaoDoCliente getSituacao() {
		return situacao;
	}
	
	public void ativa() {
		this.situacao = SituacaoDoCliente.ATIVO;
	}

	public Cliente(String cpf, String nome) {
		this.cpf = cpf;
		this.nome = nome;		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
