package modelos;

import java.util.ArrayList;
import java.util.List;

public class Estoque {

	private static Estoque estoque;
	private List<Produto> produtos = new ArrayList<>();
	
	private Estoque() {
		estoque = new Estoque();
	}
	
	public void adicionaProdutoEstoque(Produto produto) {
		produtos.add(produto);
	}
	
	public static Estoque getInstancia() {
		if(estoque == null) 
			estoque = new Estoque();
		
		return estoque;
	}
}
