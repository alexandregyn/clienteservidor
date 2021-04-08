package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conexao {

	//	URL do banco de dados
	private String urlBanco;
	
	// Usuário do banco de dados
	private String usuarioBanco;
	
	// Senha do banco de dados
	private String senhaBanco;
	
	// Realiza coneção no banco
	private Connection conexao;
	
	public Conexao() {
		this.urlBanco = "jdbc:postgresql://localhost:5432/CLIENTESERVIDOR";
		this.usuarioBanco = "postgres";
		this.senhaBanco = "123456";
		
		try {
			
			Class.forName("org.postgresql.Driver");
			conexao = DriverManager.getConnection(urlBanco, usuarioBanco, senhaBanco);
			System.out.println("Conexão realizada com sucesso!");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Problema para realizar conexão no banco de dados");
		}
	}
	
	// Método responsável em executar o SQL
	public int executaSql(String sql) {
		try {
			Statement stm = conexao.createStatement();
			int resultado =  stm.executeUpdate(sql);
			// Fecha conexção com o banco.
			conexao.close();
			return resultado;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
