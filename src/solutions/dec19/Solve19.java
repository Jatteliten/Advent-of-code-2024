package solutions.dec19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solve19 {
    long result;
    List<String> towelPatterns;
    List<String> designs;
    public long solveA(List<String> input){
        initializeSolve(input);

        for (String design : designs) {
            Map<String, Integer> currentDesigns = new HashMap<>();
            currentDesigns.put(design, 1);
            boolean earlyBreak = false;

            while (!currentDesigns.isEmpty() && !earlyBreak) {
                Map<String, Integer> nextDesigns = new HashMap<>();

                for (Map.Entry<String, Integer> entry : currentDesigns.entrySet()) {
                    String newDesign = entry.getKey();
                    int numberOfDesigns = entry.getValue();

                    if (newDesign.isEmpty()) {
                        result++;
                        earlyBreak = true;
                        break;
                    } else {
                        for (String pattern : towelPatterns) {
                            if (newDesign.startsWith(pattern)) {
                                String remaining = newDesign.substring(pattern.length());
                                nextDesigns.merge(remaining, numberOfDesigns, Integer::sum);
                            }
                        }
                    }
                }
                currentDesigns = nextDesigns;
            }
        }
        return result;
    }

    public long solveB(List<String> input){
        initializeSolve(input);

        for (String design : designs) {
            Map<String, Long> currentDesigns = new HashMap<>();
            currentDesigns.put(design, 1L);

            while (!currentDesigns.isEmpty()) {
                Map<String, Long> nextDesigns = new HashMap<>();

                for (Map.Entry<String, Long> entry : currentDesigns.entrySet()) {
                    String newDesign = entry.getKey();
                    long numberOfDesigns = entry.getValue();

                    if (newDesign.isEmpty()) {
                        result += numberOfDesigns;
                    } else {
                        for (String pattern : towelPatterns) {
                            if (newDesign.startsWith(pattern)) {
                                String remaining = newDesign.substring(pattern.length());
                                nextDesigns.merge(remaining, numberOfDesigns, Long::sum);
                            }
                        }
                    }
                }
                currentDesigns = nextDesigns;
            }
        }
        return result;
    }

    private void initializeSolve(List<String> input) {
        result = 0;
        towelPatterns = List.of(input.get(0).split(", "));
        designs = new ArrayList<>();

        for(int i = 2; i < input.size(); i++){
            designs.add(input.get(i));
        }
    }
}
