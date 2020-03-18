package models;

import java.nio.file.Paths;
import java.nio.file.Path;

public class FileManager {
    public static String BASE_PATH = System.getProperty("user.dir");

    public static String move(String base, String newPath) {
        Path p = Paths.get(base + newPath);
        return p.normalize().toString();
    }
}
