package la.foton.treinamento.entity;

import la.foton.treinamento.util.Mensagem;
import la.foton.treinamento.util.NegocioException;

public class ContaCorrente extends Conta {
	private double limiteChequeEspecial;
	
	public ContaCorrente() {
		this.tipo = TipoDaConta.CORRENTE;
	}

	@Override
	public void debita(double valor) throws NegocioException {
		if(valor > this.saldo + getLimiteChequeEspecial()) {
			throw new NegocioException(Mensagem.SALDO_INSUFICIENTE);
		}
		this.saldo -= valor;		
	}


	public double getLimiteChequeEspecial() {
		return limiteChequeEspecial;
	}

	public void setLimiteChequeEspecial(double limiteChequeEspecial) {
		this.limiteChequeEspecial = limiteChequeEspecial;
	}


	
}
