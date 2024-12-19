package solutions.dec17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solve17 {
    private long a;
    private long b;
    private long c;
    public String solveA(List<String> input) {
        a = Integer.parseInt(input.get(0).substring(12));
        b = Integer.parseInt(input.get(1).substring(12));
        c = Integer.parseInt(input.get(2).substring(12));
        int[] program = Arrays.stream(input.get(4).split(" ")[1].split(",")).mapToInt(Integer::parseInt).toArray();
        return String.join(",", runProgram(program).stream().map(Long::toString).toList());
    }

    public long solveB(List<String> input){
        int[] program = Arrays.stream(input.get(4).split(" ")[1].split(",")).mapToInt(Integer::parseInt).toArray();
        long counter = 1;
        int negativeCheck = 0;
        while(true){
            a = counter;
            b = 0;
            c = 0;
            List<Integer> newValue = runProgramWithValue(input, counter);
            if(newValue.size() == program.length && newValue.get(0) == program[0] && newValue.get(1) == program[1]){
                return counter;
            }
            boolean checkValues = true;
            for(int i = 1; i <= newValue.size(); i++){
                if(newValue.get(newValue.size()-i) != program[program.length-i]){
                    checkValues = false;
                    break;
                }
            }
            if(checkValues){
                negativeCheck = 0;
                counter *= 8;
            }else{
                if(negativeCheck < 50 && counter > 0){
                    counter--;
                    negativeCheck++;
                }else{
                    counter++;
                }
            }
        }
    }

    public List<Integer> runProgramWithValue(List<String> input, long value){
        int[] program = Arrays.stream(input.get(4).split(" ")[1].split(",")).mapToInt(Integer::parseInt).toArray();
        a = value;

        return runProgram(program);
    }

    private long getComboValue(int operand) {
        return switch (operand) {
            case 0, 1, 2, 3 -> operand;
            case 4 -> a;
            case 5 -> b;
            case 6 -> c;
            default -> 0;
        };
    }

    private List<Integer> runProgram(int[] program){
        List<Integer> result = new ArrayList<>();

        int ip = 0;

        while (ip < program.length) {
            int litOp = program[ip+1];
            long combOp = getComboValue(program[ip+1]);
            switch (program[ip]) {
                case 0 -> a = a / (long)Math.pow(2, combOp);
                case 1 -> b = b ^ ((long)litOp);
                case 2 -> b = combOp % 8;
                case 3 -> {
                    if(a != 0) {
                        ip = litOp-2;
                    }
                }
                case 4 -> b = b^c;
                case 5 -> result.add((int) (combOp % 8L));
                case 6 -> b = a / (long)Math.pow(2, combOp);
                case 7 -> c = a / (long)Math.pow(2, combOp);
            }
            ip += 2;
        }

        return result;
    }
}
