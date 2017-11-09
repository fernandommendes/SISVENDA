package usuario;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import utilitarios.Console;
import utilitarios.LtpUtil;
import cadastro.Cadastro;
import dados.Cliente;
import dados.ItemVenda;
import dados.Produto;
import dados.Venda;
import erros.SisVendasException;


public class Usuario {
	public static void main(String[] args) {
		if (new File("Clientes.obj").exists()) {
			lerArqClientes();	
			if(Cadastro.listaClientes.size()!=0&&Cadastro.listaClientes.size()>-1){
				int ultCod = Cadastro.listaClientes.get(Cadastro.listaClientes.size()-1).getCodigo();
				Cliente.setSeq(ultCod);
			}
			if(Cadastro.listaProdutos.size()!=0&&Cadastro.listaProdutos.size()>-1){
				int ultCodP = Cadastro.listaProdutos.get(Cadastro.listaProdutos.size()-1).getCodigo();
				Produto.setSeq(ultCodP);
			}
			if(Cadastro.listaVendas.size()!=0&&Cadastro.listaVendas.size()>-1){
				int ultCodV = Cadastro.listaVendas.get(Cadastro.listaVendas.size()-1).getNumVenda();
				Venda.setSeq(ultCodV);
			}
		}
		menu();
		gravarArqClientes();
		System.out.println("\nFinalizar...");
		System.exit(0);
	}

