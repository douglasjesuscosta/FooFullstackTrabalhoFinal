package modelos;

public class Gerente extends Pessoa{
	
	public void adicionaProduto(Produto produto) {
		Estoque estoque = Estoque.getInstancia();
		estoque.adicionaProdutoEstoque(produto);
	}
}
