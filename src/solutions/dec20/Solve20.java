package solutions.dec20;

import utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solve20 {
    List<List<Character>> map;
    List<Path> paths;
    Map<List<Integer>, Long> mapPoints;
    Path start;
    Path winner;
    int result;
    int cheatTarget;
    public int solve(List<String> input, int cT, boolean partOne){
        cheatTarget = cT;
        initializeMap(input);
        paths.add(start);
        boolean end = false;

        while(!paths.isEmpty() || !end){
            List<Path> newPaths = new ArrayList<>();
            for(Path p: paths){
                int y = p.visitedLocations.get(p.visitedLocations.size()-1).get(0);
                int x = p.visitedLocations.get(p.visitedLocations.size()-1).get(1);
                if(map.get(y).get(x) == 'E'){
                    winner = p;
                    end = true;
                    break;
                }else{
                    checkDirection(y-1, x, p, newPaths);
                    checkDirection(y+1, x, p, newPaths);
                    checkDirection(y, x-1, p, newPaths);
                    checkDirection(y, x+1, p, newPaths);
                }
            }
            paths = newPaths;
        }

        if(partOne) {
            for (int i = 0; i < winner.visitedLocations.size(); i++) {
                List<Integer> tile = winner.visitedLocations.get(i);
                checkCheats(tile.get(0) - 2, tile.get(1), i);
                checkCheats(tile.get(0) + 2, tile.get(1), i);
                checkCheats(tile.get(0), tile.get(1) - 2, i);
                checkCheats(tile.get(0), tile.get(1) + 2, i);
            }
        }else{
            for(int i = 0; i < winner.visitedLocations.size(); i++) {
                List<Integer> tile = winner.visitedLocations.get(i);
                checkCheatsB(tile.get(0), tile.get(1), i);
            }
        }

        return result;
    }

    private void checkCheatsB(int y, int x, int currentPoints){
        int xCounter = 0;
        for(int i = -20; i <= 20; i++){
            for(int j = -xCounter; j <= xCounter; j++){
                if(mapPoints.containsKey(List.of(y+i, x+j)) && mapPoints.get(List.of(y+i, x+j)) != Long.MAX_VALUE
                        && mapPoints.get(List.of(y+i, x+j)) >= currentPoints+cheatTarget+Math.abs(j)+Math.abs(i)){
                    result++;
                }
            }
            if(i < 0){
                xCounter++;
            }else{
                xCounter--;
            }
        }
    }

    private void checkCheats(int y, int x, int currentPoints){
        if(mapPoints.containsKey(List.of(y, x)) && mapPoints.get(List.of(y, x)) != Long.MAX_VALUE
                && mapPoints.get(List.of(y, x)) >= currentPoints+cheatTarget+2){
            result++;
        }
    }

    private void checkDirection(int y, int x, Path p, List<Path> newPaths) {
        if (map.get(y).get(x) != '#' && !p.visitedLocations.contains(List.of(y, x))) {
            Path temp = new Path(p.points, new ArrayList<>(p.visitedLocations));
            temp.points++;
            temp.visitedLocations.add(List.of(y, x));
            mapPoints.put(List.of(y, x), temp.points);
            newPaths.add(temp);
        }
    }

    private void initializeMap(List<String> input){
        result = 0;
        map = new ArrayList<>();
        paths = new ArrayList<>();
        mapPoints = new HashMap<>();
        Utils.createPointsMap(input, mapPoints, map);

        for(int y = 0; y < map.size(); y++){
            if(map.get(y).contains('S')){
                start = new Path(0, new ArrayList<>(List.of(List.of(y, map.get(y).indexOf('S')))));
                break;
            }
        }
    }

}
