package commandes;

import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandeUSER extends Commande {
	
	public CommandeUSER(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() {
		Path passwords = Paths.get(System.getProperty("user.dir") + "/passwords");
		Stream<Path> paths;
		List<String> users = null;

		try {
			paths = Files.walk(passwords);
			users = paths.filter(Files::isDirectory)
					.filter(f -> f.getFileName().equals(commandeArgs[0]))
					.map(f -> f.getFileName().toString())
					.collect(Collectors.toList());
		} catch(Exception ex) {
			ex.printStackTrace();
		}

		if(users != null) {
			CommandExecutor.userOk = true;
			ps.println("331 Commande user OK");
		}
		else {
			ps.println("530 Le user " + commandeArgs[0] + " n'existe pas");
		}
		
	}

}
