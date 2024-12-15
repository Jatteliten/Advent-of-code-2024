package solutions.dec12;

import java.util.ArrayList;
import java.util.List;

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
                            if((y == 0 && x == 0) ||
                                    (y == height && x == width) ||
                                    (y == height && x == 0) ||
                                    (y == 0 && x == width)) {
                                fence+=2;
                            }else if(y == 0 || y == height ||
                                    x == 0 || x == width){
                                fence++;
                            }
                            if(y != 0 && input.get(y-1).charAt(x) == plotLetter && !checkedPoints.contains(List.of(y-1, x))){
                                newPlotsToCheck.add(List.of(y-1, x));
                                checkedPoints.add(List.of(y-1, x));
                            }else if(y != 0 && input.get(y-1).charAt(x) != plotLetter){
                                fence++;
                            }
                            if(y != height && input.get(y+1).charAt(x) == plotLetter && !checkedPoints.contains(List.of(y+1, x))){
                                newPlotsToCheck.add(List.of(y+1, x));
                                checkedPoints.add(List.of(y+1, x));
                            }else if(y != height && input.get(y+1).charAt(x) != plotLetter){
                                fence++;
                            }
                            if(x != 0 && input.get(y).charAt(x-1) == plotLetter && !checkedPoints.contains(List.of(y, x-1))){
                                newPlotsToCheck.add(List.of(y, x-1));
                                checkedPoints.add(List.of(y, x-1));
                            }else if(x != 0 && input.get(y).charAt(x-1) != plotLetter){
                                fence++;
                            }
                            if(x != width && input.get(y).charAt(x+1) == plotLetter && !checkedPoints.contains(List.of(y, x+1))){
                                newPlotsToCheck.add(List.of(y, x+1));
                                checkedPoints.add(List.of(y, x+1));
                            }else if(x != width && input.get(y).charAt(x+1) != plotLetter){
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
}