package commandes;

import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CommandePASSE extends Commande {

    public CommandePASSE(PrintStream ps, String commandeStr) {
        super(ps, commandeStr);
    }

    public void execute() {
        Path path = Paths.get(System.getProperty("user.dir") + String.format("/passwords/%s/pwd.txt", commandeArgs[1]));
        try {
            List<String> content = Files.readAllLines(path);
            if(content != null && content.size() > 0) {
                if(commandeArgs[0].toLowerCase().equals(content.get(0))) {
                    ps.println("230 Vous êtes bien connecté sur notre serveur");
                } else {
                    ps.println("530 Le mot de passe est faux");
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }

}
