package la.foton.treinamento.entity;

import la.foton.treinamento.util.Mensagem;
import la.foton.treinamento.util.NegocioException;

public abstract class Conta {
	private int agencia;
	private int numero;
	protected double saldo;	
	private Cliente titular;	
	private EstadoDaConta estado;	
	protected TipoDaConta tipo;
	
	public Conta() {
		this.estado = EstadoDaConta.ATIVA;
		this.saldo = 0.0;
	}
	
	public Conta(EstadoDaConta estado) {
		this.estado = EstadoDaConta.ENCERRADA;
	}
	
	public void credita(double valor) {
		this.saldo += valor;
	}
	
	public abstract void debita(double valor) throws NegocioException;
	
	public void transfere(Conta contaDeCredito, double valor) throws Exception {
		this.debita(valor);
		
		contaDeCredito.credita(valor);
	}
	
		
	public void encerra() throws NegocioException {
		//Só pode ser encerrada se não tiver saldo		
		//Uma conta só pode ser encerrada se ela estiver ativa
		if (EstadoDaConta.ENCERRADA.equals(estado)) {
			throw new NegocioException(Mensagem.CONTA_JA_ENCERRADA);
		}
		
		if(this.saldo > 0) {
			throw new NegocioException(Mensagem.CONTA_NAO_PODE_SER_ENCERRADA);
		}
		estado = EstadoDaConta.ENCERRADA;
		
	}
	
	public void encerrada() {
		estado = EstadoDaConta.ENCERRADA;
	}

	public double getSaldo() {
		return saldo;
	}

	public EstadoDaConta getEstado() {
		return estado;
	}

	public int getAgencia() {
		return agencia;
	}

	
	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}

	public int getNumero() {
		return numero;
	}

	
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Cliente getTitular() {
		return titular;
	}

	public void setTitular(Cliente titular) {
		this.titular = titular;
	}

	

}
