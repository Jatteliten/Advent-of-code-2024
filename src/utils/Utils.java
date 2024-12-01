package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Utils {
    public static List<String> readFileToList(String filePath){
        Path path = Paths.get(filePath);
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
