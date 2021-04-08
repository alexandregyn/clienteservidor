package view;

import java.net.ServerSocket;

import model.AtendeCliente;

public class Servidor {

	public static void main(String[] args) {

		try {	
			ServerSocket servidor = new ServerSocket(7000);
			System.out.println("Servidor Iniciado....");
			
			while (true) {
				AtendeCliente atendeCliente = new AtendeCliente(servidor.accept());
				atendeCliente.start();
			}
			
		} catch (Exception e) {
			System.out.println("Ocorreu um erro durante o processo!");
		}
	}

}
