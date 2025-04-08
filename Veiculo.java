package gabrielLeonardoCunhaSantos.LocadoraComBancoDeDados;

public class Veiculo {
	
	private String marca;
	private String modelo;
	private String placa;
	private double valorDaDiaria;
	private double valorDoBem;
	private int ano;

	
	Veiculo(String m, String mod, int a, double vDB, double vDD, String p){
		marca = m;
		modelo = mod;
		ano = a;
		valorDoBem = vDB;
		valorDaDiaria = vDD;
		placa = p;
	}

	int getAnoDeFabricacao() {
		return ano;
	}
	
	String getMarca() {
		return marca;
	}
	

	String getModelo() {
		return modelo;
	}

	String getPlaca() {
		return placa;
	}


	double getValorDiaria() {
		return valorDaDiaria;
	}
	
	void setValorDiaria(double num) {
		valorDaDiaria = num;
	}


	double getValorAvaliado() {
		return valorDoBem;
	}


	void setValorAvaliado(double num) {
		valorDoBem = num;
	}

}
