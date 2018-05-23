package servicos;

import modelos.Produto;

public class LeitorCodigoBarraService {

	public String preçoProduto(String codigoBarra) {
		EstoqueService estoqueService = new EstoqueService();
		
		Produto produto = estoqueService.getProdutoByCodigoBarra(codigoBarra);
		
		if(produto == null)
			return "Produto não encontrado!";
		else
			return Double.toString(produto.getPreco());
	}
}
