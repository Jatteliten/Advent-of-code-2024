package solutions.dec16;

import java.util.ArrayList;
import java.util.List;

public class Solve16 {
    public int solveA(List<String> input){
        List<String> availablePath = new ArrayList<>();
        String goal = 1 + ":" + (input.get(0).length() - 2);
        availablePath.add(goal);
        List<List<String>> paths = new ArrayList<>();
        paths.add(List.of((input.size() - 2) + ":" + 1));
        for(int i = 0; i < input.size(); i++){
            for(int j = 0; j < input.get(0).length(); j++){
                if(input.get(i).charAt(j) == '.'){
                    availablePath.add(i + ":" + j);
                }
            }
        }

        List<List<String>> finalPaths = findFinalPath(paths, availablePath, goal);
        int lowest = Integer.MAX_VALUE;
        for(List<String> l: finalPaths){
            int turns = 0;
            boolean horizontal = true;
            for(int i = 1; i < l.size(); i++){
                String last = l.get(i-1);
                String current = l.get(i);
                int lastY = Integer.parseInt(last.substring(0, last.indexOf(":")));
                int lastX = Integer.parseInt(last.substring(last.indexOf(":") + 1));
                int currentY = Integer.parseInt(current.substring(0, current.indexOf(":")));
                int currentX = Integer.parseInt(current.substring(current.indexOf(":") + 1));
                if(horizontal){
                    if(lastY != currentY){
                        horizontal = false;
                        turns++;
                    }
                }else{
                    if(lastX != currentX){
                        horizontal = true;
                        turns++;
                    }
                }
            }
            lowest = Math.min(lowest, l.size() - 1 + turns * 1000);
        }
        return lowest;
    }

    private List<List<String>> findFinalPath(List<List<String>> paths, List<String> availablePath, String goal) {
        List<List<String>> goalPaths = new ArrayList<>();
        while(true){
            List<List<String>> newPaths = new ArrayList<>();
            for(List<String> path: paths){
                String p = path.get(path.size()-1);
                int y = Integer.parseInt(p.substring(0, p.indexOf(":")));
                int x = Integer.parseInt(p.substring(p.indexOf(":") + 1));
                String down = (y-1) + ":" + x;
                String up= (y+1) + ":" + x;
                String left = y + ":" + (x-1);
                String right = y + ":" + (x+1);

                for(String s: List.of(down, up, left, right)){
                    List<String> newPath = checkDirection(availablePath, path, s);
                    if (newPath != null){
                        if(newPath.get(newPath.size()-1).equals(goal)){
                            goalPaths.add(newPath);
                        }else{
                            newPaths.add(newPath);
                        }
                    }
                }
                paths = new ArrayList<>(newPaths);
            }
            if(paths.size() == 0){
                return goalPaths;
            }
        }
    }

    private List<String> checkDirection(List<String> availablePath, List<String> path, String direction) {
        if(!path.contains(direction) && availablePath.contains(direction)){
            List<String> newPath = new ArrayList<>(path);
            newPath.add(direction);
            return newPath;
        }
        return null;
    }
}
