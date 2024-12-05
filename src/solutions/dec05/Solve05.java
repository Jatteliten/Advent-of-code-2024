package solutions.dec05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solve05 {

    public int solveA(List<String> input){
        int output = 0;
        Map<String, ArrayList<String>> instructions = new HashMap<>();
        for(String s: input){
            if(!s.isEmpty()){
                if(s.charAt(2) == '|'){
                    createOrUpdateMapOfInstructions(instructions, s);
                }else{
                    output += checkIfValidInstructionsAndReturnMiddlePage(s.split(","), instructions);
                }
            }
        }
        return output;
    }

    public int solveB(List<String> input){
        int output = 0;
        Map<String, ArrayList<String>> instructions = new HashMap<>();
        for(String s: input){
            if(!s.isEmpty()){
                if(s.charAt(2) == '|'){
                    createOrUpdateMapOfInstructions(instructions, s);
                }else{
                    if(checkIfValidInstructionsAndReturnMiddlePage(s.split(","), instructions) == 0){
                        output+= correctOrderAndFindMiddlePage(s.split(","), instructions);
                    }
                }
            }
        }
        return output;
    }

    private void createOrUpdateMapOfInstructions(Map<String, ArrayList<String>> instructions, String s) {
        String[] ins = s.split("\\|");
        if(instructions.containsKey(ins[0])){
            ArrayList<String> temp = instructions.get(ins[0]);
            temp.add(ins[1]);
            instructions.put(ins[0], temp);
        }else{
            instructions.put(ins[0], new ArrayList<>(List.of(ins[1])));
        }
    }

    private int correctOrderAndFindMiddlePage(String[] s, Map<String, ArrayList<String>> ins){
        while(checkIfValidInstructionsAndReturnMiddlePage(s, ins) == 0) {
            int error = findErrorInInvalidInstruction(s, ins);
            for(int i = error+1; i < s.length; i++){
                if(ins.get(s[i]).contains(s[error])){
                    String temp = s[i];
                    s[i] = s[i-1];
                    s[i-1] = temp;
                    break;
                }
            }
        }
        return Integer.parseInt(s[s.length/2].substring(0, 2));
    }

    private int findErrorInInvalidInstruction(String[] s, Map<String, ArrayList<String>> ins){
        for(int i = 0; i < s.length - 1; i++){
            if(ins.get(s[i]) == null || !ins.get(s[i]).contains(s[i+1])){
                return i;
            }
        }
        return 0;
    }

    private int checkIfValidInstructionsAndReturnMiddlePage(String[] s, Map<String, ArrayList<String>> ins){
        for(int i = 0; i < s.length - 1; i++){
            if(ins.get(s[i]) == null || !ins.get(s[i]).contains(s[i+1])){
                return 0;
            }
        }
        return Integer.parseInt(s[s.length/2].substring(0, 2));
    }
}
