package servicos;

import java.util.ArrayList;
import java.util.Iterator;

import modelos.Pagamento;
import modelos.Produto;
import modelos.Venda;

public class CaixaRegistradora {

	public CaixaRegistradora() {
		
	}

	public void adicionarProduto(Produto produto, Venda venda) {
		venda.getItens().add(produto);		
	}
	
	public Double somarTotalCompra(Venda venda) {
		Double resultado = 0.0;
		ArrayList<Produto> itens = venda.getItens();
		for(int i =0; i < itens.size(); i++) {
			resultado += itens.get(i).getPreco();
		}
		return resultado;
	}
	
	public void receberPagamento(Pagamento pagamento, Venda venda) {
		venda.setPagamento(pagamento);
	}
	
	public String gerarRelatorioCompra(Venda venda) {
		ArrayList<Produto> itens = venda.getItens();
		String relatorioVenda = "";
		relatorioVenda += "\t\t\t===== RELATORIO DE VENDAS =====\t\t\t\n";
		relatorioVenda += "Vendedor: " + venda.getRespVenda().getNome() + "\n";
		relatorioVenda += "Produtos: \n";
		
		Iterator it = itens.iterator();
		Produto prod;
		
		while(it.hasNext()) {
			prod = (Produto) it.next();
			relatorioVenda += "-----------------------------------------\n";
			relatorioVenda += "Item: " + prod.getNome() + " ";
			relatorioVenda += "Preco: " + prod.getPreco() + "\n";
			relatorioVenda += "-----------------------------------------";
		}
		
		relatorioVenda += "Total: " + venda.getTotal() + "\n";
		return relatorioVenda;
	}
}
