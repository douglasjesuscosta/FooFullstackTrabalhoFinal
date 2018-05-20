package servicos;

import modelos.Estoque;
import modelos.Produto;

public class LeitorCodigoBarra {

	public String preçoProduto(String codigoBarra) {
		Estoque estoque = Estoque.getInstancia();
		
		Produto produto = estoque.getProdutoByCodigoBarra(codigoBarra);
		
		if(produto == null)
			return "Produto não encontrado!";
		else
			return Double.toString(produto.getPreco());
	}
}
