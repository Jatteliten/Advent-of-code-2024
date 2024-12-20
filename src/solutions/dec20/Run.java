package solutions.dec20;

import utils.Utils;

import java.util.List;

public class Run {
    public static void main(String[] args) {
        List<String> input = Utils.readFileToList("src/solutions/dec20/input");
        List<String> testInput = Utils.readFileToList("src/solutions/dec20/testInput");
        Solve20 s = new Solve20();

        System.out.println(s.solve(input, 100, true));

        System.out.println(s.solve(input, 100, false));
    }
}
