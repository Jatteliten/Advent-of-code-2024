package solutions.dec10;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solve10 {

    public int solveA(List<String> input){
        int result = 0;
        for(int y = 0; y < input.size(); y++){
            for(int x = 0; x < input.get(0).length(); x++){
                if(input.get(y).charAt(x) == '0'){
                    result+= traverseTrail(input, List.of(List.of(y, x)));
                }
            }
        }
        return result;
    }

    public int solveB(List<String> input){
        int result = 0;
        for(int y = 0; y < input.size(); y++){
            for(int x = 0; x < input.get(0).length(); x++){
                if(input.get(y).charAt(x) == '0'){
                    result+= traverseTrailB(input, List.of(List.of(y, x)));
                }
            }
        }
        return result;
    }

    private int traverseTrail(List<String> input, List<List<Integer>> currentCoordinates){
        Set<List<Integer>> topSet = new HashSet<>();
        for(int i = 0; i <= 9; i++){
            if(currentCoordinates.isEmpty()){
                break;
            }
            List<List<Integer>> newCoordinates = new ArrayList<>();
            for(List<Integer> l: currentCoordinates){
                int current = input.get(l.get(0)).charAt(l.get(1));
                if(current == 57){
                    topSet.add(l);
                }else{
                    checkDirections(input, newCoordinates, l, current);
                }
            }
            currentCoordinates = newCoordinates;
        }
        return topSet.size();
    }

    private int traverseTrailB(List<String> input, List<List<Integer>> currentCoordinates){
        int result = 0;
        for(int i = 0; i <= 9; i++){
            if(currentCoordinates.isEmpty()){
                break;
            }
            List<List<Integer>> newCoordinates = new ArrayList<>();
            for(List<Integer> l: currentCoordinates){
                int current = input.get(l.get(0)).charAt(l.get(1));
                if(current == 57){
                    result++;
                }else{
                    checkDirections(input, newCoordinates, l, current);
                }
            }
            currentCoordinates = newCoordinates;
        }
        return result;
    }

    private void checkDirections(List<String> input, List<List<Integer>> newCoordinates, List<Integer> l, int current) {
        if(l.get(0) > 0 && current + 1 == input.get(l.get(0) - 1).charAt(l.get(1))){
            newCoordinates.add(List.of(l.get(0) - 1, l.get(1)));
        }
        if(l.get(1) > 0 && current + 1 == input.get(l.get(0)).charAt(l.get(1) - 1)){
            newCoordinates.add(List.of(l.get(0), l.get(1) - 1));
        }
        if(l.get(0) < input.size() - 1 && current + 1== input.get(l.get(0) + 1).charAt(l.get(1))){
            newCoordinates.add(List.of(l.get(0) + 1, l.get(1)));
        }
        if(l.get(1) < input.get(0).length() - 1 && current + 1 == input.get(l.get(0)).charAt(l.get(1) + 1)){
            newCoordinates.add(List.of(l.get(0), l.get(1) + 1));
        }
    }
}