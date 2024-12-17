package solutions.dec16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solve16 {
    long lowest;
    List<Integer> start;
    List<Integer> end;
    List<List<Character>> map;
    List<Path> paths;
    Map<List<Integer>, Long> mapPoints;
    private final static String UP = "u";
    private final static String DOWN = "d";
    private final static String LEFT = "l";
    private final static String RIGHT = "r";

    public long solveA(List<String> input){
        initializeSolve(input);

        while(!paths.isEmpty()){
            List<Path> newPaths = new ArrayList<>();
            for(Path p: paths){
                if(p.points <= lowest) {
                    List<Integer> currentSpot = p.visitedLocations.get(p.visitedLocations.size()-1);
                    int y = currentSpot.get(0);
                    int x = currentSpot.get(1);
                    if (end.get(0) == y && end.get(1) == x) {
                        lowest = p.points;
                    }else{
                        checkDirection(y - 1, x, p, UP, newPaths);
                        checkDirection(y + 1, x, p, DOWN, newPaths);
                        checkDirection(y, x - 1, p, LEFT, newPaths);
                        checkDirection(y, x + 1, p, RIGHT, newPaths);
                    }
                }
            }
            paths = newPaths;
        }
        return lowest;
    }


    public long solveB(List<String> input) {
        initializeSolve(input);
        List<Path> winningPaths = new ArrayList<>();

        while (!paths.isEmpty()) {
            List<Path> newPaths = new ArrayList<>();
            for (Path p : paths) {
                if (p.points <= lowest) {
                    List<Integer> currentSpot = p.visitedLocations.get(p.visitedLocations.size() - 1);
                    int y = currentSpot.get(0);
                    int x = currentSpot.get(1);
                    if (end.get(0) == y && end.get(1) == x) {
                        winningPaths.add(p);
                        lowest = p.points;
                    }else{
                        checkDirection(y - 1, x, p, UP, newPaths);
                        checkDirection(y + 1, x, p, DOWN, newPaths);
                        checkDirection(y, x - 1, p, LEFT, newPaths);
                        checkDirection(y, x + 1, p, RIGHT, newPaths);
                    }
                }
            }
            paths = newPaths;
        }
        Set<List<Integer>> winningTiles = new HashSet<>();
        for(Path p: winningPaths){
            if(p.points == lowest) {
                winningTiles.addAll(p.visitedLocations);
            }
        }
        return winningTiles.size();
    }

    private void checkDirection(int y, int x, Path p, String direction, List<Path> newPaths) {
        if (map.get(y).get(x) != '#' && !p.visitedLocations.contains(List.of(y, x))) {
            Path temp = new Path(p.points, new ArrayList<>(p.visitedLocations), direction);
            temp.points++;
            if (!p.direction.equals(direction)) {
                temp.points += 1000;
            }
            temp.visitedLocations.add(List.of(y, x));

            if (temp.points <= mapPoints.get(List.of(y, x)) || temp.points - 1000 == mapPoints.get(List.of(y, x))) {
                mapPoints.put(List.of(y, x), temp.points);
                newPaths.add(temp);
            }
        }
    }

    private void initializeSolve(List<String> input) {
        lowest = Long.MAX_VALUE;
        start = List.of(input.size() - 2, 1);
        end = new ArrayList<>(List.of(1, input.get(0).length() - 2));
        map = new ArrayList<>();
        paths = new ArrayList<>();
        mapPoints = new HashMap<>();

        for(int i = 0; i < input.size(); i++){
            List<Character> row = new ArrayList<>();
            char[] chars = input.get(i).toCharArray();
            for(int j = 0; j < chars.length; j++){
                row.add(chars[j]);
                mapPoints.put(List.of(i, j), Long.MAX_VALUE);
            }
            map.add(row);
        }
        paths.add(new Path(0, new ArrayList<>(List.of(start)), RIGHT));
    }

}
