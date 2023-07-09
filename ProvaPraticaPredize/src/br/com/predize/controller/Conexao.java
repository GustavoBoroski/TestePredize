package br.com.predize.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	//Nome do usuário do mysql
	private static final String USERNAME = "root";
	
	//Senha do banco 
	private static final String PASSWORD = "Gm180800@";
	
	//Caminho do banco de dados, porta, nome do banco de dados 
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/predizebanco";
	
	//Conexão com o banco de dados
	public static Connection createConnectionToMySQL() throws SQLException {
		try {			
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
			return connection;
		} catch (ClassNotFoundException e) {
			throw new SQLException(e.getException());
		}
	}

	public static void main(String[] args) throws Exception {
			
		//Recuperar uma conexão com o banco de dados
		Connection con = createConnectionToMySQL();
		
		//Testar se a conexão é nula
		if(con != null) {
			System.err.println("Conexão obetida com sucesso!");
			con.close();
		}
	}
}
