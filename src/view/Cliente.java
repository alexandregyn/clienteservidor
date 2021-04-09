package view;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import model.Conexao;

public class Cliente {

	public static void main(String[] args) {
		 
		// IP Local 192.168.0.6
		// IP Máquina Virtual Cliente 192.168.0.13
		
		try {
			//Declarar o soket cliente
			Socket cliente = new Socket("192.168.0.6", 7000);
			System.out.println("Cliente Iniciado");
			
			// Cria um instancia de conexão com o banco de dados.
			Conexao conexao = new Conexao();
			
			Scanner teclado = new Scanner(System.in);
			Scanner chegada = new Scanner(cliente.getInputStream());
			
			// Fluxo de dados para envio
			PrintStream saida = new PrintStream(cliente.getOutputStream());
			
			String mensagem = "";
			do {
				System.out.println("Digite sua mensagem: ");
				mensagem = teclado.nextLine();
				if (mensagem.length() != 0) {
					// mensagem enviada para o servidor.
					saida.println(mensagem);
					
					// Salva no banco mensagem do cliente
					String sql = "INSERT INTO CLISER (CS_ID, CS_NOME, CS_MENSAGEM)" +
							"VALUES (DEFAULT, 'Cliente 1', '"+ mensagem +"')";
					conexao.executaSql(sql);
					
					String resposta = chegada.nextLine();
					System.out.println("Mensagem: " + mensagem);
					System.out.println("Resposta: " + resposta);
					System.out.println("|---------------------------------|\n");
				}
			} while (mensagem.length() > 0);
			
			cliente.close();
			System.out.println("Cliente Finalizado");
			
		} catch (Exception e) {
			System.out.println("Ocorreu um erro durante a conexão...");
		}

	}

}