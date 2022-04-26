package br.com.residencia.contas;

public class ContaPoupanca extends Conta {

	private Integer idContaPoupanša;
	private Double rendimento;

	public ContaPoupanca(Integer idContaPoupanša, Double rendimento) {
		super();
		this.idContaPoupanša = idContaPoupanša;
		this.rendimento = rendimento;
	}

	public ContaPoupanca() {

	}

	public Integer getIdContaPoupanša() {
		return idContaPoupanša;
	}

	public Double getRendimento() {
		return rendimento;
	}

	public void setRendimento(Double rendimento) {
		this.rendimento = rendimento;
	}

}
