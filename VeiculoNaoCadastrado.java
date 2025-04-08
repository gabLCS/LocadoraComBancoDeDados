package gabrielLeonardoCunhaSantos.LocadoraComBancoDeDados;

@SuppressWarnings("serial")
public class VeiculoNaoCadastrado extends Exception {
	public VeiculoNaoCadastrado() {
		super("Veiculo nao cadastrado");
	}

}
