import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import modelos.Caixa;
import modelos.Data;
import modelos.Funcionario;
import modelos.PagamentoCartao;
import modelos.PagamentoDinheiro;
import modelos.Pessoa;
import modelos.Produto;
import modelos.ProdutoQuilo;
import modelos.ProdutoUnidade;
import modelos.Venda;
import servicos.CaixaService;
import servicos.EstoqueService;
import servicos.FuncionarioService;
import servicos.LeitorCodigoBarraService;
import servicos.RelatorioEstoqueService;
import servicos.RelatorioVendasService;
import servicos.VendaService;
import servicos.VendasService;

public class Main {

	private static CaixaService caixaService = CaixaService.getInstanciaCaixaService();
	private static FuncionarioService funcionarioService = FuncionarioService.getInstanciaFuncionarioService();
	private static VendasService vendasService = VendasService.getInstanciaVendasService();
	private static EstoqueService estoqueService = EstoqueService.getInstanciaEstoqueService();
	private static RelatorioVendasService relatorioVendasService = RelatorioVendasService.getInstanciaRelatorioVendasService();
	private static RelatorioEstoqueService relatorioEstoqueService = RelatorioEstoqueService.getInstanciaRelatorioEstoqueService();

	public static void main(String[] args) {

		Scanner leitor = new Scanner(System.in);

		testaProdutos();
		testeFuncionarios();
		testarCaixa();

		System.out.println("Digite a sua função \n1 - Gerente \n2 - Funcionário \n3 - Cliente \n0 - Sair");
		int op = leitor.nextInt();

		while (op != 0) {
			switch (op) {
			case 1:

				gerente();
				break;

			case 2:
				funcionario();
				break;

			case 3:
				cliente();
				break;

			case 0:
				continue;

			default:
				System.out.println("Código inválido!");
				;
			}

			System.out.println("Digite a sua função \n1 - Gerente \n2 - Funcionário \n3 - Cliente \n0 - Sair");
			op = leitor.nextInt();
		}

		leitor.close();
	}

	private static void cliente() {
		LeitorCodigoBarraService leitorCodigoBarra = new LeitorCodigoBarraService();	
		
		Scanner leitor = new Scanner(System.in);

		System.out.println("Digite a operação desejada \n1 - Consultar o preço dos produtos em leitores \n0 - Sair");
		int op = leitor.nextInt();

		while (op != 0) {

			switch (op) {
			case 1:
				
				System.out.println("Digite o código de barra do produto:");
				long codigoBarra = leitor.nextLong();
				System.out.printf("Preço do produto: %.2f", leitorCodigoBarra.preçoProduto(Long.toString(codigoBarra)));
				break;

			default:
				break;
			}

			System.out
					.println("Digite a operação desejada \n1 - consultem o preço dos produtos em leitores \n0 - Sair");
			op = leitor.nextInt();
		}

		leitor.close();
	}

	private static void funcionario() {
		Scanner leitor = new Scanner(System.in);

		System.out.println("Digite a operação desejada:\n" + "1 - Iniciar o trabalho em um caixa \n" + "0 - Sair \n");
		int op = leitor.nextInt();

		while (op != 0) {

			switch (op) {
			case 1:
				iniciarTrabalhoCaixa();
				break;

			default:
				break;
			}

			System.out.println("Digite a operação desejada: \n1 - Iniciar o trabalho em um caixa \n" + "0 - Sair \n");
			op = leitor.nextInt();
		}

	}

