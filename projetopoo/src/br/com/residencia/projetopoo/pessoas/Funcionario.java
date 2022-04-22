package br.com.residencia.projetopoo.pessoas;

import java.util.Date;

public class Funcionario extends Pessoa {

	private Integer idFuncionario;
	private String senha;
	private String login;
	private Double salario;
	private String cargo;

	public Funcionario(String nome, String genero, String estadoCivil, String cpf, String telefone, String endereco,
			String email, Date dataNascimento, String tipoPessoa, Integer idFuncionario, String senha, String login,
			Double salario, String cargo) {
		super(nome, genero, estadoCivil, cpf, telefone, endereco, email, dataNascimento, tipoPessoa);
		this.idFuncionario = idFuncionario;
		this.senha = senha;
		this.login = login;
		this.salario = salario;
		this.cargo = cargo;

	}

	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

}
