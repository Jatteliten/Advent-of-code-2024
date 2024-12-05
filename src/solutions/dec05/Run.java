package solutions.dec05;

import utils.Utils;

import java.util.List;

public class Run {
    public static void main(String[] args) {
        List<String> testInput = Utils.readFileToList("src/solutions/dec05/testInput");
        List<String> input = Utils.readFileToList("src/solutions/dec05/input");
        Solve05 s = new Solve05();

        System.out.println("Test A: " + s.solveA(testInput));
        System.out.println("Actual A: " + s.solveA(input));
        System.out.println("Test B: " + s.solveB(testInput));
        System.out.println("Actual B: " + s.solveB(input));
    }
}
