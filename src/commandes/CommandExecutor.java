package commandes;
import java.io.PrintStream;

public class CommandExecutor {
	
	public static boolean userOk = false ;
	public static boolean pwOk = false ;
	
	public static void executeCommande(PrintStream ps, String commande) {

			String[] tokens = commande.split(" ");

			if(tokens[tokens.length - 1].equals("true")) {

				// Changer de repertoire. Un (..) permet de revenir au repertoire superieur
				if (tokens[0].equals("cd")) (new CommandeCD(ps, commande)).execute();

				// Telecharger un fichier
				else if (tokens[0].equals("get")) (new CommandeGET(ps, commande)).execute();

				// Afficher la liste des fichiers et des dossiers du repertoire courant
				else if (tokens[0].equals("ls")) (new CommandeLS(ps, commande)).execute();

				// Afficher le repertoire courant
				else if (tokens[0].equals("pwd")) (new CommandePWD(ps, commande)).execute();

				// Envoyer (uploader) un fichier
				else if (tokens[0].equals("stor")) (new CommandeSTOR(ps, commande)).execute();

				else ps.println("530 Cette commande n'existe pas");
			}
			else if( tokens[0].equals("pass") ||
				tokens[0].equals("passe") ||
				tokens[0].equals("user")
			)
			{
				// Le mot de passe pour l'authentification
				if(tokens[0].equals("pass")) (new CommandePASS(ps, commande)).execute();

				// Le mot de passe pour l'authentification
				if(tokens[0].equals("passe")) (new CommandePASSE(ps, commande)).execute();
	
				// Le login pour l'authentification
				if(tokens[0].equals("user")) (new CommandeUSER(ps, commande)).execute();
			} else {
				ps.println("530 Vous n'êtes pas connecté");
			}
		}

}
