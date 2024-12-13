package solutions.dec13;

import utils.Utils;

import java.util.List;

public class Run {
    public static void main(String[] args) {
        List<String> testInput = Utils.readFileToList("src/solutions/dec13/testInput");
        List<String> input = Utils.readFileToList("src/solutions/dec13/input");
        Solve13 s = new Solve13();

        System.out.println(s.SolveA(testInput));
        System.out.println(s.SolveA(input));
    }
}
