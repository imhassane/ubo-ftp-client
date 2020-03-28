package commandes;

import java.io.*;
import java.util.Arrays;

public class CommandeGET extends Commande {
	
	public CommandeGET(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() {
		String fileName = commandeArgs[0];
		File f = new File(fileName);

		if(!f.exists()) {
			ps.println("530 ce fichier n'existe pas");
			return;
		}

		StringBuilder builder = new StringBuilder();
		// On lit le fichier, puis on le convertit en tableau de bits qu'on envoie ensuite
		try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			reader.lines().forEach(l -> builder.append(l + "\n"));
			byte[] content = builder.toString().getBytes();
			ps.println("210 " + Arrays.toString(content));
		} catch(Exception e) {
			ps.println("530 Impossible de recuperer ce fichier, une erreur s'est produite");
		}
	}

}
