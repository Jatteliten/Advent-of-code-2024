package solutions.dec13;

import utils.Utils;

import java.util.List;

public class Run {
    public static void main(String[] args) {
        List<String> testInput = Utils.readFileToList("src/solutions/dec13/testInput");
        List<String> input = Utils.readFileToList("src/solutions/dec13/input");
        Solve13 s = new Solve13();

        System.out.println("Test A: " + s.solve(testInput, true));
        System.out.println("Actual A: " + s.solve(input, true));

        System.out.println("Test B: " + s.solve(testInput, false));
        System.out.println("Actual B: " + s.solve(input, false));
    }
}
