package commandes;

import models.FileManager;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandeLS extends Commande {
	
	public CommandeLS(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() {
		try(Stream<Path> walker = Files.walk(Paths.get(FileManager.BASE_PATH))) {
			List<String> files = walker
									.filter(Files::isDirectory)
									.map(x -> x.getFileName().toString())
									.collect(Collectors.toList());
			StringBuilder builder = new StringBuilder();
			builder.append("210 ");
			files.forEach(f -> builder.append(String.format("%s ", f)));
			ps.println(builder.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
