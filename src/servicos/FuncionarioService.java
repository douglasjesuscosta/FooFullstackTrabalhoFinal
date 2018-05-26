package servicos;

import java.util.ArrayList;
import java.util.Iterator;

import modelos.Pessoa;

public class FuncionarioService {
	
	private ArrayList<Pessoa> funcionarios;
	
	public FuncionarioService() {
		funcionarios = new ArrayList<Pessoa>();
	}
	
	public ArrayList<Pessoa> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(ArrayList<Pessoa> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public void adicionarFuncionario(Pessoa pessoa) {
		funcionarios.add(pessoa);
	}
	
	public Pessoa bucarFuncionario(int cod) {
		
		Iterator<Pessoa> it = funcionarios.iterator();
		Pessoa pessoa;
		Pessoa pessoaEnc = null;
		
		while(it.hasNext()) {
			pessoa = it.next();
			
			if(pessoa.getCodigo() == cod) {
				pessoaEnc = pessoa;
			}
		}
		return pessoaEnc;
	}

}
