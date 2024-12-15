package solutions.dec14;

import utils.Utils;

import java.util.List;

public class Run {
    public static void main(String[] args) {
        List<String> testInput = Utils.readFileToList("src/solutions/dec14/testInput");
        List<String> input = Utils.readFileToList("src/solutions/dec14/input");
        Solve14 s = new Solve14();

        System.out.println("Test A: " + s.solveA(testInput,7, 11));
        System.out.println("Actual A: " + s.solveB(input,103, 101));
    }
}
