package la.foton.treinamento.entity;

import la.foton.treinamento.util.Mensagem;
import la.foton.treinamento.util.NegocioException;

public class ContaPoupanca extends Conta {
	private int diaAniversario;
	
	public ContaPoupanca() {
		this.tipo = TipoDaConta.POUPANCA;
	}

	@Override
	public void debita(double valor) throws NegocioException {
		if(this.saldo < valor) {
			throw new NegocioException(Mensagem.SALDO_INSUFICIENTE);
		}
		this.saldo -= valor;
	}

	public int getDiaAniversario() {
		return diaAniversario;
	}

	public void setDiaAniversario(int diaAniversario) {
		this.diaAniversario = diaAniversario;
	}
}
