package solutions.dec15;

import utils.Utils;

import java.util.List;

public class Run {
    public static void main(String[] args) {
        List<String> smallTestInput = Utils.readFileToList("src/solutions/dec15/smallTestInput");
        List<String> testInput = Utils.readFileToList("src/solutions/dec15/testInput");
        List<String> input = Utils.readFileToList("src/solutions/dec15/input");
        Solve15 s = new Solve15();

        System.out.println("Small Test A: " + s.solveA(smallTestInput));
        System.out.println("Test A: " + s.solveA(testInput));
        System.out.println("Actual A: " + s.solveA(input));
    }
}
