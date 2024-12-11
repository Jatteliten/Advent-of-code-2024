package solutions.dec11;

import utils.Utils;

import java.util.List;

public class Run {
    public static void main(String[] args) {
        List<String> input = Utils.readFileToList("src/solutions/dec11/input");
        List<String> testInput = Utils.readFileToList("src/solutions/dec11/testInput");
        Solve11 s = new Solve11();

        System.out.println("Test A: " + s.solve(testInput.get(0), 25));
        System.out.println("Actual A: " + s.solve(input.get(0), 25));
        System.out.println("Test A: " + s.solve(testInput.get(0), 75));
        System.out.println("Actual A: " + s.solve(input.get(0), 75));
    }
}