	private static void gravarArqClientes() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Clientes.obj"));
			ObjectOutputStream out2 = new ObjectOutputStream(new FileOutputStream("Produtos.obj"));
			ObjectOutputStream out3 = new ObjectOutputStream(new FileOutputStream("Vendas.obj"));
			out.writeObject(Cadastro.listaClientes);
			out2.writeObject(Cadastro.listaProdutos);
			out3.writeObject(Cadastro.listaVendas);
			out.close();
			out2.close();
			out3.close();
		} catch (Exception erro) {
			System.out.println(erro.getMessage());
			System.exit(2); // Termino por falha na gravação do arquivo
		}
	}

	@SuppressWarnings("unchecked")
	private static void lerArqClientes() {
		try {
			ObjectInputStream inp = new ObjectInputStream(new FileInputStream("Clientes.obj"));
			Cadastro.listaClientes = (ArrayList<Cliente>)inp.readObject();
			ObjectInputStream inp2 = new ObjectInputStream(new FileInputStream("Produtos.obj"));
			Cadastro.listaProdutos = (ArrayList<Produto>)inp2.readObject();
			ObjectInputStream inp3 = new ObjectInputStream(new FileInputStream("Vendas.obj"));
			Cadastro.listaVendas = (ArrayList<Venda>)inp3.readObject();
			inp.close();
			inp2.close();
			inp3.close();
		} catch (Exception erro) {
			System.out.println(erro.getMessage());
			System.exit(1); // termino por falha na leitura do arquivo
		}

	}

	private static void menu() {
		int opcao=0;
		do {
			System.out.println("\nCadastro de Clientes");
			System.out.println("1-Cadastrar Cliente");
			System.out.println("2-Alterar Cliente");
			System.out.println("3-Excluir Cliente");
			System.out.println("4-Pesquisar Cliente pelo Código");
			System.out.println("5-Pesquisar Cliente pelo CPF");
			System.out.println("6-Pesquisar Cliente pelo Nome");
			System.out.println("7-Cadastrar Produto");
			System.out.println("8-Alterar Produto");
			System.out.println("9-Excluir Produto");
			System.out.println("10-Pesquisar Produto pelo Código");
			System.out.println("11-Pesquisar Produto pelo Nome");
			System.out.println("12-Incluir Venda");
			System.out.println("13-Pesquisar Venda pelo código");
			System.out.println("14-Pesquisar Venda por periodo");
			System.out.println("0-Sair");
			opcao=Console.readInt("Informe o nº da opção: ");
			switch (opcao) {
			case 1:
				incluirCliente();
				break;
			case 2:
				alterarCliente();
				break;
			case 3:
				excluirCliente();
				break;
			case 4:
				pesquisaClienteCod();
				break;
			case 5:
				pesquisaClienteCPF();
				break;
			case 6:
				pesquisaClienteNome();
				break;
			case 7:
				incluirProduto();
				break;
			case 8:
				alterarProduto();
				break;
			case 9:
				excluirProduto();
				break;
			case 10:
				pesquisaProdutoCod();
				break;
			case 11:
				pesquisaProdutoNome();
				break;
			case 12:
				incluirVenda();
				break;
			case 13:
				pesquisaVenda();
				break;
			case 14:
				pesquisaPeriodoVenda();
				break;
			case 0:
				break;
			default:
				System.out.println("Opção Inválida.");
				break;
			}
		} while (opcao!=0);

	}


	private static void pesquisaPeriodoVenda() {
		System.out.println("\nPESQUISAR VENDA PELO PERIODO\n");
		GregorianCalendar dataInicio = new GregorianCalendar();
		GregorianCalendar dataFim = new GregorianCalendar();
		ArrayList<Venda> lista = Cadastro.listaVendas;
		for(Venda objV:lista){
			while (true) {
				String dataIn = Console.readLine("Data de Inicio: ");
				if (!LtpUtil.validarData(dataIn, dataInicio)) {
					System.out.println("Data de Inicio Inválida");
					continue;
				}
				if (dataInicio.after(new GregorianCalendar())) {
					System.out.println("Data de Inicio superior a Data Corrente.");
				} else break;
			}
			
			while (true) {
				String dataF = Console.readLine("Data da Fim: ");
				if (!LtpUtil.validarData(dataF, dataFim)) {
					System.out.println("Data de Fim Inválida");
					continue;
				}
				if (dataFim.before(dataInicio)||dataInicio.after(new GregorianCalendar())) {
					System.out.println("Data de Fim Inferior a Data de Inicio ou Superior a Data Atual.");
				} else break;
			}
			GregorianCalendar dataComp = objV.getDataVenda();
			if ((dataComp.after(dataInicio)&&dataComp.before(dataFim))||dataComp.equals(dataInicio)||dataComp.equals(dataFim)){
				System.out.println(objV.toString());
				break;
			}else{
				System.out.println("Não existe vendas nesse periodo.");
				break;
			}
		}
	}

	private static void pesquisaVenda() {
		System.out.println("\nPESQUISAR VENDA PELO CÓDIGO\n");
		try {
			int cod = Console.readInt("Informe o código da venda: ");
			Venda venda = Cadastro.pesqVendaCod(cod);
			System.out.println(venda.toString());
		} catch (SisVendasException erro) {
			System.out.println(erro.getMessage());
		}

	}

	private static void incluirVenda() {	
		ArrayList<ItemVenda> item = new ArrayList<ItemVenda>();
		GregorianCalendar dataVenda = new GregorianCalendar();
		System.out.println("\nINCLUIR VENDA PARA O CLIENTE\n");
		try {
			String cpf = Console.readLine("Informe o CPF do cliente: ");
			Cliente resp = Cadastro.pesqClienteCpf(cpf);
			System.out.println(resp.toString());
			while (true){
				if (Console.readLine("Deseja incluir venda? S/N").equalsIgnoreCase("S")){
					String nome = Console.readLine("Informe o nome do produto: ");
					ArrayList<Produto> prod = Cadastro.pesqProdutoNome(nome);
					for (Produto objProduto : prod ) {
						System.out.println(objProduto.toString());
						while (true){
							if (Console.readLine("Deseja incluir este produto? S/N").equalsIgnoreCase("S")){
								GregorianCalendar dataInclusao = resp.getDataInclusao();
								while (true) {
									String dataV = Console.readLine("Data da Venda: ");
									if (!LtpUtil.validarData(dataV, dataVenda)) {
										System.out.println("Data Inválida");
										continue;
									}
									if (dataVenda.before(dataInclusao)||dataVenda.after(new GregorianCalendar())) {
										System.out.println("Data da Venda Inferior a Data de Inclusao ou Superior a Data Atual.");
									} else break;
								}

								int quant = Console.readInt("Quantidade do produto: ");
								if (quant <= 0){
									System.out.println("Quantidade inválida.");
									continue;
								}
								double precoUn = objProduto.getPrecoUnitario();
								double valorT = precoUn*quant;
								item.add(new ItemVenda(objProduto,precoUn,quant,valorT));
								System.out.println("Item adicionado.");
								break;
							}
							break;
						}
						break;
					}
				}else{
					Cadastro.incluirVenda(new Venda(resp,dataVenda,item));
					System.out.println("Venda cadastrada.");
					break;	
				}
			}
		} catch (SisVendasException erro) {
			System.out.println(erro.getMessage());
		}
	}


	private static void pesquisaProdutoNome() {
		System.out.println("\nPESQUISAR PRODUTO PELO NOME\n");
		try {
			String nome = Console.readLine("Informe o Nome ou parte dele: ");
			ArrayList<Produto> resp = Cadastro.pesqProdutoNome(nome);
			for (Produto objProdutos : resp ) {
				System.out.println(objProdutos.toString());
			}
		} catch (SisVendasException erro) {
			System.out.println(erro.getMessage());
		}
	}

	private static void pesquisaProdutoCod() {
		System.out.println("\nPESQUISAR PRODUTO PELO CÓDIGO\n");
		try {
			int cod = Console.readInt("Informe o código do produto: ");
			ArrayList<Produto> resp = Cadastro.pesqProdutoCod(cod);
			for (Produto objProdutos : resp ) {
				System.out.println(objProdutos.toString());
			}
		} catch (SisVendasException erro) {
			System.out.println(erro.getMessage());
		}

	}

	private static void excluirProduto() {

		System.out.println("\nEXCLUIR PRODUTO\n");
		try {
			int cod = Console.readInt("Informe o código do produto: ");
			ArrayList<Produto> resp = Cadastro.pesqProdutoCod(cod);
			for (Produto objProdutos : resp ) {
				System.out.println(objProdutos.toString());
				while (true){
					if (Console.readLine("Deseja excluir? S/N").equalsIgnoreCase("S")){
						Cadastro.excluirProduto(objProdutos);
						System.out.println("Produto excluido.");
						break;
					}
				}
			}
		} catch (SisVendasException erro) {
			System.out.println(erro.getMessage());
		}

	}

	private static void alterarProduto() {
		System.out.println("\nALTERAÇÃO DO PRODUTO\n");
		try {
			int cod = Console.readInt("Informe o código do produto: ");
			ArrayList<Produto> resp = Cadastro.pesqProdutoCod(cod);
			for (Produto objProduto : resp ) {
				System.out.println(objProduto.toString());
				while (true){
					if (Console.readLine("Deseja fazer alteração? S/N").equalsIgnoreCase("S")){
						String nome;
						while (true) {
							nome = Console.readLine("Nome do produto: ").trim();
							if (nome.isEmpty()) {
								System.out.println("Falta o nome do produto.");
								continue;
							} else {
								try {
									ArrayList<Produto> objProdutos = Cadastro.pesqProdutoNome(nome);
									System.out.println(objProdutos.toString());
									System.out.println("\nJÁ EXISTE UM PRODUTO COM ESSE NOME CADASTRADO!");
								} catch (SisVendasException e) {
									break;
								}
							}
						}

						double precoUnitario;
						while (true) {
							precoUnitario = Console.readDouble("Preço Unitário: R$ ");
							if (precoUnitario<=0){
								System.out.println("Valor Inválido.");
								continue;
							}else{
								break;
							}
						}

						GregorianCalendar dataInclusao = new GregorianCalendar();
						dataInclusao = objProduto.getDataInclusao();

						GregorianCalendar dataUltAlteracao = new GregorianCalendar();
						while (true) {
							String dataAlt = Console.readLine("Data da Alteração: ");
							if (!LtpUtil.validarData(dataAlt, dataUltAlteracao)) {
								System.out.println("Data de Alteração Inválida");
								continue;
							}
							if (dataUltAlteracao.before(dataInclusao)||dataUltAlteracao.after(new GregorianCalendar())) {
								System.out.println("Data de Alteração Inferior a Data de Inclusao ou Superior a Data Atual.");
							} else break;
						}
						objProduto.setNome(nome);
						objProduto.setPrecoUnitario(precoUnitario);
						objProduto.setDataUltAlteracao(dataUltAlteracao);
						System.out.println("\nAlteração realizado!");
						break;
					}else{
						System.out.println("Produto inalterado.");
					} break;
				}
			}

		} catch (SisVendasException erro) {
			System.out.println(erro.getMessage());
		}

	}

	private static void incluirProduto() {
		System.out.println("\nINCLUIR PRODUTO\n");
		String nome;
		while (true) {
			nome = Console.readLine("Nome do produto: ").trim();
			if (nome.isEmpty()) {
				System.out.println("Falta o nome do produto.");
				continue;
			} else {
				try {
					ArrayList<Produto> objProdutos = Cadastro.pesqProdutoNome(nome);
					System.out.println(objProdutos.toString());
					System.out.println("\nJÁ EXISTE UM PRODUTO COM ESSE NOME CADASTRADO!");
				} catch (SisVendasException e) {
					break;
				}
			}
		}

		double precoUnitario;
		while (true) {
			precoUnitario = Console.readDouble("Preço Unitário: R$ ");
			if (precoUnitario<=0){
				System.out.println("Valor Inválido.");
				continue;
			}else{
				break;
			}
		}

		GregorianCalendar dataInclusao = new GregorianCalendar();
		while (true) {
			String dataInc = Console.readLine("Data de Inclusão: ");
			if (!LtpUtil.validarData(dataInc, dataInclusao)) {
				System.out.println("Data de Inclusão Inválida");
				continue;
			}
			if (dataInclusao.after(new GregorianCalendar())) {
				System.out.println("Data de Inclusão superior a Data Corrente.");
			} else break;
		}
		Cadastro.incluirProduto(new Produto(nome, precoUnitario, dataInclusao));
		System.out.println("Produto cadastrado!");
	}

	private static void pesquisaClienteNome() {
		System.out.println("\nPESQUISAR CLIENTE PELO NOME\n");
		try {
			String nome = Console.readLine("Informe o Nome ou parte dele: ");
			ArrayList<Cliente> resp = Cadastro.pesqClienteNome(nome);
			for (Cliente objClientes : resp ) {
				System.out.println(objClientes.toString());
			}
		} catch (SisVendasException erro) {
			System.out.println(erro.getMessage());
		}

	}

	private static void pesquisaClienteCPF() {
		System.out.println("\nPESQUISAR CLIENTE PELO CPF\n");
		try {
			String cpf = Console.readLine("Informe o CPF: ");
			Cliente objClientes = Cadastro.pesqClienteCpf(cpf);
			System.out.println(objClientes.toString());
		} catch (SisVendasException erro) {
			System.out.println(erro.getMessage());
		}

	}

	private static void pesquisaClienteCod() {
		System.out.println("\nPESQUISAR CLIENTE PELO CÓDIGO\n");
		try {
			int cod = Console.readInt("Informe o código do cliente: ");
			ArrayList<Cliente> resp = Cadastro.pesqClienteCod(cod);
			for (Cliente objClientes : resp ) {
				System.out.println(objClientes.toString());
			}
		} catch (SisVendasException erro) {
			System.out.println(erro.getMessage());
		}
	}

	private static void excluirCliente() {
		System.out.println("\nEXCLUIR CLIENTE\n");		
		try {
			int cod = Console.readInt("Informe o código do cliente: ");
			ArrayList<Cliente> resp = Cadastro.pesqClienteCod(cod);
			for (Cliente objClientes : resp ) {
				System.out.println(objClientes.toString());				
				while (true){
					if (Console.readLine("Deseja excluir? S/N").equalsIgnoreCase("S")){
						Cadastro.excluirCliente(objClientes);
						System.out.println("Cliente excluido.");
						break;
					}
				}
			}
		} catch (SisVendasException erro) {
			System.out.println(erro.getMessage());
		}

	}

	private static void alterarCliente() {
		System.out.println("\nALTERAÇÃO DE CLIENTE\n");
		try {
			int cod = Console.readInt("Informe o código do cliente: ");
			ArrayList<Cliente> resp = Cadastro.pesqClienteCod(cod);
			for (Cliente objClientes : resp ) {
				System.out.println(objClientes.toString());
				while (true){
					if (Console.readLine("Deseja fazer alteração? S/N").equalsIgnoreCase("S")){
						String nome;
						while (true) {
							nome = Console.readLine("Nome: ").trim();
							if (nome.isEmpty() || LtpUtil.contarPalavras(nome) < 2) {
								System.out.println("Falta o nome ou nome incompleto.");
							} else break;
						}

						String cpf;
						while (true) {
							cpf = Console.readLine("CPF: ");
							if (!LtpUtil.validarCPF(cpf))  {
								System.out.println("CPF Inválido.");
								continue;
							}
							try {
								objClientes = Cadastro.pesqClienteCpf(cpf);
								System.out.println("CPF já está cadastrado para o cliente " +
										objClientes.getNome());
							} catch (SisVendasException e) {
								break;
							}
						}

						String email;
						while(true) {
							email = Console.readLine("Email: ");
							if (!email.matches("\\w+\\d@\\w+[.]\\w{3}[.]\\w{2}|\\w+@\\w+[.]\\w{2,3}")){
								System.out.println("Email inválido.");
								continue;
							}else{
								break;
							}
						}

						String telefone;
						while (true) {
							telefone = Console.readLine("Telefone: ");
							if (!telefone.matches("\\d{4}-?\\d{4}"))  {
								System.out.println("Telefone Inválido.");
								continue;
							}else{
								break;
							}

						}

						GregorianCalendar dataInclusao = new GregorianCalendar();
						dataInclusao = objClientes.getDataInclusao();

						GregorianCalendar dataUltAlteracao = new GregorianCalendar();
						while (true) {
							String dataAlt = Console.readLine("Data da Alteração: ");
							if (!LtpUtil.validarData(dataAlt, dataUltAlteracao)) {
								System.out.println("Data de Alteração Inválida");
								continue;
							}
							if (dataUltAlteracao.before(dataInclusao)||dataUltAlteracao.after(new GregorianCalendar())) {
								System.out.println("Data de Alteração Inferior a Data de Inclusao ou Superior a Data Atual.");
							} else break;
						}
						objClientes.setCpf(cpf);
						objClientes.setNome(nome);
						objClientes.setTelefone(telefone);
						objClientes.setEmail(email);
						objClientes.setDataUltAlteracao(dataUltAlteracao);
						System.out.println("\nAlteração realizado!");
						break;
					}else{
						System.out.println("Cadastro inalterado.");
					} break;
				}
			}

		} catch (SisVendasException erro) {
			System.out.println(erro.getMessage());
		}
	}

	private static void incluirCliente() {
		System.out.println("\nINCLUIR CLIENTE\n");
		String nome;
		while (true) {
			nome = Console.readLine("Nome: ").trim();
			if (nome.isEmpty() || LtpUtil.contarPalavras(nome) < 2) {
				System.out.println("Falta o nome ou nome incompleto.");
			} else break;
		}

		String cpf;
		while (true) {
			cpf = Console.readLine("CPF: ");
			if (!LtpUtil.validarCPF(cpf))  {
				System.out.println("CPF Inválido.");
				continue;
			}
			try {
				Cliente objClientes = Cadastro.pesqClienteCpf(cpf);
				System.out.println("CPF já está cadastrado para o cliente " +
						objClientes.getNome());
			} catch (SisVendasException e) {
				break;
			}
		}
		String email;
		while(true) {
			email = Console.readLine("Email: ");
			if (!email.matches("\\w+\\d@\\w+[.]\\w{3}[.]\\w{2}|\\w+@\\w+[.]\\w{2,3}")){
				System.out.println("Email inválido.");
				continue;
			}else{
				break;
			}
		}

		String telefone;
		while (true) {
			telefone = Console.readLine("Telefone: ");
			if (!telefone.matches("\\d{4}-?\\d{4}"))  {
				System.out.println("Telefone Inválido.");
				continue;
			}else{
				break;
			}
		}

		GregorianCalendar dataInclusao = new GregorianCalendar();
		while (true) {
			String dataInc = Console.readLine("Data de Inclusão: ");
			if (!LtpUtil.validarData(dataInc, dataInclusao)) {
				System.out.println("Data de Inclusão Inválida");
				continue;
			}
			if (dataInclusao.after(new GregorianCalendar())) {
				System.out.println("Data de Inclusão superior a Data Corrente.");
			} else break;
		}

		Cadastro.incluirCliente(new Cliente(cpf, nome, telefone, email, dataInclusao));
		System.out.println("\nCadastro realizado!");
	}
}
