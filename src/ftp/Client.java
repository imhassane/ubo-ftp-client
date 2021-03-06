package ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client extends Socket {

	PrintStream printer;
	BufferedReader reader;

	String username = "", password = "";
	String path = null, serverPath = ".";

	boolean authenticated = false;
	
	public Client(String host, int port)
			throws IOException
	{
		super(host, port);
		this.reader = new BufferedReader(new InputStreamReader(this.getInputStream()));
		this.printer = new PrintStream(this.getOutputStream());
		this.path = System.getProperty("user.dir");
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() { return password; }

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setServerPath(String s) { this.serverPath = s; }

	public String getServerPath() { return serverPath; }

	public BufferedReader getReader() {
		return reader;
	}

	public void sendCommande(String cmd) throws IOException {
		String state = this.authenticated ? "true" : "false";
		this.printer.println(cmd + " " + state);
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
