package commandes;

import models.FileManager;
import java.io.PrintStream;

public class CommandeCD extends Commande {
	
	public CommandeCD(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() {
		String path = FileManager.move(this.commandeArgs[1], this.commandeArgs[0]);
		ps.println("210 " + path);
	}

}
