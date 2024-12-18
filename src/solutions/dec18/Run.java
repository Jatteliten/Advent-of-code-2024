package solutions.dec18;

import utils.Utils;

import java.util.List;

public class Run {
    public static void main(String[] args) {
        List<String> testInput = Utils.readFileToList("src/solutions/dec18/testInput");
        List<String> input = Utils.readFileToList("src/solutions/dec18/input");
        Solve18 s = new Solve18();

        System.out.println(s.solveA(testInput, 6, 12));
        System.out.println(s.solveA(input, 70, 1024));
        System.out.println(s.solveB(input, 70));
    }
}
