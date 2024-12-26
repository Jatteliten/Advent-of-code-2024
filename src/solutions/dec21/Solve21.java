package solutions.dec21;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solve21 {
    Map<String, Long> keyMovementMap;
    public long solve(List<String> input, int robots) {
        long result = 0;

        for (String s : input) {
            keyMovementMap = new HashMap<>();
            keyMovementMap.put(s.split(":")[1], 1L);

            for (int i = 0; i < robots; i++) {
                pressButtons();
            }

            long count = 0;
            for (String key : keyMovementMap.keySet()) {
                count += keyMovementMap.get(key) * key.length();
            }
            result += count * Integer.parseInt(s.split(":")[0].substring(0, 3));
        }
        return result;
    }

    private void pressButtons() {
        Map<String, Long> newKeyMovementMap = new HashMap<>();
        for (String ss : keyMovementMap.keySet()) {
            String[] keyPresses = ss.split("");
            for (int k = 0; k < keyPresses.length; k++) {
                String movement = (k == 0) ? getKeyMovement(keyPresses[k], "A") :
                        getKeyMovement(keyPresses[k], keyPresses[k - 1]);
                newKeyMovementMap.put(movement, newKeyMovementMap.getOrDefault(movement, 0L) + keyMovementMap.get(ss));
            }
        }
        keyMovementMap = newKeyMovementMap;
    }


    private String getKeyMovement(String key, String lastKey){
        String returnString = "A";
        switch(key){
            case "A" -> returnString = switch (lastKey) {
                case "^" -> ">A";
                case ">" -> "^A";
                case "v" -> "^>A";
                case "<" -> ">>^A";
                default -> returnString;
            };
            case "^" -> returnString = switch (lastKey) {
                case "A" -> "<A";
                case ">" -> "<^A";
                case "v" -> "^A";
                case "<" -> ">^A";
                default -> returnString;
            };
            case ">" -> returnString = switch (lastKey) {
                case "A" -> "vA";
                case "^" -> "v>A";
                case "v" -> ">A";
                case "<" -> ">>A";
                default -> returnString;
            };
            case "v" -> returnString = switch (lastKey) {
                case "A" -> "<vA";
                case "^" -> "vA";
                case ">" -> "<A";
                case "<" -> ">A";
                default -> returnString;
            };
            case "<" -> returnString = switch (lastKey) {
                case "A" -> "v<<A";
                case "^" -> "v<A";
                case ">" -> "<<A";
                case "v" -> "<A";
                default -> returnString;
            };
        }
        return returnString;
    }

}