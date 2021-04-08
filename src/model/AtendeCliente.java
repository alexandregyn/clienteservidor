package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class AtendeCliente extends Thread {

	Socket cliente;

	public AtendeCliente(Socket cliente) {
		this.cliente = cliente;
	}

	@Override
	public void run() {
		System.out.println("Cliente Conectado com thread ("+ this.getId() +"): " + cliente.getInetAddress());
		
		Scanner teclado = new Scanner(System.in);
		Scanner chegada; //Cliente está digitando ou vai digitar.
		
		InputStreamReader fluxoDados;
		
		// Cria um instancia de conexão com o banco de dados.
		Conexao conexao = new Conexao();
		
		try {
			chegada = new Scanner(cliente.getInputStream());
			PrintStream saida =  new PrintStream(cliente.getOutputStream());
			
			while (chegada.hasNextLine()) {
				// Mensagem que vem do cliente
				String mensagemCliente = chegada.nextLine();
				
				// Mensagem do servidor
				System.out.println("Informe a respota para ("+ mensagemCliente + "): ");
				String mensagemResposta = teclado.nextLine();
				saida.println(mensagemResposta);
				
				// Cria um instancia de conexão com o banco de dados.
				conexao = new Conexao();
				// Salva no banco mensagem do servidor
				String sql = "INSERT INTO CLISER (CS_ID, CS_NOME, CS_MENSAGEM)" +
						"VALUES (DEFAULT, 'Servidor', '"+ mensagemResposta +"')";
				conexao.executaSql(sql);
				System.out.println("|---------------------------------|\n");
			}
			
			fluxoDados = new InputStreamReader(cliente.getInputStream());
			BufferedReader dado = new BufferedReader(fluxoDados);
			System.out.println(dado.readLine());

			cliente.close();

			System.out.println("Cliente desconectado: " + cliente.getInetAddress() + " da Thread ("+ this.getId() +")" );

		} catch (Exception e) {
			System.out.println("Ocorreu um erro durante o processo!");
		}
	}
}
