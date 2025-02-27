package br.com.residencia.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import br.com.residencia.agencias.Agencia;
import br.com.residencia.contas.Conta;
import br.com.residencia.contas.ContaCorrente;
import br.com.residencia.contas.ContaPoupanca;
import br.com.residencia.enderecos.Endereco;
import br.com.residencia.enums.TipoConta;
import br.com.residencia.enums.TipoUsuario;
import br.com.residencia.pessoas.Cliente;
import br.com.residencia.pessoas.Diretor;
import br.com.residencia.pessoas.Funcionario;
import br.com.residencia.pessoas.Gerente;
import br.com.residencia.pessoas.Presidente;

public class LeituraEscrita {
	final static String PATH_BASICO = "./temp/";
	final static String EXTENSAO = ".txt";

	static List<Cliente> listaClientes = new ArrayList<Cliente>();
	static List<Conta> listaContas = new ArrayList<Conta>();
	static List<Agencia> listaAgencias = new ArrayList<Agencia>();
	static List<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();
	static List<Gerente> listaGerentes = new ArrayList<Gerente>();

	public static void escritor(String path) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Escreva o nome do arquivo: ");
		String nome = sc.nextLine();
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(PATH_BASICO + nome));
		System.out.println("Escreva algo: ");
		while (true) {
			String linha = sc.nextLine();
			if (linha.equals("/fim"))
				break;
			buffWrite.append(linha);
			buffWrite.newLine();
		}

		buffWrite.close();
		sc.close();
	}

	public static void leitor(String path) throws IOException {

		BufferedReader buffRead = new BufferedReader(new FileReader(PATH_BASICO + path));
		String linha = "";


		while (true) {
			linha = buffRead.readLine();
			if (linha != null) {
				String[] vetor = linha.split(";");

				if (vetor[0].equalsIgnoreCase(TipoConta.CORRENTE.getTipoConta())) {
					ContaCorrente contaCorrentes = new ContaCorrente(TipoConta.CORRENTE, Integer.parseInt(vetor[1]),
							vetor[2], vetor[3], vetor[4], Double.parseDouble(vetor[5]), vetor[6],
							Boolean.parseBoolean(vetor[7]), vetor[8], Integer.parseInt(vetor[9]),
							Double.parseDouble(vetor[10]), Double.parseDouble(vetor[11]));
					Conta.mapaContas.put(vetor[8], contaCorrentes);
					listaContas.add(contaCorrentes);

				} else if (vetor[0].equalsIgnoreCase(TipoConta.POUPANCA.getTipoConta())) {
					ContaPoupanca contaPoupancas = new ContaPoupanca(TipoConta.POUPANCA, Integer.parseInt(vetor[1]),
							vetor[2], vetor[3], vetor[4], Double.parseDouble(vetor[5]), vetor[6],
							Boolean.parseBoolean(vetor[7]), vetor[8], Integer.parseInt(vetor[9]),
							Double.parseDouble(vetor[10]));
					Conta.mapaContas.put(vetor[8], contaPoupancas);
					listaContas.add(contaPoupancas);

				} else if (vetor[0].equalsIgnoreCase(TipoUsuario.CLIENTE.getTipoUsuario())) {
					Cliente clientes = new Cliente(TipoUsuario.CLIENTE, vetor[1], vetor[2], vetor[3], vetor[4],
							vetor[5], vetor[6], LocalDate.parse(vetor[7]), Integer.parseInt(vetor[8]),
							Integer.parseInt(vetor[9]), Integer.parseInt(vetor[10]), Integer.parseInt(vetor[11]),
							vetor[12]);
					Cliente.mapaClientes.put(vetor[4], clientes);
					Cliente.mapaClientes.put(vetor[8], clientes);
					Cliente.OrdenaClientes.put(vetor[1], clientes);

					listaClientes.add(clientes);

				} else if (vetor[0].equalsIgnoreCase(TipoUsuario.GERENTE.getTipoUsuario())) {
					Gerente gerentes = new Gerente(TipoUsuario.GERENTE, vetor[1], vetor[2], vetor[3], vetor[4],
							vetor[5], vetor[6], LocalDate.parse(vetor[7]), Integer.parseInt(vetor[8]),
							Integer.parseInt(vetor[9]), vetor[10], Double.parseDouble(vetor[11]),
							Integer.parseInt(vetor[12]), Integer.parseInt(vetor[13]));
					Gerente.mapaGerentes.put(vetor[4], gerentes);
					Gerente.OrdenaGerentes.put(vetor[1], gerentes);
					listaFuncionarios.add(gerentes);
					listaGerentes.add(gerentes);

				} else if (vetor[0].equalsIgnoreCase(TipoUsuario.DIRETOR.getTipoUsuario())) {
					Diretor diretores = new Diretor(TipoUsuario.DIRETOR, vetor[1], vetor[2], vetor[3], vetor[4],
							vetor[5], vetor[6], LocalDate.parse(vetor[7]), Integer.parseInt(vetor[8]),
							Integer.parseInt(vetor[9]), vetor[10], Double.parseDouble(vetor[11]),
							Integer.parseInt(vetor[12]), Integer.parseInt(vetor[13]));
					Diretor.mapaDiretores.put(vetor[4], diretores);
					Diretor.OrdenaDiretores.put(vetor[1], diretores);
					listaFuncionarios.add(diretores);

				} else if (vetor[0].equalsIgnoreCase(TipoUsuario.PRESIDENTE.getTipoUsuario())) {
					Presidente presidentes = new Presidente(TipoUsuario.PRESIDENTE, vetor[1], vetor[2], vetor[3],
							vetor[4], vetor[5], vetor[6], LocalDate.parse(vetor[7]), Integer.parseInt(vetor[8]),
							Integer.parseInt(vetor[9]), vetor[10], Double.parseDouble(vetor[11]),
							Integer.parseInt(vetor[12]));
					Presidente.mapaPresidentes.put(vetor[4], presidentes);
					Presidente.OrdenaPresidentes.put(vetor[1], presidentes);
					listaFuncionarios.add(presidentes);
					
				} else if (vetor[0].equalsIgnoreCase("Endereco")) {
					Endereco enderecos = new Endereco(Integer.parseInt(vetor[1]), vetor[2], vetor[3],
							Integer.parseInt(vetor[4]), vetor[5], vetor[6], vetor[7], vetor[8]);

					Endereco.mapaContas.put(vetor[0], enderecos);

				} else if (vetor[0].equalsIgnoreCase("Agencia")) {
					Agencia agencias = new Agencia(Integer.parseInt(vetor[1]), Integer.parseInt(vetor[2]),
							Integer.parseInt(vetor[3]), vetor[4]);
					Agencia.mapaAgencias.put(vetor[1], agencias);
					listaAgencias.add(agencias);
					

				} else {
					break;
				}

			} else {
				break;
			}
		}

		buffRead.close();

	}

	public static List<Cliente> juvenal() {
		return listaClientes;
	}
	
	public static List<Conta> juvenal2() {
		return listaContas;
		
	}
	
	public static List<Agencia> juvenal3() {
		return listaAgencias;
		
	}
	public static List<Funcionario> juvenal4() {
		return listaFuncionarios;
	}
	
	public static List<Gerente> juvenal5() {
		return listaGerentes;
	}
	
	
	public static void comprovanteSaqueDeposito(Conta conta, double valor, boolean ehDeposito) throws IOException {
		String saqueDeposito = "";
		if(ehDeposito) {
			saqueDeposito = "DEPOSITO";
		} else {
			saqueDeposito = "SAQUE";
		}
		Date now = new Date();
		String nomeArquivo = conta.getCpf() + "_" + conta.getNumeroAgencia() + "_" + conta.getNumeroConta()
				+ "_transacoes_" + now.getTime();
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(PATH_BASICO + nomeArquivo + EXTENSAO));

		String linha = "*************** " + saqueDeposito + " ***************";
		buffWrite.append(linha + "\n");

		linha = "CPF: " + conta.getCpf();
		buffWrite.append(linha + "\n");

		linha = "Ag�ncia: " + conta.getNumeroAgencia();
		buffWrite.append(linha + "\n");

		linha = "Conta: " + conta.getNumeroConta();
		buffWrite.append(linha + "\n");

		linha = "Valor: R$" + valor;
		buffWrite.append(linha + "\n");

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		linha = "Opera��o realizada em: " + simpleDateFormat.format(date);
		buffWrite.append(linha + "\n");

		linha = "*************** FIM DO " + saqueDeposito + " ***************";
		buffWrite.append(linha + "\n\n");

		buffWrite.close();

	}
	
	public static void comprovanteTransferencia(Conta contaOrigem, Conta contaDestino, double valor) throws IOException {
		Date now = new Date();
		String nomeArquivo = contaOrigem.getCpf() + "_" + contaOrigem.getNumeroAgencia() + "_" + contaOrigem.getNumeroConta()
				+ "_transacoes_" + now.getTime();
		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(PATH_BASICO + nomeArquivo + EXTENSAO));

		String linha = "*************** TRANFERENCIA ***************";
		buffWrite.append(linha + "\n");

		linha = "CPF do Remetente: " + contaOrigem.getCpf();
		buffWrite.append(linha + "\n");

		linha = "Ag�ncia do Remetente: " + contaOrigem.getNumeroAgencia();
		buffWrite.append(linha + "\n");

		linha = "Conta do Remetente: " + contaOrigem.getNumeroConta();
		buffWrite.append(linha + "\n");

		linha = "CPF do Destinat�rio: " + contaDestino.getCpf();
		buffWrite.append(linha + "\n");

		linha = "Ag�ncia do Destinat�rio: " + contaDestino.getNumeroAgencia();
		buffWrite.append(linha + "\n");

		linha = "Conta do Destinat�rio: " + contaDestino.getNumeroConta();
		buffWrite.append(linha + "\n");

		linha = "Valor: R$" + valor;
		buffWrite.append(linha + "\n");

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		linha = "Opera��o realizada em: " + simpleDateFormat.format(date);
		buffWrite.append(linha + "\n");

		linha = "*************** FIM DA TRANFERENCIA ***************";
		buffWrite.append(linha + "\n\n");

		buffWrite.close();

	}
	


	public static void relatorioContasPorAgencia(Conta conta) throws IOException {

		String nomeArquivo = conta.getCpf() + "_" + conta.getNumeroAgencia() + "_" + conta.getNumeroConta()
				+ "_contas_por_agencia";

		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(PATH_BASICO + nomeArquivo + EXTENSAO));

		int totalContas = 0;

		String linha = "********************** INFORMA��ES DO RESPONS�VEL **********************";
		buffWrite.append(linha + "\n\n");

		linha = "CPF: " + conta.getCpf();
		buffWrite.append(linha + "\n");

		linha = "Ag�ncia : " + conta.getNumeroAgencia();
		buffWrite.append(linha + "\n");

		linha = "*******************************************************";
		buffWrite.append(linha + "\n\n");

		linha = "*************** TOTAL DE CONTAS NA MESMA AG�NCIA ***************";
		buffWrite.append(linha + "\n\n");

		for (String cpf : Conta.mapaContas.keySet()) {
			if (Conta.mapaContas.get(cpf).getNumeroAgencia().equals(conta.getNumeroAgencia())) {

				linha = "CPF: " + Conta.mapaContas.get(cpf).getCpf();
				buffWrite.append(linha + "\n");

				linha = "Ag�ncia : " + Conta.mapaContas.get(cpf).getNumeroAgencia();
				buffWrite.append(linha + "\n");

				linha = "Conta: " + Conta.mapaContas.get(cpf).getNumeroConta();
				buffWrite.append(linha + "\n");

				totalContas++;

				linha = "**********************************";
				buffWrite.append(linha + "\n");
			}

		}

		linha = "Total de contas: " + totalContas;
		buffWrite.append(linha + "\n");

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		linha = "Opera��o realizada em: " + simpleDateFormat.format(date);
		buffWrite.append(linha + "\n");

		linha = "************************************************************************";
		buffWrite.append(linha + "\n\n");

		buffWrite.close();

	}

	public static void relatorioTotalCapital(Conta conta, Map<String, Conta> mapaContas) throws IOException {

		String nomeArquivo = conta.getCpf() + "_" + conta.getNumeroAgencia() + "_" + conta.getNumeroConta()
				+ "_total_capital";

		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(PATH_BASICO + nomeArquivo + EXTENSAO, true));

		double saldoTotal = 0.0;

		String linha = "************************* TOTAL DE CAPITAL ARMAZENADO *************************";
		buffWrite.append(linha + "\n\n");

		for (String cpf : Conta.mapaContas.keySet()) {

			saldoTotal += Conta.mapaContas.get(cpf).getSaldo();

		}

		linha = "O total de capital armazenado no banco � de: R$" + saldoTotal;
		buffWrite.append(linha + "\n");

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		linha = "Opera��o realizada em: " + simpleDateFormat.format(date);
		buffWrite.append(linha + "\n");

		linha = "*******************************************************************************";
		buffWrite.append(linha + "\n\n");
		buffWrite.close();

	}
	
}