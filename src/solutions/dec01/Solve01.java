package solutions.dec01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solve01 {

    List<Integer> firstList = new ArrayList<>();
    List<Integer> secondList = new ArrayList<>();

    public int solveA(List<String> input){
        firstList = new ArrayList<>();
        secondList = new ArrayList<>();
        initializeLists(input);
        Collections.sort(firstList);
        Collections.sort(secondList);
        int output = 0;
        for(int i = 0; i < firstList.size(); i++){
            if(secondList.get(i) > firstList.get(i)){
                output += (secondList.get(i) - firstList.get(i));
            }else{
                output += (firstList.get(i) - secondList.get(i));
            }
        }
        return output;
    }

    public int solveB(List<String> input){
        firstList = new ArrayList<>();
        secondList = new ArrayList<>();
        initializeLists(input);
        int output = 0;
        for(Integer i: firstList){
            int counter = 0;
            for(Integer j: secondList){
                if(i.equals(j)){
                    counter++;
                }
            }
            if(counter > 0) {
                output += (i * counter);
            }
        }
        return output;
    }

    public void initializeLists(List<String> input){
        for(String s: input) {
            String[] sArr = s.split("   ");
            firstList.add(Integer.valueOf(sArr[0]));
            secondList.add(Integer.valueOf(sArr[1]));
        }
    }

}
