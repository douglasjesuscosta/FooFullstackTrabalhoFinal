package servicos;

import java.util.ArrayList;
import java.util.List;

import modelos.Caixa;
import modelos.Pessoa;

public class CaixaService {

	private List<Caixa> caixas = new ArrayList<>();
	
	public boolean verificarDisponibilidadeCaixa(int i) {
		
		return caixas.get(i).getPessoa() == null;
	}
	
	public List<Caixa> getCaixasLivres() {
		List<Caixa> caixasLivres = new ArrayList<>();
		
		for (Caixa caixa : caixas) {
			if(caixa.getPessoa() == null)
				caixasLivres.add(caixa);
		}
		
		return caixasLivres;
	}

	public boolean iniciarCaixa(int codCaixa, int codigo) {
		boolean iniciou = false;
		
		for (Caixa caixa : caixas) {
			if(caixa.getPessoa().getCodigo() == codigo)
				iniciou = false;
		}
		
		for (Caixa caixa : caixas) {
			if(caixa.getCod() == codCaixa) {
				if(caixa.getPessoa() != null) {
					iniciou = false;
				} else {
					iniciou = true;
				}
			}	
		}	
		
		return iniciou;
	}
}
