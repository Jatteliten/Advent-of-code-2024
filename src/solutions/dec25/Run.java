package solutions.dec25;

import utils.Utils;

import java.util.List;

public class Run {
    public static void main(String[] args) {
        List<String> testInput = Utils.readFileToList("src/solutions/dec25/testInput");
        List<String> input = Utils.readFileToList("src/solutions/dec25/input");
        Solve25 s = new Solve25();

        System.out.println("Test A: " + s. solveA(testInput));
        System.out.println("Actual A: " + s. solveA(input));
    }
}
