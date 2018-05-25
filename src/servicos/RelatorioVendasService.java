package servicos;

import java.util.ArrayList;
import java.util.Iterator;

import modelos.Caixa;

public class RelatorioVendasService {
	private ArrayList<Caixa> caixas;
	
	public ArrayList<Caixa> getCaixas() {
		return caixas;
	}
	
	public void setCaixas(ArrayList<Caixa> caixas) {
		this.caixas = caixas;
	}

	public String relatorioVendas() {
		
		Iterator it = caixas.iterator();
		Caixa caixa;
		String relatorio = "";
		
		while(it.hasNext()) {
			caixa = (Caixa) it.next();
			relatorio += "==================Caixa " + caixa.getCod() + "==================\n";
			relatorio += caixa.gerarRelatorioVendas() + "\n";
		}
		
		return relatorio;
	}

}
