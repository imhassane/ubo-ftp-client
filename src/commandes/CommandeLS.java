package commandes;

import models.FileManager;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class CommandeLS extends Commande {
	
	public CommandeLS(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() {
		StringBuilder builder = new StringBuilder();

		try(Stream<Path> paths = Files.walk(Paths.get(commandeArgs[1]))) {
			// On liste tous les fichiers qui ne sont pas cachés.
			paths.filter(f -> !f.toFile().isHidden() && !f.toAbsolutePath().toString().contains(".")).forEach(f -> builder.append( f.toString() + " "));
		} catch (Exception e) {
			e.printStackTrace();
		}

		ps.println("210 " + builder.toString());
	}

}
