package solutions.dec03;

import utils.Utils;

import java.util.List;

public class Run {
    public static void main(String[] args) {
        List<String> testInput = Utils.readFileToList("src/solutions/dec03/testInput");
        List<String> testInputB = Utils.readFileToList("src/solutions/dec03/testInputB");
        List<String> input = Utils.readFileToList("src/solutions/dec03/input");
        Solve03 s = new Solve03();

        System.out.println("Test A: " + s.solveA(testInput));
        System.out.println("Actual A: " + s.solveA(input));
        System.out.println("Test B: " + s.solveB(testInputB));
        System.out.println("Actual B: " + s.solveB(input));
    }

}