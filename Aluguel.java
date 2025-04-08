package gabrielLeonardoCunhaSantos.LocadoraComBancoDeDados;

import java.util.Date;

public class Aluguel {
	
	private String placa;
	private Date data;
	private int dias;
	private int cpf;
	private Date devolucao;
	
	Aluguel(String Placa, Date inicio, int periodo){
		placa = Placa;
		data = inicio;
		dias = periodo;
	}
	
	public String getPlaca() {
		return placa;
	}
	
	public Date getData() {
		return data;
	}
	public Date getDevolucao() {
		return devolucao;
	}
	public int getDias() {
		return dias;
	}
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int c) {
		cpf = c;
	}
	public void setDevolucao(Date x) {
		devolucao = x;
	}


}
