package solutions.dec17;

import utils.Utils;

import java.util.List;

public class Run {
    public static void main(String[] args) {
        List<String> testInput = Utils.readFileToList("src/solutions/dec17/testInput");
        List<String> input = Utils.readFileToList("src/solutions/dec17/input");
        Solve17 s = new Solve17();

        System.out.println("Test A: " + s.solveA(testInput));
        System.out.println("Actual A: " + s.solveA(input));

        s.printB(input);
        long test = 38886248019999L;
        System.out.println(s.runProgramWithValue(input, 38886248019999L) + " : " + s.runProgramWithValue(input, 34639));
        System.out.println("[2, 4, 1, 2, 7, 5, 1, 3, 4, 3, 5, 5, 0, 3, 3, 0]");
    }

}