	private static void gerente() {
		Scanner leitor = new Scanner(System.in);

		System.out.println("Digite a operação desejada \n1 - Cadastrar caixa\n" + "2 - Cadastrar funcionario\n"
				+ "3 - Cadastrar Produtos\n" + "4 - Relatorio Vendas\n" + "5 - Relatorio Estoque\n"
				+ "6 - Visualizar funcionarios\n" + "7 - Visualizar produtos\n" + "0 - Sair");
		int op = leitor.nextInt();

		while (op != 0) {

			switch (op) {
			case 1:
				cadastrarCaixa();
				break;
			case 2:
				cadastrarFuncionario();
				break;
			case 3:
				cadastrarProduto();
				break;
			case 4:
				relatorioVendas();
				break;
			case 5:
				relatorioEstoque();
				break;
			case 6:
				visualizarFuncionarios();
				break;
			case 7:
				visualizarProdutos();
				break;
			default:
				break;
			}

			System.out.println("Digite a operação desejada \n1 - Cadastrar caixa\n" + "2 - Cadastrar funcionario\n"
					+ "3 - Cadastrar Produtos\n" + "4 - Relatorio Vendas\n" + "5 - Relatorio Estoque\n"
					+ "6 - Visualizar funcionarios\n" + "7 - Visualizar produtos\n" + "0 - Sair");
			op = leitor.nextInt();
		}

	}

	private static void iniciarTrabalhoCaixa() {
		Scanner leitor = new Scanner(System.in);

		System.out.println("Digite a operação desejada:\n" + "1 - Visualizar caixas livres \n"
				+ "2 - Escolher Caixa para trabalhar \n" + "0 - Sair\n");
		int op = leitor.nextInt();

		while (op != 0) {

			switch (op) {
			case 1:
				List<Caixa> caixaLivres = new ArrayList<>();
				caixaLivres = caixaService.getCaixasLivres();

				if (caixaLivres.size() > 1) {
					System.out.println("Caixa livres: ");
					for (Caixa caixa : caixaLivres) {
						System.out.println(caixa.getCod());
					}
				} else {
					System.out.println("Nenhum caixa livre");
				}
				break;
			case 2:
				System.out.println("Digite o seu código: ");
				int codFuncionario = leitor.nextInt();
				Pessoa pessoa = funcionarioService.bucarFuncionario(codFuncionario);

				if (pessoa == null) {
					System.out.println("Pessoa não encontrada");
					break;
				}

				System.out.println("Digite o caixa que deseja trabalhar:");
				int codCaixa = leitor.nextInt();
				Caixa caixa = caixaService.bucarCaixa(codCaixa);

				if (caixa == null) {
					System.out.println("Caixa não encontrado");
					break;
				}

				if (caixaService.iniciarCaixa(caixa, pessoa))
					operarCaixaFuncionario(caixa);
				else
					System.out.println("Não foi possível iniciar o caixa. Caixa");
			default:
				break;
			}

			System.out.println("Digite a operação desejada: \n" + "1 - Visualizar caixas livres\n"
					+ "2 - Escolher Caixa para trabalhar\n" + "0 - Sair\n");
			op = leitor.nextInt();
		}
	}

	private static void operarCaixaFuncionario(Caixa caixa) {
		Scanner leitor = new Scanner(System.in);

		System.out.println("Digite a operação desejada:\n" + "1 - Iniciar venda\n" + "0 - Sair\n");
		int op = leitor.nextInt();

		while (op != 0) {
			switch (op) {
			case 1:
				fazervenda(caixa);
				break;
			default:
				break;
			}
			System.out.println("Digite a operação desejada: \n" + "1 - Iniciar venda \n" + "0 - Sair \n");
			op = leitor.nextInt();
		}

		caixa.setPessoa(null);

	}

