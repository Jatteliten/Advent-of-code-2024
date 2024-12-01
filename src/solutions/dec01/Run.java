package solutions.dec01;

import utils.Utils;

import java.util.List;

public class Run {
    public static void main(String[] args) {
        Solve01 s = new Solve01();
        List<String> testInput = Utils.readFileToList("src/solutions/dec01/testInput");
        List<String> input = Utils.readFileToList("src/solutions/dec01/input");
        System.out.println("Test A: " + s.solveA(testInput));
        System.out.println("Actual A: " + s.solveA(input));
        System.out.println("Test B: " + s.solveB(testInput));
        System.out.println("Actual B: " + s.solveB(input));
    }
}
