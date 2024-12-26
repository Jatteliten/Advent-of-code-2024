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

        // If output is a z gate, the operation has to be XOR and it cannot have x and y as inputs. Unless it is z00
        // If a XOR operation has x and y as inputs, the output has to have a XOR operation where it is the input
        // If operation is AND. An OR operation has to use the output from the AND operation as input
        System.out.println("Actual B done manually: cnk,mps,msq,qwf,vhm,z14,z27,z39");
    }
}
