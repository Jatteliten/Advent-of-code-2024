package solutions.dec16;

import utils.Utils;

import java.util.List;

public class Run {
    public static void main(String[] args) {
        List<String> testInput = Utils.readFileToList("src/solutions/dec16/testInput");
        List<String> input = Utils.readFileToList("src/solutions/dec16/input");
        Solve16 s = new Solve16();

        System.out.println("Test A: " + s.solveA(testInput));
        System.out.println("Actual A: " + s.solveA(input));
    }
}
