package solutions.dec07;

import java.util.ArrayList;
import java.util.List;

public class Solve07 {
    List<Long> numbers = new ArrayList<>();
    List<Character> operators = new ArrayList<>();
    public long solve(List<String> input, boolean partA){
        long counter = 0;

        for(String s: input){
            String[] calc = s.split(":");
            String[] op = calc[1].trim().split(" ");

            counter += createEquations(Long.parseLong(calc[0]), op, partA);
        }

        return counter;
    }

    private long createEquations(long result, String[] operators, boolean partA){
        List<String> calculations = new ArrayList<>(List.of(operators[0]));
        for(int i = 1; i < operators.length; i++){
            List<String> newCalculations = new ArrayList<>();
            for(String s: calculations){
                newCalculations.add(s + "+" + operators[i]);
                newCalculations.add(s + "*" + operators[i]);
                if(!partA){
                    newCalculations.add(s + "|" + operators[i]);
                }
            }
            calculations = new ArrayList<>(newCalculations);
        }

        for(String s: calculations){
            if(partA && calculateEquationsLeftToRight(s) == result){
                return result;
            }else if(calculateEquationsLeftToRightWithConcatenations(s) == result){
                return result;
            }
        }
        return 0;
    }

    private void fillNumbersAndOperatorsLists(String eq) {
        numbers = new ArrayList<>();
        operators = new ArrayList<>();

        StringBuilder num = new StringBuilder();
        for(int i = 0; i < eq.length(); i++){
            if(Character.isDigit(eq.charAt(i))){
                num.append(eq.charAt(i));
            }else{
                numbers.add(Long.parseLong(String.valueOf(num)));
                num = new StringBuilder();
                operators.add(eq.charAt(i));
            }
        }
        numbers.add(Long.parseLong(String.valueOf(num)));
    }

    private long calculateEquationsLeftToRight(String eq) {
        fillNumbersAndOperatorsLists(eq);

        long result = numbers.get(0);
        for(int i = 1; i < numbers.size(); i++){
            if(operators.get(i-1) == '+'){
                result += numbers.get(i);
            }else{
                result *= numbers.get(i);
            }
        }

        return result;
    }


    private long calculateEquationsLeftToRightWithConcatenations(String eq) {
        fillNumbersAndOperatorsLists(eq);

        long result = numbers.get(0);
        for(int i = 1; i < numbers.size(); i++){
            if(operators.get(i-1) == '+'){
                result += numbers.get(i);
            }else if(operators.get(i-1) == '*'){
                result *= numbers.get(i);
            }else{
                result = Long.parseLong(result + String.valueOf(numbers.get(i)));
            }
        }

        return result;
    }

}