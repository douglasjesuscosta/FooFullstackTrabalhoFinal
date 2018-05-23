package servicos;

import modelos.Produto;
import modelos.ProdutoUnidade;
import modelos.Venda;

public class VendaService {

	private Venda venda;
	private EstoqueService estoqueService = new EstoqueService();
	
	public VendaService() {
		venda = new Venda();
	}

	public void adicionarProdutoUnidade(int codProduto, int qntProduto) {
		ProdutoUnidade produtoUnidade = (ProdutoUnidade) estoqueService.getProduto(codProduto);
		venda.adicionaProduto(produtoUnidade); 
	}
	
	
}
