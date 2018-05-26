package servicos;

import modelos.Produto;

public class LeitorCodigoBarraService {

	public String preçoProduto(String codigoBarra) {
		EstoqueService estoqueService = EstoqueService.getInstanciaEstoqueService();
		
		Produto produto = estoqueService.getProdutoByCodigoBarra(codigoBarra);
		
		if(produto == null)
			return "Produto não encontrado!";
		else
			return Double.toString(produto.getPreco());
	}
}
