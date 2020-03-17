package models;

import java.nio.file.Paths;
import java.nio.file.Path;

public class FileManager {
    public static String BASE_PATH = System.getProperty("user.dir");

    public static void move(String path) {
        Path p = Paths.get(BASE_PATH + "/" + path);
        BASE_PATH = p.normalize().toString();
    }
}
