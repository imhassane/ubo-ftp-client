package commandes;

import java.io.PrintStream;

public class CommandePASS extends Commande {
	
	public CommandePASS(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() {
		// Le mot de passe est : abcd
		if(commandeArgs[0].toLowerCase().equals("abcd")) {
			CommandExecutor.pwOk = true;
			ps.println("230 Vous êtes bien connecté sur notre serveur");

		}
		else {
			ps.println("530 Le mot de passe est faux");
		}
		
	}

}
