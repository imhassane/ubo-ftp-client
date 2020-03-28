package commandes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintStream;

public class CommandeSTOR extends Commande {
	
	public CommandeSTOR(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(commandeArgs[1]))) {
			/*
				commandeArgs[2] est une chaine de bits sous cette forme: 21,89,90,20,39
				On convertit les bytes recus comme chaine de caractères en tableau de bits puis on écrit dans le fichier.
			 */
			String stringBytes = commandeArgs[2];
			String[] splitted = stringBytes.split(",");
			byte[] bytes = new byte[splitted.length];
			for(int i = 0; i < splitted.length; i++) {
				bytes[i] = (byte)((int) Integer.valueOf(splitted[i]));
			}
			writer.write(new String(bytes));
			ps.println("230 Le fichier a ete transfere");
		} catch(Exception e) {
			ps.println("530 Une erreur s'est produite, impossible de transferer le fichier");
		}
	}

}
