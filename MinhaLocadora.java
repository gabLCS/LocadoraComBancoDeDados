package gabrielLeonardoCunhaSantos.LocadoraComBancoDeDados;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


public class MinhaLocadora implements Locadora{
	
	Date hoje = new Date();
	DAOalugueis faturamento = new DAOalugueis();
	DAOVeiculo veiculos = new DAOVeiculo();
	DAOcliente clientes = new DAOcliente();
	
	 
	

	public void inserir(Veiculo v) throws VeiculoJaCadastrado, SQLException {
			veiculos.inserir(v);
	}
	
	public void removerTudo() {
		veiculos.removerTudo();
		clientes.removerTudo();
		faturamento.removerAlugueis();
	}
	
	public Veiculo pesquisar(String placa) throws VeiculoNaoCadastrado{ 
		Veiculo v;
		v = veiculos.pesquisar(placa);
		return v;
	}

	public void inserir(Cliente c) throws ClienteJaCadastrado, SQLException {
			clientes.inserir(c);
	}
	
	protected Cliente pesquisarCliente(int cpf) throws ClienteNaoCadastrado {
		Cliente c;
			c = clientes.pesquisar(cpf);
			return c;
	}

    public ArrayList<Veiculo> pesquisarMoto(int cilindrada){
    	if(cilindrada >0) {
    	ArrayList<Veiculo> aux = new ArrayList<Veiculo>();
			veiculos.pesquisarVeiculos("M", cilindrada, aux);
			return aux;
	      }
    	return null;
    }

    public ArrayList<Veiculo> pesquisarCarro(int tipoCarro){
    	if(tipoCarro>0 && tipoCarro<=3) {
    	ArrayList<Veiculo> aux = new ArrayList<Veiculo>();
    	veiculos.pesquisarVeiculos("C", tipoCarro, aux);
    	return aux;
	      }
	    return null;
    }

    public  ArrayList<Veiculo> pesquisarCaminhao(int carga){
    	if(carga>0) {
    	ArrayList<Veiculo> aux = new ArrayList<Veiculo>();
    	veiculos.pesquisarVeiculos("CM", carga, aux);
    	return aux;
    	}
	    return null;
    }

    public  ArrayList<Veiculo> pesquisarOnibus(int passageiros){
    	if(passageiros >0) {
    	ArrayList<Veiculo> aux = new ArrayList<Veiculo>();
		veiculos.pesquisarVeiculos("O", passageiros, aux);
    	return aux;
    	}
	    return null;
    }
    
    

    public double calcularAluguel(String placa, int dias) throws VeiculoNaoCadastrado {
    	double seguro;
    	Veiculo c;
			c = pesquisar(placa);
			System.out.println(c.getValorDiaria());
			if(c instanceof Moto) {
				seguro = (c.getValorAvaliado() * 0.11)/365;
				return (c.getValorDiaria() + seguro)*dias; 
			}else if(c instanceof Carro) {
				seguro = (c.getValorAvaliado() *  0.03)/365;
				return (c.getValorDiaria() + seguro)*dias;
			}else if(c instanceof Onibus) {
				seguro = (c.getValorAvaliado() *  0.20)/365;
				return (c.getValorDiaria() + seguro)*dias;
			}else if(c instanceof Caminhao) {
				seguro = (c.getValorAvaliado() * 0.08)/365;
				return (c.getValorDiaria() + seguro)*dias;
			}
			return 0;
    }

    public void registrarAluguel(String placa, Date inicio, int dias, int cpf) throws VeiculoNaoCadastrado, VeiculoAlugado, ClienteNaoCadastrado {
    	Veiculo v;
    	Cliente c = clientes.pesquisar(cpf);
    	v = pesquisar(placa);
    	double aux = 0;
    	aux = calcularAluguel(placa, dias);
    	veiculos.RegistrarAluguel(placa);
    	c.RegistrarAluguel(placa,inicio, dias, cpf, aux);

    }
    
    public void registrarDevolucao(String placa, Date data, int cpf) throws VeiculoNaoCadastrado, VeiculoNaoAlugado, ClienteNaoCadastrado {
    	Cliente c = clientes.pesquisar(cpf);
    	c.RegistrarDevolucao(data, cpf, placa);
    	veiculos.RegistrarDevolucao(placa);
    }
    

    public void depreciarVeiculos(int tipo, double taxaDepreciacao) {
	    			if(tipo == 0) {
	    				veiculos.AtualizarDadosdoBanco(1, taxaDepreciacao, null);
	    			}else if(tipo == 1) {
	    				veiculos.AtualizarDadosdoBanco(1, taxaDepreciacao, "M");
	    			}else if(tipo == 2){
	    				veiculos.AtualizarDadosdoBanco(1, taxaDepreciacao, "C");
	    			}else if(tipo == 3) {
	    				veiculos.AtualizarDadosdoBanco(1, taxaDepreciacao, "CM");
	    			}else if(tipo == 4) {
	    				veiculos.AtualizarDadosdoBanco(1, taxaDepreciacao, "O");
	    			}
    	
    	
    }
    
    public  void aumentarDiaria(int tipo, double taxaAumento) {
    	if(taxaAumento >= 0) {
    			if(tipo == 0) {
    				veiculos.AtualizarDadosdoBanco(2, taxaAumento, null);
    			}else if(tipo == 1) {
    				veiculos.AtualizarDadosdoBanco(2, taxaAumento, "M");
    			}else if(tipo == 2){
    				veiculos.AtualizarDadosdoBanco(2, taxaAumento, "C");
    			}else if(tipo == 3) {
    				veiculos.AtualizarDadosdoBanco(2, taxaAumento, "CM");
    			}else if(tipo == 4) {
    				veiculos.AtualizarDadosdoBanco(2, taxaAumento, "O");
    			}
    			return;
    	}
    }
    
    public double faturamentoTotal(int tipo, Date inicio, Date fim) {
    	double aux = 0;
        	ArrayList<Aluguel> fat = new ArrayList<Aluguel>();
        	fat = faturamento.recuperarFaturamento(tipo, inicio, fim);
        	try {
        	for(Aluguel f: fat) {
        		aux= aux + calcularAluguel(f.getPlaca(), f.getDias());
        	}
        	}catch(Exception e){
        		e.printStackTrace();
        		
        	}
        	return aux;

    }
    
    public  int quantidadeTotalDeDiarias(int tipo, Date inicio, Date fim) {
    	int aux = 0;
    	try {
    	ArrayList<Aluguel> fat = new ArrayList<Aluguel>();
    	fat = faturamento.recuperarFaturamento(tipo, inicio, fim);
    	for(Aluguel f: fat) {
    		aux= aux + f.getDias();
    	}
    	return aux;
    	
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return aux;
    }

}

