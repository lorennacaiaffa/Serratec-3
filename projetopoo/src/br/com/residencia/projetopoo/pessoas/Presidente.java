package br.com.residencia.projetopoo.pessoas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.com.residencia.agencias.Agencia;

public class Presidente extends Funcionario {

	private Integer idPresidente;
	List<Agencia> agencia = new ArrayList<>();
	public Presidente(String nome, String genero, String estadoCivil, String cpf, String telefone, String email,
			Date dataNascimento, String tipoPessoa, Integer idEndereco, Integer idFuncionario, String senha,
			String login, Double salario, String cargo, Integer idPresidente, List<Agencia> agencias) {
		super(nome, genero, estadoCivil, cpf, telefone, email, dataNascimento, tipoPessoa, idEndereco, idFuncionario,
				senha, salario, cargo);
		this.idPresidente = idPresidente;
		this.agencia = agencia;
	}

	public Presidente() {
		
	}

	public Integer getIdPresidente() {
		return idPresidente;
	}

	public List<Agencia> getAgencia() {
		return agencia;
	}

	public void setAgencia(List<Agencia> agencia) {
		this.agencia = agencia;
	}
	
	
}
