import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelos.Caixa;
import modelos.Produto;
import modelos.ProdutoUnidade;
import servicos.CaixaService;
import servicos.EstoqueService;
import servicos.LeitorCodigoBarraService;
import servicos.VendaService;

public class Main {
	
	private static CaixaService caixaService = new CaixaService();

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

		System.out.println("Digite a operação desejada \n1 - consultem o preço dos produtos em leitores \n0 - Sair");
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

		System.out.println("Digite a operação desejada \\n1 - iniciar o trabalho em um caixa eletrônico \n");
		int op = leitor.nextInt();

		while (op != 0) {

			switch (op) {
			case 1:
				iniciarTrabalhoCaixa();
				break;

			default:
				break;
			}

			System.out
					.println("Digite a operação desejada \n1 - consultem o preço dos produtos em leitores \n0 - Sair");
			op = leitor.nextInt();
		}

	}

	private static void gerente() {
		// TODO Auto-generated method stub

	}
	


	private static void iniciarTrabalhoCaixa() {
		Scanner leitor = new Scanner(System.in);

		System.out.println("Digite a operação desejada \n1 - visualizar caixas livres "
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
				System.out.println("Digite o caixa que deseja trabalhar:");
				int codCaixa = leitor.nextInt();
				if(caixaService.iniciarCaixa(codCaixa, codFuncionario))
					operarCaixaFuncionario(codCaixa, codFuncionario);
				else 
					System.out.println();
			default:
				break;
			}

			System.out
					.println("Digite a operação desejada \n1 - consultem o preço dos produtos em leitores \n0 - Sair");
			op = leitor.nextInt();
		}
	}

	private static void operarCaixaFuncionario(int codCaixa, int codFuncionario) {
		Scanner leitor = new Scanner(System.in);

		System.out.println("Digite a operação desejada \n1 - iniciar venda "
				+ "\n0 - Sair");
		int op = leitor.nextInt();
		
		while (op != 0) {
			switch (op) {
			case 1:
				fazervenda(codCaixa, codFuncionario);
				break;

			default:
				break;
			}
		}
		
	}

	private static void fazervenda(int codCaixa, int codFuncionario) {
		EstoqueService estoqueService = new EstoqueService();
		VendaService vendaService = new VendaService();
		
		Scanner leitor = new Scanner(System.in);

		System.out.println("Digite a operação desejada \n1 - iniciar produto "
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
	}
}
