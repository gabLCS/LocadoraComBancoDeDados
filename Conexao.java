package gabrielLeonardoCunhaSantos.LocadoraComBancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

	private static Connection[] conexoes = new Connection[10];
	private static boolean conectou = false;
	private static int pos = 0;
	
	private Conexao() {
	}
	
	public static Connection getConexao() {
		if(pos == 10) {
			pos = 0;
		}
		if(!conectou) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				for(int i = 0; i < 10; i++) {
					conexoes[i] = DriverManager.getConnection("jdbc:mysql://localhost/Locadora?useSSL=false", "root", "biel36421987");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
			
			conectou = true;
		}
		
		return conexoes[pos++];
	}
	
}
