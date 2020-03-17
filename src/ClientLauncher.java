import java.io.BufferedReader;
import java.io.PrintStream;

import ftp.Client;

public class ClientLauncher {

	public static void main(String[] args) {
		try {
			Client client = new Client("localhost", 2121);
			client.setUser("personne", "abcd");

			while(true) {
				client.authenticate();
				System.out.println("hello");
				client.getResponse();
			}

		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
