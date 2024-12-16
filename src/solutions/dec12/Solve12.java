package solutions.dec12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solve12 {
    public int solveA(List<String> input) {
        int result = 0;
        List<List<Integer>> checkedPoints = new ArrayList<>();
        int height = input.size() - 1;
        int width = input.get(0).length() - 1;
        for(int i = 0; i < input.size(); i++){
            for(int j = 0; j < input.get(0).length(); j++){
                if(!checkedPoints.contains(List.of(i, j))){
                    List<List<Integer>> plotsToCheck = List.of(List.of(i, j));
                    checkedPoints.add(plotsToCheck.get(0));
                    int plotSize = 0;
                    int fence = 0;
                    char plotLetter = input.get(i).charAt(j);
                    while(!plotsToCheck.isEmpty()){
                        List<List<Integer>> newPlotsToCheck = new ArrayList<>();
                        for(List<Integer> plot: plotsToCheck){
                            int y = plot.get(0);
                            int x = plot.get(1);
                            plotSize++;
                            if(y != 0 && input.get(y-1).charAt(x) == plotLetter && !checkedPoints.contains(List.of(y-1, x))){
                                newPlotsToCheck.add(List.of(y-1, x));
                                checkedPoints.add(List.of(y-1, x));
                            }else if(y == 0 || input.get(y-1).charAt(x) != plotLetter){
                                fence++;
                            }
                            if(y != height && input.get(y+1).charAt(x) == plotLetter && !checkedPoints.contains(List.of(y+1, x))){
                                newPlotsToCheck.add(List.of(y+1, x));
                                checkedPoints.add(List.of(y+1, x));
                            }else if(y == height || input.get(y+1).charAt(x) != plotLetter){
                                fence++;
                            }
                            if(x != 0 && input.get(y).charAt(x-1) == plotLetter && !checkedPoints.contains(List.of(y, x-1))){
                                newPlotsToCheck.add(List.of(y, x-1));
                                checkedPoints.add(List.of(y, x-1));
                            }else if(x == 0 || input.get(y).charAt(x-1) != plotLetter){
                                fence++;
                            }
                            if(x != width && input.get(y).charAt(x+1) == plotLetter && !checkedPoints.contains(List.of(y, x+1))){
                                newPlotsToCheck.add(List.of(y, x+1));
                                checkedPoints.add(List.of(y, x+1));
                            }else if(x == width || input.get(y).charAt(x+1) != plotLetter){
                                fence++;
                            }
                            plotsToCheck = newPlotsToCheck;
                        }
                    }
                    result += plotSize * fence;
                }
            }
        }
        return result;
    }


    public int solveB(List<String> input) {
        int result = 0;
        List<List<Integer>> checkedPoints = new ArrayList<>();
        int height = input.size() - 1;
        int width = input.get(0).length() - 1;
        for(int i = 0; i < input.size(); i++){
            for(int j = 0; j < input.get(0).length(); j++){
                if(!checkedPoints.contains(List.of(i, j))){
                    int plotSize = 0;
                    Map<List<Integer>, List<String>> fenceMap = new HashMap<>();
                    List<List<Integer>> plotsToCheck = List.of(List.of(i, j));
                    checkedPoints.add(plotsToCheck.get(0));
                    char plotLetter = input.get(i).charAt(j);
                    while(!plotsToCheck.isEmpty()){
                        List<List<Integer>> newPlotsToCheck = new ArrayList<>();
                        for(List<Integer> plot: plotsToCheck){
                            plotSize++;
                            List<String> fences = new ArrayList<>();
                            int y = plot.get(0);
                            int x = plot.get(1);
                            if(y != 0 && input.get(y-1).charAt(x) == plotLetter && !checkedPoints.contains(List.of(y-1, x))){
                                newPlotsToCheck.add(List.of(y-1, x));
                                checkedPoints.add(List.of(y-1, x));
                            }else if(y == 0 || input.get(y-1).charAt(x) != plotLetter){
                                fences.add("UP");
                            }
                            if(y != height && input.get(y+1).charAt(x) == plotLetter && !checkedPoints.contains(List.of(y+1, x))){
                                newPlotsToCheck.add(List.of(y+1, x));
                                checkedPoints.add(List.of(y+1, x));
                            }else if(y == height || input.get(y+1).charAt(x) != plotLetter){
                                fences.add("DOWN");
                            }
                            if(x != 0 && input.get(y).charAt(x-1) == plotLetter && !checkedPoints.contains(List.of(y, x-1))){
                                newPlotsToCheck.add(List.of(y, x-1));
                                checkedPoints.add(List.of(y, x-1));
                            }else if(x == 0 || input.get(y).charAt(x-1) != plotLetter){
                                fences.add("LEFT");
                            }
                            if(x != width && input.get(y).charAt(x+1) == plotLetter && !checkedPoints.contains(List.of(y, x+1))){
                                newPlotsToCheck.add(List.of(y, x+1));
                                checkedPoints.add(List.of(y, x+1));
                            }else if(x == width || input.get(y).charAt(x+1) != plotLetter){
                                fences.add("RIGHT");
                            }
                            plotsToCheck = newPlotsToCheck;
                            fenceMap.put(plot, fences);
                        }
                    }
                    int corners = 0;
                    for(List<Integer> l: fenceMap.keySet()){
                        List<String> fences = fenceMap.get(l);
                        int yy = l.get(0);
                        int xx = l.get(1);
                        corners += checkCorners(fences, "UP", "RIGHT");
                        corners += checkCorners(fences, "RIGHT", "DOWN");
                        corners += checkCorners(fences, "DOWN", "LEFT");
                        corners += checkCorners(fences, "LEFT", "UP");
                        if(fences.contains("LEFT")){
                            if(fenceMap.get(List.of(yy-1, xx-1)) != null && fenceMap.get(List.of(yy-1, xx)) != null && fenceMap.get(List.of(yy-1, xx-1)).contains("DOWN")){
                                corners++;
                            }
                            if(fenceMap.get(List.of(yy+1, xx-1)) != null && fenceMap.get(List.of(yy+1, xx)) != null&& fenceMap.get(List.of(yy+1, xx-1)).contains("UP")){
                                corners++;
                            }
                        }
                        if(fences.contains("RIGHT")){
                            if(fenceMap.get(List.of(yy-1, xx+1)) != null && fenceMap.get(List.of(yy-1, xx)) != null && fenceMap.get(List.of(yy-1, xx+1)).contains("DOWN")){
                                corners++;
                            }
                            if(fenceMap.get(List.of(yy+1, xx+1)) != null && fenceMap.get(List.of(yy+1, xx)) != null && fenceMap.get(List.of(yy+1, xx+1)).contains("UP")){
                                corners++;
                            }
                        }
                    }
                    result += plotSize * corners;
                }
            }
        }
        return result;
    }

    private int checkCorners(List<String> l, String first, String second){
        if(l.contains(first) && l.contains(second)){
            return 1;
        }
        return 0;
    }
}