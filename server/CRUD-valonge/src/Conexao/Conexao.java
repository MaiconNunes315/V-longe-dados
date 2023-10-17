package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static final String URL = "jdbc:mysql://localhost:3306/valonge";
	private static final String USUARIO = "root";
	private static final String SENHA = "";

	public static Connection conectar(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver encontrado");
		}catch (ClassNotFoundException e) {
			System.out.println("Driver não encontrado" + e.getMessage());
		}
		
		try {
			System.out.println("Conectado com sucesso");
			return DriverManager.getConnection(URL, USUARIO, SENHA);
			
		} catch (SQLException e) {
			System.out.println("Erro " + e.getMessage());
			return null;
		}

	}
}
