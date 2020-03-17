package ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client extends Socket {

	Scanner scanner = new Scanner(System.in);

	PrintStream printer;
	BufferedReader reader;

	String host = "localhost", username = "", password = "";
	int port = 2121;
	
	public Client(String host, int port)
			throws UnknownHostException, IOException
	{
		super(host, port);
		this.host = host;
		this.port = port;
		this.reader = new BufferedReader(new InputStreamReader(this.getInputStream()));
		this.printer = new PrintStream(this.getOutputStream());
	}

	public void setUser(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void authenticate() {
		this.printer.println("user " + username);
		this.printer.println("pass " + password);
	}

	public void sendCommande(String cmd) throws IOException {
		this.printer.println(cmd);
		System.out.println("Commande: " + cmd);
	}

	public String getResponse() throws IOException {
		String str = new String();
		do {
			str= reader.readLine();
			System.out.println(str);
		}while(!(
				Character.isDigit(str.charAt(0)) &&
				Character.isDigit(str.charAt(1)) &&
				Character.isDigit(str.charAt(2)) &&
				str.charAt(3)==' '
			)
		);
		return str.substring(0,3);
	}
	
	public void leave() throws IOException {
		this.close();
	}
}
