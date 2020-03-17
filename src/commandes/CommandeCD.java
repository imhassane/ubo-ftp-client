package commandes;

import models.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class CommandeCD extends Commande {
	
	public CommandeCD(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() {
		FileManager.move(this.commandeArgs[0]);
		ps.println(FileManager.BASE_PATH);
	}

}
