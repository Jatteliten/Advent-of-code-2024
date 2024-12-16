package solutions.dec12;

import utils.Utils;

import java.util.List;

public class Run {
    public static void main(String[] args) {
        List<String> smallTestInput = Utils.readFileToList("src/solutions/dec12/smallTestInput");
        List<String> testInput = Utils.readFileToList("src/solutions/dec12/testInput");
        List<String> eShape = Utils.readFileToList("src/solutions/dec12/eShape");
        List<String> input = Utils.readFileToList("src/solutions/dec12/input");
        Solve12 s = new Solve12();

        System.out.println("Small test A: " + s.solveA(smallTestInput));
        System.out.println("Test A: " + s.solveA(testInput));
        System.out.println("Actual A: " + s.solveA(input));
        System.out.println("Small Test B: " + s.solveB(smallTestInput));
        System.out.println("Test B: " + s.solveB(testInput));
        System.out.println("Test B E-shape: " + s.solveB(eShape));
        System.out.println("Actual B: " + s.solveB(input));
    }
}