	private static void fazervenda(Caixa caixa) {
		VendaService vendaService = new VendaService();
		
		vendaService.iniciarVenda(caixa);

		Scanner leitor = new Scanner(System.in);

		Data data = new Data();
		System.out.println("Digite a data da venda: ");
		System.out.println("Dia: ");
		data.setDia(leitor.nextInt());

		System.out.println("Mes: ");
		data.setMes(leitor.nextInt());

		System.out.println("Ano: ");
		data.setAno(leitor.nextInt());

		vendaService.getVenda().setDataVenda(data);

		System.out.println(
				"Digite a operação desejada: \n" + "1 - Iniciar adição de produtos\n" + "0 - Terminar venda\n");
		int op = leitor.nextInt();

		while (op != 0) {
			
			switch (op) {
			case 1:
				
				System.out.println("Digite o código do produto: ");
				int codProduto = leitor.nextInt();

				if (estoqueService.verificarCodigo(codProduto)) {

					Produto produto = estoqueService.getProduto(codProduto);

					if (produto instanceof ProdutoUnidade) {

						System.out.println("Digite quantas unidades: ");
						int qntProduto = leitor.nextInt();

						if (estoqueService.verificarQuantidadeUnidade(codProduto, qntProduto))
							vendaService.adicionarProdutoUnidade(codProduto, qntProduto);
						else
							System.out.println("Quantidade indisponível!");
					} else {
						System.out.println("Digite o quilo: ");
						double kgProduto = leitor.nextDouble();

						if (estoqueService.verificarQuantidadeKg(codProduto, kgProduto))
							vendaService.adicionarProdutoQuilo(codProduto, kgProduto);
						else
							System.out.println("Quantidade indisponível!");

					}

				} else {
					System.out.println("Código inválido!");
				}

				break;
			
			case 0:
				break;

			default:
				System.out.println("Código inválido!");
				break;
			}

			System.out.println(
					"Digite a operação desejada: \n" + "1 - Continuar adição de produtos\n" + "0 - Terminar venda\n");
			op = leitor.nextInt();
			
		}
		vendaService.finalizarVenda(caixa);

		System.out.println(vendaService.getVenda().getRelatorioVenda());
		System.out.println("Total a pagar: " + vendaService.getVenda().getTotal());
		System.out.println("Opções de Pagamento:");
		System.out.println("Digite a opção de pagamento:\n1 - Cartão de crédito\n2 - Dinheiro:");
		int opPagamento = leitor.nextInt();
		leitor.nextLine();

		if (opPagamento == 1) {
			System.out.println("Digite o número do cartao");
			String numeroCartao = leitor.nextLine();

			System.out.println("Digite a quantidade de parcelas");
			int qntParcelas = leitor.nextInt();
			leitor.nextLine();

			PagamentoCartao pagamento = new PagamentoCartao();
			pagamento.setValorPagamento(vendaService.getVenda().getTotal() / qntParcelas);
			pagamento.setQntParcelas(qntParcelas);
			pagamento.setNumeroCartao(numeroCartao);
			vendaService.getVenda().setPagamento(pagamento);

			System.out.println("Quantidade de parcelas: " + qntParcelas);
			System.out.printf("Valor das parcelas: %.2f\n", pagamento.getValorPagamento());

		} else {
			System.out.println("Digite o valor pago");
			Double pagamento = leitor.nextDouble();
			PagamentoDinheiro pagamentoDinheiro = new PagamentoDinheiro();
			pagamentoDinheiro.setValorPagamento(vendaService.getVenda().getTotal());
			pagamentoDinheiro.setValorPago(pagamento);
			pagamentoDinheiro.calcularTroco(pagamento);

			vendaService.getVenda().setPagamento(pagamentoDinheiro);
			System.out.println("Troco: " + pagamentoDinheiro.getTroco());
		}

		Venda venda = vendaService.finalizarVenda(caixa);
		vendasService.adicionarVenda(venda);
		caixa.adicionarVenda(venda);

	}

	public static void cadastrarCaixa() {

		Scanner leitor = new Scanner(System.in);
		Caixa caixa = new Caixa();

		System.out.println("Cadastro de caixa\n");

		System.out.println("Digite o codigo do caixa: ");
		caixa.setCod(leitor.nextInt());

		caixaService.adicionarCaixa(caixa);
	}

