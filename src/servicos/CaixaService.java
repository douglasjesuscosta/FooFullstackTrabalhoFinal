package servicos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelos.Caixa;
import modelos.Pessoa;

public class CaixaService {

	private static CaixaService caixaService;
	private List<Caixa> caixas;	
	
	private CaixaService() {
		caixas = new ArrayList<>();
	}
	
	public static CaixaService getInstanciaCaixaService() {
		if(caixaService == null)
			return caixaService = new CaixaService();
		else 
			return caixaService;
	}
	
	public List<Caixa> getCaixas() {
		return caixas;
	}

	public void setCaixas(List<Caixa> caixas) {
		this.caixas = caixas;
	}

	public boolean verificarDisponibilidadeCaixa(int i) {
		
		return caixas.get(i).getPessoa() == null;
	}
	
	public void adicionarCaixa(Caixa caixa) {
		this.getCaixasLivres().add(caixa);
		this.getCaixas().add(caixa);
	}
	
	public Caixa bucarCaixa(int cod) {
		Iterator<Caixa> it = caixas.iterator();
		Caixa caixa;
		Caixa caixaEnc = null;
		
		while(it.hasNext()) {
			caixa = it.next();
			
			if(caixa.getCod() == cod) {
				caixaEnc = caixa;
			}
		}
		return caixaEnc;
	}
	
	public List<Caixa> getCaixasLivres() {
		List<Caixa> caixasLivres = new ArrayList<>();
		
		for (Caixa caixa : caixas) {
			if(caixa.getPessoa() == null)
				caixasLivres.add(caixa);
		}
		
		return caixasLivres;
	}

	public boolean iniciarCaixa(Caixa caixaDesejado, Pessoa pessoa) {
		boolean iniciou = false;
		
		for (Caixa caixa : caixas) {
			if(caixa.getCod() == caixaDesejado.getCod()) {
				if(caixa.getPessoa() != null) {
					iniciou = false;
				} else {
					
					caixa.setPessoa(pessoa);
					iniciou = true;
				}
			}	
		}	
		
		return iniciou;
	}
}
