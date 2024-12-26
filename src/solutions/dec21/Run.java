package solutions.dec21;

import utils.Utils;

import java.util.List;

public class Run {
    public static void main(String[] args) {
        List<String> testInput = Utils.readFileToList("src/solutions/dec21/testInput");
        List<String> input = Utils.readFileToList("src/solutions/dec21/input");
        Solve21 s = new Solve21();

        System.out.println("Test A: " + s.solve(testInput, 2));
        System.out.println("Actual A: " + s.solve(input, 2));
        System.out.println("Test B: " + s.solve(testInput, 25));
        System.out.println("Actual B: " + s.solve(input, 25));
    }
}
