package gabrielLeonardoCunhaSantos.LocadoraComBancoDeDados;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DAOVeiculo {
	
	public void inserir(Veiculo v) throws VeiculoJaCadastrado, SQLException {
		
		try {
		pesquisar(v.getPlaca());
		throw new VeiculoJaCadastrado(); 
		} catch (Exception e1) {
		  
		try {
		Connection con = Conexao.getConexao();
		Statement st = con.createStatement();
		String tipo = null;
		int Cilindrada;
		int TipoDeCarro;
		int CapaciadeDePassageiros;
		double CapacidadeDeCarga;
	    String insert = "insert into Veiculo(Placa, Marca, Modelo, Ano, ValorDoBem, ValorDaDiaria, Tipo,  Cilindrada, TipoDeCarro, capacidadeDePassageiros, capacidadeDeCarga, Disponivel) values"
	        		+ " (" +"\'" + v.getPlaca() + "\',"+ "\'"+v.getMarca()+"\'"  + ",\'"+v.getModelo()+"\'" + "," + v.getAnoDeFabricacao()+
	        		"," + v.getValorAvaliado() + "," + v.getValorDiaria();
	    if(v instanceof Moto) {
	    tipo = "M";
	    Cilindrada = ((Moto) v).getCilindrada();
	    insert = insert + ",\'" + tipo + "\'," + Cilindrada + ", null, null, null, \'S\')";
	    }else if(v instanceof Carro) {
	    tipo = "C";
	    TipoDeCarro = ((Carro) v).getTipo();
	    insert = insert + ",\'" + tipo + "\', null," + TipoDeCarro + ", null , null, \'S\')";
	    }else if(v instanceof Onibus) {
	    tipo = "O";
	    CapaciadeDePassageiros = ((Onibus) v).capacidadePassageiros;
	    insert = insert + ",\'" + tipo + "\',null,null," + CapaciadeDePassageiros + ",null, \'S\')";
	    }else if(v instanceof Caminhao) {
	    tipo = "CM";
	    CapacidadeDeCarga = ((Caminhao) v).getCapacidadeCarga();
	    insert = insert + ",\'" + tipo + "\', null, null, null," + CapacidadeDeCarga +", \'S\')";
	    }
	        
	        System.out.println(insert);
	        st.executeUpdate(insert);
        	st.close();
        	return;
        	
		  } catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SQLException();
		  }
		}
	}

	public Veiculo pesquisar(String string) throws VeiculoNaoCadastrado {
		Connection con = null;
		Statement st = null;
		Veiculo p = null;
		try {
			con = Conexao.getConexao();
		    st = con.createStatement();
	        String sql = "select * from veiculo where Placa = \'" + string +"\'";
	        System.out.println(sql);
	        ResultSet rs = st.executeQuery(sql);
	        if (rs.next())
	        {
	        	String placa = rs.getString("Placa");
	        	String marca = rs.getString("Marca");
	        	String modelo = rs.getString("Modelo");
	        	int ano = rs.getInt("Ano");
	        	double ValorDoBem = rs.getDouble("ValorDoBem");
	        	double ValorDaDiaria = rs.getDouble("ValorDaDiaria");
	        	int TipoCarro = rs.getInt("TipoDeCarro");
	        	int Cilindrada = rs.getInt("Cilindrada");
	        	int CapacidadePassageiros = rs.getInt("capacidadeDePassageiros");
	        	double CapacidadeCarga = rs.getInt("capacidadeDeCarga");
	        	String Tipo = rs.getString("Tipo");
	        	
	        	if(Tipo.equals("C")) {
	        		p = new Carro(marca, modelo, ano, ValorDoBem, ValorDaDiaria, placa, TipoCarro);
	        	}else if(Tipo.equals("M")) {
	        		p = new Moto(marca, modelo, ano, ValorDoBem, ValorDaDiaria, placa, Cilindrada);
	        	}else if(Tipo.equals("O")) {
	        		p = new Onibus(marca, modelo, ano, ValorDoBem, ValorDaDiaria, placa, CapacidadePassageiros);
	        	}else if(Tipo.equals("CM")) {
	        		p = new Caminhao(marca, modelo, ano, ValorDoBem, ValorDaDiaria, placa, CapacidadeCarga);
	        	}
	        	//Placa, Modelo, Ano, ValorDoBem, ValorDaDiaria, Tipo,  cilindrada, TipoDeCarro, capacidadeDePassageiros, capacidadeDeCarga
	         	
	        } else  {
	    	throw new VeiculoNaoCadastrado();
	        } 
        	st.close();
        	return p;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new VeiculoNaoCadastrado();
		}
	}
	
	public void pesquisarVeiculos(String tipo, int var, ArrayList<Veiculo> v) {
		Connection con = null;
		Statement st = null;
		Veiculo x = null;
		String sql;
		try {
		con = Conexao.getConexao();
		st = con.createStatement();
	    sql = "select * from Veiculo where Tipo = \'" + tipo + "\'" ;
		    
	    System.out.println(sql);
	        
	    ResultSet rs = st.executeQuery(sql);
	    
	    while (rs.next())
	    {
	        	
	    	String placa = rs.getString("Placa");
        	String marca = rs.getString("Marca");
        	String modelo = rs.getString("Modelo");
        	int ano = rs.getInt("Ano");
        	double ValorDoBem = rs.getDouble("ValorDoBem");
        	double ValorDaDiaria = rs.getDouble("ValorDaDiaria");
        	int TipoCarro = rs.getInt("TipoDeCarro");
        	int Cilindrada = rs.getInt("Cilindrada");
        	int CapacidadePassageiros = rs.getInt("capacidadeDePassageiros");
        	double CapacidadeCarga = rs.getInt("capacidadeDeCarga");
        	String Tipo = rs.getString("Tipo");
	        	
	    if(Tipo.equals("C") && TipoCarro == var) {
	      x = new Carro(marca, modelo, ano, ValorDoBem, ValorDaDiaria, placa, TipoCarro);
	      v.add(x);
	    }else if(Tipo.equals("M") && Cilindrada >= var) {
	      x = new Moto(marca, modelo, ano, ValorDoBem, ValorDaDiaria, placa, Cilindrada);
	      v.add(x);  		
	    }else if(Tipo.equals("O") && CapacidadePassageiros >= var) {
	      x = new Onibus(marca, modelo, ano, ValorDoBem, ValorDaDiaria, placa, CapacidadePassageiros);
	      v.add(x);
	    }else if(Tipo.equals("CM") && CapacidadeCarga >= var) {
	      x = new Caminhao(marca, modelo, ano, ValorDoBem, ValorDaDiaria, placa, CapacidadeCarga);
	      v.add(x);
	        	}
	         	 
	    }
        st.close();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	public boolean EstaDisponivel(String string) throws Exception {
		Connection con = null;
		Statement st = null;
		Veiculo p = null;
		boolean x = false;
		String disponibilidade;
		try {
			con = Conexao.getConexao();
		    st = con.createStatement();
	        String sql = "select * from veiculo where Placa = \'" + string +"\'";
	        System.out.println(sql);
	        ResultSet rs = st.executeQuery(sql);
	        if (rs.next())
	        {
	        	disponibilidade = rs.getString("Disponivel");
	        	if(disponibilidade.contains("S")) {
	         	x= true;
	         	}
	        }
        	st.close();
        	return x;
        	
		} catch (Exception e) {
    		throw new Exception("veiculo inexistente");
		}
		
	}
	
	
	public void RegistrarDevolucao(String placa) throws VeiculoNaoAlugado, VeiculoNaoCadastrado {
		
		try {
		if(EstaDisponivel(placa)) {
			throw new VeiculoNaoAlugado();
		}
		
		Veiculo v = pesquisar(placa);
		Connection con = Conexao.getConexao();
	    Statement st = con.createStatement();
	    
        String update = "update veiculo set Disponivel = \'S\' where Placa = \'"+placa + "\'";
        System.out.println(update);
        st.execute(update);
    	st.close();
    	return;
		}catch (Exception e2) {
		e2.printStackTrace(); 
	  }

}
	public void RegistrarAluguel(String placa) throws VeiculoNaoCadastrado, VeiculoAlugado {
		try {
		if(EstaDisponivel(placa) == true) {
		Veiculo v = pesquisar(placa);
		Connection con = Conexao.getConexao();
	    Statement st = con.createStatement();
	    
        String update = "update veiculo set Disponivel = \'N\' where Placa = \'"+placa + "\'";
        System.out.println(update);
        st.execute(update);
    	st.close();
    	return;
		}else {
			throw new VeiculoAlugado();
		}
	} catch (Exception e2) {
		e2.printStackTrace(); 
		throw new VeiculoAlugado();
	  }

}
	

	
	
	public void AtualizarDadosdoBanco(int opc, double var, String tipo) {
		Connection con = null;
		Statement st = null;
		String sql;
		
		try {
		con = Conexao.getConexao();
		st = con.createStatement();
		if(tipo !=null) {
			if(opc == 1) {
				sql = "update veiculo set ValorDoBem = ValorDoBem -(ValorDoBem * "+ var + ")  where Tipo = \'" + tipo + "\'" ;
			}else{
				sql = "update veiculo set ValorDaDiaria = ValorDaDiaria *(1+"+ var + ") where Tipo = \'" + tipo + "\'" ;
			}
			//update veiculo set Modelo = 'fiesta' where placa = 'abc-1234'
	    
		}else {
			if(opc == 1) {
				sql = "update veiculo set ValorDoBem = ValorDoBem -(ValorDoBem * "+ var + ")"  ;
			}else{
				sql = "update veiculo set ValorDaDiaria = ValorDaDiaria *(1+"+ var + ")" ;
			}
		}
	    System.out.println(sql);
	        
	    st.execute(sql) ;
        st.close();
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	

	
	public void removerTudo() {
		Connection con = null;
		Statement st = null;
		Veiculo p = null;
		try {
			con = Conexao.getConexao();
		    st = con.createStatement();
	        String insert = "delete from veiculo";
	        System.out.println(insert);
	        st.executeUpdate(insert);
        	st.close();
        	return;
		  } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
	}

}
