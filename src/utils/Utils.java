package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Utils {
    public static List<String> readFileToList(String filePath){
        Path path = Paths.get(filePath);
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createPointsMap(List<String> input, Map<List<Integer>, Long> mapPoints, List<List<Character>> map) {
        for(int i = 0; i < input.size(); i++){
            List<Character> row = new ArrayList<>();
            char[] chars = input.get(i).toCharArray();
            for(int j = 0; j < chars.length; j++){
                row.add(chars[j]);
                mapPoints.put(List.of(i, j), Long.MAX_VALUE);
            }
            map.add(row);
        }
    }
}
