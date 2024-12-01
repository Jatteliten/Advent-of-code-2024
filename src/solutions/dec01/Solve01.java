package solutions.dec01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solve01 {
    List<Integer> firstList;
    List<Integer> secondList;

    public int solveA(List<String> input){
        int output = 0;
        initializeLists(input);
        Collections.sort(firstList);
        Collections.sort(secondList);

        for(int i = 0; i < firstList.size(); i++){
            output += Math.abs(secondList.get(i) - firstList.get(i));
        }
        return output;
    }

    public int solveB(List<String> input){
        int output = 0;
        initializeLists(input);

        for(Integer i: firstList){
            if(secondList.contains(i)) {
                output += i * Collections.frequency(secondList, i);
            }
        }
        return output;
    }

    public void initializeLists(List<String> input){
        firstList = new ArrayList<>();
        secondList = new ArrayList<>();
        for(String s: input) {
            String[] sArr = s.split("   ");
            firstList.add(Integer.valueOf(sArr[0]));
            secondList.add(Integer.valueOf(sArr[1]));
        }
    }

}