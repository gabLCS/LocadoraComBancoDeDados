package gabrielLeonardoCunhaSantos.LocadoraComBancoDeDados;

public class Moto extends Veiculo{
	int cilindrada;
	double taxa = 0.11;
	
	Moto(String m, String mod, int a, double vDB, double vDD, String p, int c){
		super(m, mod, a, vDB, vDD, p);
		cilindrada = c;
	}
	double getTaxa() {
		return taxa;
	}

	int getCilindrada() {
		return cilindrada;
	}



}