	public static void cadastrarFuncionario() {

		Scanner leitor = new Scanner(System.in);
		Funcionario funcionario = new Funcionario();

		System.out.println("-------Cadastro de funcionario-------\n");

		System.out.println("Digite o nome do funcionario: ");
		funcionario.setNome(leitor.nextLine());

		System.out.println("Digite o codigo do funcionario: ");
		funcionario.setCodigo(leitor.nextInt());

		leitor.reset();
		System.out.println("Digite o cpf do funcionario: ");
		funcionario.setCpf(leitor.nextInt());

		System.out.println("Digite o salario do funcionario: ");
		funcionario.setSalario(leitor.nextDouble());

		funcionarioService.adicionarFuncionario(funcionario);
	}

	public static void cadastrarProduto() {
		Scanner leitor = new Scanner(System.in);

		System.out.println("-------Cadastro de produto-------\n");
		System.out.println("Digite a medida do produto:\n1 - Quilos\n 2- Unidades");
		int op = leitor.nextInt();
		leitor.nextLine();

		System.out.println("Digite o nome do produto: ");
		String nome = leitor.nextLine();

		System.out.println("Digite a marca do produto ");
		String marca = leitor.nextLine();

		System.out.println("Digite a descrição do produto: ");
		String descricao = leitor.nextLine();

		System.out.println("Digite o preco do produto: ");
		Double preco = leitor.nextDouble();

		System.out.println("Digite o codigo do produto");
		int codigo = leitor.nextInt();
		leitor.nextLine();

		System.out.println("Digite o codigo de barras do produto:");
		String codBarras = leitor.nextLine();

		if (op == 1) {
			ProdutoQuilo produto = new ProdutoQuilo();

			produto.setNome(nome);
			produto.setMarca(marca);
			produto.setDescricao(descricao);
			produto.setCodigo(codigo);
			produto.setCodigoBarra(codBarras);
			produto.setPreco(preco);

			System.out.println("Digite os quilos do produto ");
			produto.setQuilo(leitor.nextDouble());
			estoqueService.adicionarProduto(produto);

		} else {
			ProdutoUnidade produto = new ProdutoUnidade();

			produto.setNome(nome);
			produto.setMarca(marca);
			produto.setDescricao(descricao);
			produto.setCodigo(codigo);
			produto.setCodigoBarra(codBarras);
			produto.setPreco(preco);

			System.out.println("Digite as unidades do produto ");
			produto.setQuantidade(leitor.nextInt());
			estoqueService.adicionarProduto(produto);
		}

	}

	public static void relatorioVendas() {		
		relatorioVendasService.setCaixaService(caixaService);
		String relatorioVendas = relatorioVendasService.relatorioVendas();

		System.out.println(relatorioVendas);
	}

	public static void relatorioEstoque() {
		Scanner leitor = new Scanner(System.in);
		System.out.println("Data:");
		Data data = new Data();

		System.out.println("Digite o dia:");
		data.setDia(leitor.nextInt());

		System.out.println("Digite o mes: ");
		data.setMes(leitor.nextInt());

		System.out.println("Digite o ano: ");
		data.setAno(leitor.nextInt());

		relatorioEstoqueService.setEstoqueService(estoqueService);
		relatorioEstoqueService.setCaixaService(caixaService);

		String relatorioEstoque = relatorioEstoqueService.gerarRelatorio(data);

		System.out.println(relatorioEstoque);
	}

	public static void testeFuncionarios() {

		Funcionario fun1 = new Funcionario();
		fun1.setNome("Aquamarine");
		fun1.setCpf(314134124);
		fun1.setSalario(1123);
		fun1.setCodigo(1);

		Funcionario fun2 = new Funcionario();
		fun2.setNome("Elizabeth");
		fun2.setCpf(314134124);
		fun2.setSalario(1123);
		fun2.setCodigo(2);

		Funcionario fun3 = new Funcionario();
		fun3.setNome("Lazaro");
		fun3.setCpf(314134124);
		fun3.setSalario(1123);
		fun3.setCodigo(3);

		funcionarioService.adicionarFuncionario(fun1);
		funcionarioService.adicionarFuncionario(fun2);
		funcionarioService.adicionarFuncionario(fun3);
	}

