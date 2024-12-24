package solutions.dec24;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solve24 {
    Map<String, Integer> bits;
    Map<String, Integer> initialBits;
    List<String> operations;
    Map<String, Integer> calculatedBits;
    public long solveA(List<String> input){
        initializeSolve(input);
        calculateA();

        return Long.parseLong(createPrintableBinaryString(calculatedBits, true),2);
    }

    private String createPrintableBinaryString(Map<String, Integer> mapToProcess, boolean shouldStartWithZ) {
        List<String> printableBits = new ArrayList<>();
        StringBuilder binaryBuilder = new StringBuilder();

        for (String s : mapToProcess.keySet()) {
            if (s.startsWith("z") && shouldStartWithZ) {
                printableBits.add(s + ": " + mapToProcess.get(s));
            }else if(!shouldStartWithZ){
                printableBits.add(s + ": " + mapToProcess.get(s));
            }
        }

        Collections.sort(printableBits);
        Collections.reverse(printableBits);

        for (String s : printableBits) {
            binaryBuilder.append(s.substring(s.length() - 1));
        }

        return binaryBuilder.toString();
    }

    private void calculateA(){
        while(calculatedBits.size() != operations.size() + initialBits.size()){
            for(String s: operations){
                String[] operation = s.split(" -> ");
                String resultingBit = operation[1];
                String[] calculation = operation[0].split(" ");
                String first = calculation[0];
                String second = calculation[2];
                String operator = calculation[1];
                if(calculatedBits.get(resultingBit) == null && calculatedBits.get(first) != null
                        && calculatedBits.get(second) != null){
                    switch (operator) {
                        case "AND" -> {
                            if (calculatedBits.get(first) == 0 || calculatedBits.get(second) == 0) {
                                calculatedBits.put(resultingBit, 0);
                            } else {
                                calculatedBits.put(resultingBit, 1);
                            }
                        }
                        case "OR" -> {
                            if (calculatedBits.get(first) == 0 && calculatedBits.get(second) == 0) {
                                calculatedBits.put(resultingBit, 0);
                            } else {
                                calculatedBits.put(resultingBit, 1);
                            }
                        }
                        case "XOR" -> {
                            if (calculatedBits.get(first).equals(calculatedBits.get(second))) {
                                calculatedBits.put(resultingBit, 0);
                            } else {
                                calculatedBits.put(resultingBit, 1);
                            }
                        }
                    }
                }
            }
        }
    }

    private void initializeSolve(List<String> input){
        bits = new HashMap<>();
        initialBits = new HashMap<>();
        operations = new ArrayList<>();
        calculatedBits = new HashMap<>();

        for(String s: input){
            if(s.contains(":")){
                String[] bit = s.split(": ");
                initialBits.put(bit[0], Integer.parseInt(bit[1]));
                calculatedBits.put(bit[0], Integer.parseInt(bit[1]));
            }else if(s.contains("->")){
                operations.add(s);
            }
        }
    }
}
