package projetoagenda;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoBanco {
	private static final String USUARIO = "root";
	private static final String SENHA = "Developer@100Senha!";
	private static final String BANCO_URL = "jdbc:mysql://localhost:3306/agendadb";
	
	public static Connection criarConexaoMySQL() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(BANCO_URL, USUARIO, SENHA);
	}
	
	public static void main(String[] args) throws Exception {
		Connection con = criarConexaoMySQL();
		if (con != null) {
			System.out.println("Conectado com sucesso!");
		}
	}

}
