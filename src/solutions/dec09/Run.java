package solutions.dec09;

import utils.Utils;

import java.util.List;

public class Run {
    public static void main(String[] args) {
        List<String> input = Utils.readFileToList("src/solutions/dec09/input");
        List<String> testInput = Utils.readFileToList("src/solutions/dec09/testInput");
        Solve09 s = new Solve09();

        System.out.println("Test A: " + s.solveA(testInput));
        System.out.println("Actual A: " + s.solveA(input));
        System.out.println("Test B: " + s.solveB(testInput));
        System.out.println("Actual B: " + s.solveB(input));
    }
}