	public static void testaProdutos() {
		ProdutoQuilo prodQ = new ProdutoQuilo();
		prodQ.setCodigo(1);
		prodQ.setCodigoBarra("1234");
		prodQ.setNome("Carne");
		prodQ.setDescricao("Carne bovina");
		prodQ.setMarca("Ares");
		prodQ.setPreco(15.00);
		prodQ.setQuilo(90);

		ProdutoQuilo prodQ2 = new ProdutoQuilo();
		prodQ2.setCodigo(2);
		prodQ2.setCodigoBarra("1235");
		prodQ2.setNome("Carne");
		prodQ2.setDescricao("Carne suína");
		prodQ2.setMarca("Ares");
		prodQ2.setPreco(19.00);
		prodQ2.setQuilo(150);

		ProdutoQuilo prodQ3 = new ProdutoQuilo();
		prodQ3.setCodigo(3);
		prodQ3.setCodigoBarra("1236");
		prodQ3.setNome("Carne");
		prodQ3.setDescricao("Carne branca");
		prodQ3.setMarca("Ares");
		prodQ3.setPreco(13.00);
		prodQ3.setQuilo(50);

		ProdutoUnidade prodU4 = new ProdutoUnidade();
		prodU4.setCodigo(4);
		prodU4.setCodigoBarra("1237");
		prodU4.setNome("Toddy");
		prodU4.setDescricao("Achocolatado");
		prodU4.setMarca("Toddy");
		prodU4.setPreco(13.00);
		prodU4.setQuantidade(10);

		ProdutoUnidade prodU5 = new ProdutoUnidade();
		prodU5.setCodigo(5);
		prodU5.setCodigoBarra("1238");
		prodU5.setNome("Sabonete Dove");
		prodU5.setDescricao("Sabonete");
		prodU5.setMarca("Dove");
		prodU5.setPreco(7.00);
		prodU5.setQuantidade(10);

		estoqueService.adicionarProduto(prodQ);
		estoqueService.adicionarProduto(prodQ2);
		estoqueService.adicionarProduto(prodQ3);
		estoqueService.adicionarProduto(prodU4);
		estoqueService.adicionarProduto(prodU5);
	}

	public static void testarCaixa() {
		Caixa c1 = new Caixa();
		c1.setCod(1);

		Caixa c2 = new Caixa();
		c2.setCod(2);

		Caixa c3 = new Caixa();
		c3.setCod(3);

		caixaService.adicionarCaixa(c1);
		caixaService.adicionarCaixa(c2);
		caixaService.adicionarCaixa(c3);
	}

	public static void visualizarProdutos() {

		Iterator it = estoqueService.getProdutos().iterator();
		Produto prod;

		System.out.println("===========PRODUTOS==========");

		while (it.hasNext()) {
			prod = (Produto) it.next();
			System.out.println("\nNome do produto: " + prod.getNome());
			System.out.println("Descrição do produto:" + prod.getDescricao());
			System.out.println("Codigo do produto: " + prod.getCodigo());
			System.out.println("Descrição: " + prod.getDescricao());
			if (prod instanceof ProdutoUnidade)
				System.out.println("Quantidade disponível: " + ((ProdutoUnidade) prod).getQuantidade() + " unidades\n");
			else
				System.out.println("Quantidade disponível: " + ((ProdutoQuilo) prod).getQuilo() + " quilos\n");

		}

	}

	public static void visualizarFuncionarios() {

		Iterator it = funcionarioService.getFuncionarios().iterator();
		Funcionario func;

		System.out.println("===========FUNCIONARIOS==========");

		while (it.hasNext()) {
			func = (Funcionario) it.next();
			System.out.println("Nome do produto: " + func.getNome());
			System.out.println("Codigo do produto: " + func.getCodigo());

		}

	}

}
