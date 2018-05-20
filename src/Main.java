import java.util.Scanner;

import servicos.LeitorCodigoBarra;

public class Main {

	public static void main(String[] args) {
		
		Scanner leitor = new Scanner(System.in);
		
		System.out.println("Digite a sua função \n1 - Gerente \n2 - Funcionário \n3 - Cliente \n0 - Sair");
		int op = leitor.nextInt();
		
		while(op != 0) {
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
				System.out.println("Código inválido!");;
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
				LeitorCodigoBarra leitorCodigoBarra = new LeitorCodigoBarra();
				
				System.out.println("Digite o código de barra do produto:");
				long codigoBarra = leitor.nextLong();
				System.out.println(leitorCodigoBarra.preçoProduto(Long.toString(codigoBarra)));
				break;

			default:
				break;
			}
			
			System.out.println("Digite a operação desejada \n1 - consultem o preço dos produtos em leitores \n0 - Sair");
			op = leitor.nextInt();
		}
		
		leitor.close();
	}

	private static void funcionario() {
		// TODO Auto-generated method stub
		
	}

	private static void gerente() {
		// TODO Auto-generated method stub
		
	}
}
