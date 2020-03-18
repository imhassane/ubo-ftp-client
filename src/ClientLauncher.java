import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.Scanner;

import commandes.CommandExecutor;
import ftp.Client;

public class ClientLauncher {

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);

			Client client = new Client("localhost", 2121);
			String commande = "", result = "";

			try {
				Thread.sleep(500);
				while (client.getReader().ready()) {
					result = client.getReader().readLine();
					System.out.println(result);
				}
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}

			do {
				System.out.print(">> ");
				commande = sc.nextLine();
				if(commande.contains("user")) {
					client.setUsername(commande.split(" ")[1]);
				} else if(commande.contains("passe")) {
					client.setPassword(commande.split(" ")[1]);
					commande += " " + client.getUsername();
				} else if(commande.contains("pass")) {
					client.setPassword(commande.split(" ")[1]);
				}
				client.sendCommande(commande);
			} while(!client.getResponse().contains("230"));

			client.setAuthenticated(true);

			boolean stay = true;
			while(stay) {
				System.out.print(">> ");
				commande = sc.nextLine();
				if(commande.toLowerCase().contains("cd")) {
					commande += " " + client.getPath();
				}
				client.sendCommande(commande);

				Thread.sleep(200);
				result = client.getResponse();
				if(result.equals("221"))
					stay = false;
			}

		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
