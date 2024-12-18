package solutions.dec18;

import java.util.ArrayList;
import java.util.List;

public class Solve18 {
    char[][] map;
    public int solveA(List<String> input, int gridSize, int iterations){
        map = new char[gridSize+1][gridSize+1];

        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                map[i][j] = '.';
            }
        }
        for(int i = 0; i < iterations; i++){
            int y = Integer.parseInt(input.get(i).split(",")[0]);
            int x = Integer.parseInt(input.get(i).split(",")[1]);
            map[y][x] = '#';
        }

        List<Path> paths = new ArrayList<>();
        paths.add(new Path(0, 0));

        int counter = 0;
        while(!paths.isEmpty()){
            List<Path> newPaths = new ArrayList<>();
            for(Path p: paths){
                if(p.y == map.length-1 && p.x == map[0].length - 1){
                    return counter;
                }
                newPaths.addAll(checkDirections(p));
            }
            paths = newPaths;
            counter++;
        }

        return 0;
    }

    public String solveB(List<String> input, int gridSize){
        map = new char[gridSize+1][gridSize+1];

        for(int i = 0; i < input.size(); i++) {
            for(int j = 0; j < map.length; j++){
                for(int k = 0; k < map[0].length; k++){
                    map[j][k] = '.';
                }
            }
            boolean end = false;
            for(int j = 0; j <= i; j++){
                int y = Integer.parseInt(input.get(j).split(",")[0]);
                int x = Integer.parseInt(input.get(j).split(",")[1]);
                map[y][x] = '#';
            }

            List<Path> paths = new ArrayList<>();
            paths.add(new Path(0, 0));

            while (!paths.isEmpty()) {
                List<Path> newPaths = new ArrayList<>();
                for (Path p : paths) {
                    if (p.y == map.length - 1 && p.x == map[0].length - 1) {
                        end = true;
                        continue;
                    }
                    newPaths.addAll(checkDirections(p));
                }
                paths = newPaths;
            }
            if(!end){
                return input.get(i);
            }
        }

        return null;
    }

    private List<Path> checkDirections(Path p){
        List<Path> newPaths = new ArrayList<>();
        if(p.y > 0 && map[p.y-1][p.x] == '.'){
            Path temp = new Path(p.y-1, p.x);
            map[p.y-1][p.x] = '#';
            newPaths.add(temp);
        }
        if(p.y < map.length-1 && map[p.y+1][p.x] == '.'){
            Path temp = new Path(p.y+1, p.x);
            map[p.y+1][p.x] = '#';
            newPaths.add(temp);
        }
        if(p.x > 0 && map[p.y][p.x-1] == '.'){
            Path temp = new Path(p.y, p.x-1);
            map[p.y][p.x-1] = '#';
            newPaths.add(temp);
        }
        if(p.x < map[0].length - 1 && map[p.y][p.x+1] == '.'){
            Path temp = new Path(p.y, p.x+1);
            map[p.y][p.x+1] = '#';
            newPaths.add(temp);
        }
        return newPaths;
    }
}
