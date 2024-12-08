package solutions.dec08;

import utils.Utils;

import java.util.List;

public class Run {
    public static void main(String[] args) {
        List<String> testInput = Utils.readFileToList("src/solutions/dec08/testInput");
        List<String> input = Utils.readFileToList("src/solutions/dec08/input");
        Solve08 s = new Solve08();

        System.out.println("Test A: " + s.solveA(testInput));
        System.out.println("Actual A: " + s.solveA(input));
        System.out.println("Test B: " + s.solveB(testInput));
        System.out.println("Test B: " + s.solveB(input));
    }
}
