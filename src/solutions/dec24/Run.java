package solutions.dec24;

import utils.Utils;

import java.util.List;

public class Run {
    public static void main(String[] args) {
        List<String> testInput = Utils.readFileToList("src/solutions/dec24/testInput");
        List<String> testInputB = Utils.readFileToList("src/solutions/dec24/testInputB");
        List<String> input = Utils.readFileToList("src/solutions/dec24/input");
        Solve24 s = new Solve24();

        System.out.println("Test A: " + s.solveA(testInput));
        System.out.println("Actual A: " + s.solveA(input));
    }
}
