package gabrielLeonardoCunhaSantos.LocadoraComBancoDeDados;

import java.util.ArrayList;
import java.util.Date;

public class Cliente {
	String nome;
	int cpf;
	DAOalugueis alugueis = new DAOalugueis();
	private ArrayList<Aluguel> ArrayDeAlugueis = new ArrayList<Aluguel>();
	
	Cliente(int num, String nom) {
		nome = nom;
		cpf = num;
	}
	
	public int getCpf() {
		return cpf;
	}
	public String getNome() {
		return nome;
	}
	public ArrayList<Aluguel> getAlugueis() throws Exception{
		ArrayDeAlugueis = alugueis.recuperarAlugueis(cpf);
		return ArrayDeAlugueis;
	}
	
	public void RegistrarAluguel(String placa, Date inicio, int dias, int cpf, double preco) throws VeiculoNaoCadastrado {
		Aluguel a = new Aluguel(placa, inicio, dias);
		a.setCpf(cpf);
		alugueis.inserir(a, preco);
	}
	public void RegistrarDevolucao(Date fim, int cpf, String placa) {
		alugueis.RegistrarDevolucao(cpf, fim, placa);
	}
	
	
	
}
