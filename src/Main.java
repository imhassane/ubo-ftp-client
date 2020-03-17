/*
 * TP JAVA RIP
 * Min Serveur FTP
 * */

import ftp.Server;

public class Main {

	public static void main(String[] args) {
		try {
			Server server = new Server(2121);

			server.listenClients();

			server.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
