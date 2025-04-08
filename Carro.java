package gabrielLeonardoCunhaSantos.LocadoraComBancoDeDados;

public class Carro extends Veiculo {
	
	int tipoCarro;
	double taxa = 0.03;
	
	public Carro(String m, String mod, int a, double vDB, double vDD, String p, int tipo){
		super(m, mod, a, vDB, vDD, p);
		tipoCarro = tipo;
	}
	
	double getTaxa() {
		return taxa;
	}

	int getTipo() {
		return tipoCarro;
	}



	

	
	

	
}
