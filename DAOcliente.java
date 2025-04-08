package gabrielLeonardoCunhaSantos.LocadoraComBancoDeDados;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOcliente {
	
	
	public void inserir(Cliente c) throws ClienteJaCadastrado, SQLException {
		try {
			Cliente c2 = pesquisar(c.getCpf());
			throw new ClienteJaCadastrado();
		} catch (Exception e1) {
		  
		  try {
			Connection con = Conexao.getConexao();
		    Statement st = con.createStatement();
	        String insert = "insert into cliente (cpf, nome) values (" + c.getCpf() +
	        		", \'" + c.getNome() + "\')";
	        System.out.println(insert);
	        st.executeUpdate(insert);
        	st.close();
        	return;
		  } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
			throw new SQLException();
		  }
		}
	}


	public Cliente pesquisar(int cpf) throws ClienteNaoCadastrado {
		Connection con = null;
		Statement st = null;
		Cliente c = null;
		try {
			con = Conexao.getConexao();
		    st = con.createStatement();
	        String sql = "select * from cliente where cpf = " + cpf;
	        System.out.println(sql);
	        ResultSet rs = st.executeQuery(sql);
	        if (rs.next())
	        {
	        	String nome = rs.getString("nome");
	         	c = new Cliente(cpf, nome);
	        }
	        else {
	        	throw new ClienteNaoCadastrado();
	        }
        	st.close();
        	return c;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ClienteNaoCadastrado();
		}
	}
	
	public void removerTudo() {
		Connection con = null;
		Statement st = null;
		Cliente c = null;
		try {
			con = Conexao.getConexao();
		    st = con.createStatement();
	        String insert = "delete from cliente";
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
