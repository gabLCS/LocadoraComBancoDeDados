package gabrielLeonardoCunhaSantos.LocadoraComBancoDeDados;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DAOalugueis {
	public void inserir(Aluguel a, double preco) throws VeiculoNaoCadastrado {
		  
		  try {
			DAOVeiculo v = new DAOVeiculo();
			v.pesquisar(a.getPlaca());
			Connection con = Conexao.getConexao();
		    Statement st = con.createStatement();
		    Date dt = a.getData();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String Inicio = sdf.format(dt);

		    
	        String insert = "insert into Aluguel (Placa, Inicio, Fim, Periodo, Preco, Cpf) values (\'" + a.getPlaca() +
	        		"\' ,\'" + Inicio + "\',null," + a.getDias() + ", "+preco+", "+ a.getCpf() +")";
	        System.out.println(insert);
	        st.executeUpdate(insert);
        	st.close();
        	return;
		  } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
			}
		  
	
	}

	
public void RegistrarDevolucao(int cpf, Date fim, String placa)   {
		DAOcliente x = new DAOcliente();
		try {
		x.pesquisar(cpf);
		
		Connection con = Conexao.getConexao();
	    Statement st = con.createStatement();
	    Date dt = fim;
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String dev = sdf.format(dt);
	    
        String update = "update aluguel set fim = \'"+ dev + "\' where cpf = \'"+cpf + "\' and placa = \'"+placa +"\'";
        System.out.println(update);
        st.execute(update);
    	st.close();
    	return;
		}catch (Exception e2) {
		e2.printStackTrace(); 
	  }
}
	
	public ArrayList<Aluguel> recuperarAlugueis(int cpf) throws Exception {
		Connection con = null;
		Statement st = null;
		ArrayList<Aluguel> alugueis = new ArrayList<Aluguel>();
		con = Conexao.getConexao();
		st = con.createStatement();
	    String sql = "select * from aluguel where cpf = " + cpf;
	    System.out.println(sql);
	    try {
	    ResultSet rs = st.executeQuery(sql);
	    while (rs.next())
	    {
	       int n = rs.getInt("cpf");
	    Date inicio = rs.getDate("Inicio");
       	int periodo = rs.getInt("Periodo");
       	String placa = rs.getString("Placa");
	     Aluguel a = new Aluguel(placa, inicio, periodo);
	       alugueis.add(a);
	    }
        st.close();
        return alugueis; 
	    }catch(Exception e) {
	    	throw new Exception("Aluguel inexistente");
	    }
	}
	
	public ArrayList<Aluguel> recuperarFaturamento(int tipo, Date inicio, Date fim) {
		if(!(tipo>=0 && tipo <=4)) {
//			throw new Exception();
		}
		DAOVeiculo v1 = new DAOVeiculo();
		Veiculo v;
		Aluguel f;
		Connection con = null;
		Statement st = null;
		ArrayList<Aluguel> fat = new ArrayList<Aluguel>();
		try {
		con = Conexao.getConexao();
		st = con.createStatement();
		String sql = "select * from aluguel";
	    System.out.println(sql);
	    ResultSet rs = st.executeQuery(sql);
	    while (rs.next())
	    {
	    	Date inicioDB = rs.getDate("Inicio");
        	Date fimDB = rs.getDate("Fim");
        	int periodo = rs.getInt("Periodo");
        	String placa = rs.getString("Placa");
        	v = v1.pesquisar(placa);
        	f = new Aluguel(placa, inicioDB,periodo);
	       
	       if(inicioDB.getDate() >= inicio.getDate() && fimDB.getDate() <= fim.getDate()) {
	    	   
	    	  if(tipo == 0) {
	    		  fat.add(f);
	    	  }else if(tipo == 1 && v instanceof Moto) {
	    		  fat.add(f);
	    		}else if(tipo == 2 && v instanceof Carro){ 
	    		  fat.add(f);
	    		}else if(tipo == 3 && v instanceof Caminhao) {
	    		  fat.add(f);
	    		}else if(tipo == 4 && v instanceof Onibus) {
	    		  fat.add(f);
	    		}
	       }
	       
	    }
	    st.close();
		}catch(Exception e){
	    	e.printStackTrace();
	    }
	    	
	   
	    return fat; 
	}
	
	public void removerAlugueis() {
		Connection con = null;
		Statement st = null;
		Cliente c = null;
		try {
			con = Conexao.getConexao();
		    st = con.createStatement();
	        String insert = "delete from Aluguel";
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
