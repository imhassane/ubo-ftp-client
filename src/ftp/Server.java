package ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import commandes.CommandExecutor;

public class Server extends ServerSocket {
	
	int PORT = 2121;

	public Server(int port) throws IOException {
		super(port);
		this.PORT = port;
		
		System.out.println("SERVEUR FTP");
	}
	
	public void listenClients() throws IOException {
		Socket socket = null;
		// Chaque fois qu'on recoit un client, on créé un thread auquel on passe
		// un flux de lecture et d'écriture qu'on attache à la socket qu'on a acceptée.
		while(true) {
			socket = this.accept();

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream printer = new PrintStream(socket.getOutputStream());

			ServerClientConnectionHandler t = new ServerClientConnectionHandler(socket, reader, printer);
			Thread th = new Thread(t);
			th.start();
		}
	}

}
