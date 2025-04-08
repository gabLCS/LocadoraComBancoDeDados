package gabrielLeonardoCunhaSantos.LocadoraComBancoDeDados;

@SuppressWarnings("serial")
public class ClienteNaoCadastrado extends Exception {
	public ClienteNaoCadastrado() {
		super("veiculo ja cadastrado");
	}
}
