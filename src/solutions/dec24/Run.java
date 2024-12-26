package solutions.dec24;

import utils.Utils;

import java.util.List;

public class Run {
    public static void main(String[] args) {
        List<String> testInput = Utils.readFileToList("src/solutions/dec24/testInput");
        List<String> input = Utils.readFileToList("src/solutions/dec24/input");
        Solve24 s = new Solve24();

        System.out.println("Test A: " + s.solveA(testInput));
        System.out.println("Actual A: " + s.solveA(input));

        System.out.println("Actual B done manually: cnk,mps,msq,qwf,vhm,z14,z27,z39");
    }
}
