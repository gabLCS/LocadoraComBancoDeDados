package gabrielLeonardoCunhaSantos.LocadoraComBancoDeDados;

public class Caminhao extends Veiculo{
	double taxa = 0.08;
	double capacidadeCarga;
	
	Caminhao(String m, String mod, int a, double vDB, double vDD, String p, double c){
		super(m, mod, a, vDB, vDD, p);
		capacidadeCarga = c;
	}
	
	double getTaxa() {
		return taxa;
	}


	double getCapacidadeCarga() {
		return capacidadeCarga;
	}

}
