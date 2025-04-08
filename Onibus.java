package gabrielLeonardoCunhaSantos.LocadoraComBancoDeDados;

public class Onibus extends Veiculo{
	int capacidadePassageiros;
	double taxa = 0.20;
	
	Onibus(String m, String mod, int a, double vDB, double vDD, String p, int c){
		super(m, mod, a, vDB, vDD, p);
		capacidadePassageiros = c;
	}
	
	double getTaxa() {
		return taxa;
	}
	
	int getCapacidadePassageiros() {
		return capacidadePassageiros;
	}








}
