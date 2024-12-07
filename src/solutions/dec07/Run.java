package solutions.dec07;

import utils.Utils;

import java.util.List;

public class Run {
    public static void main(String[] args) {
        List<String> testInput = Utils.readFileToList("src/solutions/dec07/testInput");
        List<String> input = Utils.readFileToList("src/solutions/dec07/input");

        Solve07 s = new Solve07();
        System.out.println("Test A: " + s.solve(testInput, true));
        System.out.println("Actual A: " + s.solve(input, true));
        System.out.println("Test A: " + s.solve(testInput, false));
        System.out.println("Test A: " + s.solve(input, false));
    }

}
