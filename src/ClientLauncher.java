import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

import commandes.CommandExecutor;
import ftp.Client;

public class ClientLauncher {

	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);

			Client client = new Client("localhost", 2121);
			String commande = "", result = "";

			// On attends quelques millisecondes pour recevoir les premiers messages du serveur.
			try {
				Thread.sleep(500);
				while (client.getReader().ready()) {
					result = client.getReader().readLine();
					System.out.println(result);
				}
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}
			// On force l'utilisateur à se connecter.
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
			// On récupère l'adresse courante du serveur.
			client.sendCommande("pwd");
			Thread.sleep(500);
			if(client.getReader().ready()) {
				client.setServerPath(client.getReader().readLine().substring(3));
			}

			boolean stay = true;
			while(stay) {
				System.out.print(">> ");
				commande = sc.nextLine();
				if(commande.toLowerCase().contains("cd")) {
					commande += " " + client.getServerPath();
				}
				else if(commande.toLowerCase().contains("ls")) {
					commande += " " + client.getServerPath();
				}
				else if(commande.contains("stor")) {
					File f = new File(client.getPath() + "/" + commande.split(" ")[1]);
					if(!f.exists()) {
						System.out.println("Ce fichier n'existe pas");
					} else {
						// On lit le contenu du fichier
						StringBuilder builder = new StringBuilder();
						BufferedReader reader = new BufferedReader(new FileReader(f.getAbsoluteFile().toString()));
						reader.lines().forEach(l -> builder.append(l + "\n"));
						// On créé une chaine de bytes sous la forme: 45,98,100,3
						// on met pas d'espace car le CommandeExecutor fait un split(" ").
						byte[] bytes = builder.toString().getBytes();
						String bytesValue = "";
						for(byte b: bytes) {
							bytesValue += b + ",";
						}
						// On envoie une commande composée du nom du fichier puis de la chaine de bytes.
						commande = "stor " + client.getServerPath() + "\\" + f.getName() + " " + bytesValue;
						reader.close();
						System.out.println("Copie de " + f.getAbsolutePath() + " vers " + client.getServerPath());
					}
				}
				client.sendCommande(commande);

				Thread.sleep(200);
				if(commande.contains("get")) {
					result = client.getReader().readLine();
					// On écrit le contenu du fichier dans le repertoire actuel.
					try(BufferedWriter writer = new BufferedWriter(new FileWriter(client.getPath() + commande.split(" ")[1] + "-copy"))) {
						String content = result.substring(4);
						content = content.substring(1, content.length() - 1);
						String[] splitted = content.split(", ");

						byte[] bytes = new byte[splitted.length];
						for(int i = 0; i < bytes.length; i++) {
							bytes[i] = (byte) ((int) Integer.valueOf(splitted[i]));
						}
						writer.write(new String(bytes));
						System.out.println("230 transfert reussi");
					} catch(Exception ex) {
						System.out.println("Ce fichier n'existe pas");
					}
					result = result.split(" ")[0];
				} else {
					result = client.getResponse();
				}
				if(result.equals("221"))
					stay = false;
			}

		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
