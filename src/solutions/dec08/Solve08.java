package solutions.dec08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solve08 {

    public int solveA(List<String> input){
        return getNumberOfAntiNodes(createAntennaMap(input), input);
    }

    public int solveB(List<String> input){
        return getNumberOfAntiNodesB(createAntennaMap(input), input);
    }

    public Map<Character, List<Integer>> createAntennaMap(List<String> input){
        Map<Character, List<Integer>> signals = new HashMap<>();
        for(int y = 0; y < input.size(); y++){
            for(int x = 0; x < input.get(0).length(); x++){
                if(input.get(y).charAt(x) != '.'){
                    char currentChar = input.get(y).charAt(x);
                    if(!signals.containsKey(currentChar)){
                        signals.put(currentChar, List.of(y,x));
                    }else{
                        List<Integer> tempList = new ArrayList<>(signals.get(currentChar));
                        tempList.add(y);
                        tempList.add(x);
                        signals.put(currentChar, tempList);
                    }
                }
            }
        }
        return signals;
    }

    public int getNumberOfAntiNodes(Map<Character, List<Integer>> signals, List<String> input){
        List<List<Integer>> busyLocations = new ArrayList<>();
        for(Character c: signals.keySet()){
            for(int i = 0; i < signals.get(c).size() - 2; i+=2){
                for(int j = i + 2; j < signals.get(c).size(); j+=2){
                    int firstY = signals.get(c).get(i);
                    int firstX = signals.get(c).get(i+1);
                    int secondY = signals.get(c).get(j);
                    int secondX = signals.get(c).get(j+1);
                    int yDiff = firstY - secondY;
                    int xDiff = firstX - secondX;
                    List<Integer> firstAntiNode = List.of(firstY + yDiff, firstX + xDiff);
                    List<Integer> secondAntiNode = List.of(secondY - yDiff, secondX - xDiff);

                    checkThatAntiNodeIsWithinBoundsAndDoesntExist(input, busyLocations, firstAntiNode);
                    checkThatAntiNodeIsWithinBoundsAndDoesntExist(input, busyLocations, secondAntiNode);
                }
            }
        }
        return busyLocations.size();
    }

    public int getNumberOfAntiNodesB(Map<Character, List<Integer>> signals, List<String> input){
        List<List<Integer>> busyLocations = new ArrayList<>();
        for(Character c: signals.keySet()){
            for(int i = 0; i < signals.get(c).size() - 2; i+=2){
                for(int j = i + 2; j < signals.get(c).size(); j+=2){
                    int firstY = signals.get(c).get(i);
                    int firstX = signals.get(c).get(i+1);
                    int secondY = signals.get(c).get(j);
                    int secondX = signals.get(c).get(j+1);
                    int yDiff = firstY - secondY;
                    int xDiff = firstX - secondX;
                    int multiplier = 0;
                    if(!busyLocations.contains(List.of(signals.get(c).get(i), signals.get(c).get(i+1)))){
                        busyLocations.add(List.of(signals.get(c).get(i), signals.get(c).get(i+1)));
                    }
                    if(!busyLocations.contains(List.of(signals.get(c).get(j), signals.get(c).get(j+1)))){
                        busyLocations.add(List.of(signals.get(c).get(j), signals.get(c).get(j+1)));
                    }

                    while((firstY + yDiff + yDiff * multiplier >= 0 && firstY + yDiff + yDiff * multiplier < input.size()
                            && firstX + xDiff + xDiff * multiplier >= 0 && firstX + xDiff + xDiff * multiplier < input.get(0).length())){
                        List<Integer> firstAntiNode = List.of(firstY + yDiff + yDiff * multiplier, firstX + xDiff + xDiff * multiplier);
                        checkThatAntiNodeIsWithinBoundsAndDoesntExist(input, busyLocations, firstAntiNode);
                        multiplier++;
                    }

                    multiplier = 0;
                    while(secondY - yDiff - yDiff * multiplier >= 0 && secondY - yDiff - yDiff * multiplier < input.size()
                            && secondX - xDiff - xDiff * multiplier >= 0 && secondX - xDiff - xDiff * multiplier < input.get(0).length()){
                        List<Integer> secondAntiNode = List.of(secondY - yDiff - yDiff * multiplier, secondX - xDiff - xDiff * multiplier);
                        checkThatAntiNodeIsWithinBoundsAndDoesntExist(input, busyLocations, secondAntiNode);
                        multiplier++;
                    }


                }
            }
        }
        return busyLocations.size();
    }

    private void checkThatAntiNodeIsWithinBoundsAndDoesntExist(List<String> input,
                                                              List<List<Integer>> busyLocations,
                                                              List<Integer> antiNode) {
        if(antiNode.get(0) >= 0
                && antiNode.get(0) < input.size()
                && antiNode.get(1) >= 0
                && antiNode.get(1) < input.get(0).length()
                && !busyLocations.contains(antiNode)){
            busyLocations.add(antiNode);
        }
    }

}
