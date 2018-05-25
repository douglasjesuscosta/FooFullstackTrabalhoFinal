import java.util.ArrayList;
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
	
	private static CaixaService caixaService = new CaixaService();
	private static FuncionarioService funcionarioService = new FuncionarioService();
	private static VendasService vendasService = new VendasService();
	private static EstoqueService estoqueService = new EstoqueService();

	public static void main(String[] args) {

		Scanner leitor = new Scanner(System.in);

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

			System.out.println("Digite a sua função \n1 - Gerente \n2 - Funcionário \n3 - Cliente \n0 - Sair)");
			op = leitor.nextInt();
		}

		leitor.close();
	}

	private static void cliente() {
		Scanner leitor = new Scanner(System.in);

		System.out.println("Digite a operação desejada \n1 - Consultar o preço dos produtos em leitores \n0 - Sair");
		int op = leitor.nextInt();

		while (op != 0) {

			switch (op) {
			case 1:
				LeitorCodigoBarraService leitorCodigoBarra = new LeitorCodigoBarraService();

				System.out.println("Digite o código de barra do produto:");
				long codigoBarra = leitor.nextLong();
				System.out.println(leitorCodigoBarra.preçoProduto(Long.toString(codigoBarra)));
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

		System.out.println("Digite a operação desejada: \n 1 - Iniciar o trabalho em um caixa \n"
														+ "2 - Deixar posto de trabalho \n"
														+ "0 - Sair \n");
		int op = leitor.nextInt();

		while (op != 0) {

			switch (op) {
			case 1:
				iniciarTrabalhoCaixa();
				break;

			default:
				break;
			}

			System.out.println("Digite a operação desejada: \n 1 - Iniciar o trabalho em um caixa \n"
					+ "2 - Deixar posto de trabalho \n"
					+ "0 - Sair \n");
			op = leitor.nextInt();
		}

	}

	private static void gerente() {
		Scanner leitor = new Scanner(System.in);

		System.out.println("Digite a operação desejada \n1 - Cadastrar caixa\n"
													   +"2 - Cadastrar funcionario\n"
													   +"3 - Cadastrar Produtos\n"
													   +"4 - Relatorio Vendas\n"
													   +"5 - Relatorio Estoque\n"
													   + "0 - Sair");
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
			default:
				break;
			}

			System.out.println("Digite a operação desejada \n1 - Cadastrar caixa\n"
					   +"2 - Cadastrar funcionario\n"
					   +"3 - Cadastrar Produtos\n"
					   +"4 - Relatorio Vendas\n"
					   +"5 - Relatorio Estoque\n"
					   + "0 - Sair");
			op = leitor.nextInt();
		}

	}
	


	private static void iniciarTrabalhoCaixa() {
		Scanner leitor = new Scanner(System.in);

		System.out.println("Digite a operação desejada: \n 1 - Visualizar caixas livres "
				+ "\n2 - Escolher Caixa para trabalhar"
				+ "\n0 - Sair");
		int op = leitor.nextInt();
		
		while (op != 0) {

			switch (op) {
			case 1:
				List<Caixa> caixaLivres = new ArrayList<>();
				caixaLivres = caixaService.getCaixasLivres();
				
				if(caixaLivres.size() > 1) {
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
				
				System.out.println("Digite o caixa que deseja trabalhar:");
				int codCaixa = leitor.nextInt();
				Caixa caixa = caixaService.bucarCaixa(codCaixa);
				
				if(caixaService.iniciarCaixa(caixa, pessoa))
					operarCaixaFuncionario(caixa);
				else 
					System.out.println("Não foi possível iniciar o caixa. Caixa");
			default:
				break;
			}

			System.out.println("Digite a operação desejada: \n 1 - Visualizar caixas livres "
					+ "\n2 - Escolher Caixa para trabalhar"
					+ "\n0 - Sair");
			op = leitor.nextInt();
		}
	}

	private static void operarCaixaFuncionario(Caixa caixa) {
		Scanner leitor = new Scanner(System.in);

		System.out.println("Digite a operação desejada: \n1 - Iniciar venda "
				+ "\n0 - Sair");
		int op = leitor.nextInt();
		
		while (op != 0) {
			switch (op) {
			case 1:
				fazervenda(caixa);
				break;

			default:
				break;
			}
		}
		
	}

	private static void fazervenda(Caixa caixa) {
		EstoqueService estoqueService = new EstoqueService();
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
		data.setMes(leitor.nextInt());
		
		vendaService.getVenda().setDataVenda(data);

		System.out.println("Digite a operação desejada \n1 - Iniciar adição de produtos "
				+ "\n0 - Terminar venda");
		int op = leitor.nextInt();
		
		while(op != 0) {
			
			System.out.println("Digite o código do produto: ");
			int codProduto = leitor.nextInt();
			
			if(estoqueService.verificarCodigo(codProduto)) {
				
				Produto produto = estoqueService.getProduto(codProduto);
				
				if(produto instanceof ProdutoUnidade) {
					
					System.out.println("Digite quantas unidades: ");
					int qntProduto = leitor.nextInt();
					
					if(estoqueService.verificarQuantidade(codProduto, qntProduto))
						vendaService.adicionarProdutoUnidade(codProduto, qntProduto);
					else
						System.out.println("Quantidade indisponível!");										
				} else {
					System.out.println("Digite o quilo: ");
					double kgProduto = leitor.nextDouble();
				}
				
			} else {
				System.out.println("Código inválido!");
			}
			
			System.out.println("Digite a operação desejada \n1 - iniciar produto "
					+ "\n0 - Terminar venda");
			op = leitor.nextInt();
		}
		vendaService.somarTotalVenda(vendaService.getVenda());
		Double valorPagamento = vendaService.getVenda().getTotal();
		
		System.out.println("Pagamente: \n");
		System.out.println("Digite a opção de pagamento:\n1 - Cartão de crédito\n2 - Dinheiro:");
		int opPagamento = leitor.nextInt();
		
		if(opPagamento == 1) {
			System.out.println("Digite o numero do cartao");
			String numeroCartao = leitor.nextLine();
			
			PagamentoCartao pagamento = new PagamentoCartao();
			pagamento.setValorPagamento(vendaService.getVenda().getTotal());
			pagamento.setNumeroCartao(numeroCartao);
			vendaService.getVenda().setPagamento(pagamento);
			
		}else {
			System.out.println("Digite o valor pago");
			Double pagamento = leitor.nextDouble();
			PagamentoDinheiro pagamentoDinheiro = new PagamentoDinheiro();
			pagamentoDinheiro.setValorPagamento(vendaService.getVenda().getTotal());
			
			pagamentoDinheiro.setValorPago(pagamento);
			vendaService.getVenda().setPagamento(pagamentoDinheiro);
			System.out.println("Troco: " + pagamentoDinheiro.getTroco());
		}
		
		Venda venda = vendaService.finalizarVenda(caixa);
		vendasService.adicionarVenda(venda);
		
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
		System.out.println("Digite a medida do produto:\n 1 - Quilos\n 2- Unidades");
		int op = leitor.nextInt();
		leitor.nextLine();
		
		System.out.println("Digite o nome do produto: ");
		String nome =leitor.nextLine();
		
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
		
		
		if(op == 1) {
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
			
		}else {
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
		RelatorioVendasService relatorio = new RelatorioVendasService();
		String relatorioVendas = relatorio.relatorioVendas();
		relatorio.setCaixas((ArrayList<Caixa>) caixaService.getCaixas());
		
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
		
		RelatorioEstoqueService relatorio = new RelatorioEstoqueService();
		relatorio.setEstoqueService(estoqueService);
		relatorio.setCaixaService(caixaService);
		
		String relatorioEstoque = relatorio.gerarRelatorio(data);
		
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
		fun1.setCpf(314134124);
		fun1.setSalario(1123);
		fun1.setCodigo(2);
		
		Funcionario fun3 = new Funcionario();
		fun2.setNome("Lazaro");
		fun1.setCpf(314134124);
		fun1.setSalario(1123);
		fun1.setCodigo(3);
	}
}
