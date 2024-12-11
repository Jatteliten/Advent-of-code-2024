package solutions.dec11;

import java.util.HashMap;
import java.util.Map;

public class Solve11 {

    public long solve(String input, int blinks) {
        long result = 0;
        Map<String, Long> stoneCounts = new HashMap<>();
        for (String stone : input.split(" ")) {
            stoneCounts.put(stone, 1L);
        }

        for (int i = 0; i < blinks; i++) {
            Map<String, Long> newCounts = new HashMap<>();

            for (String s: stoneCounts.keySet()) {
                long count = stoneCounts.get(s);

                double value = Double.parseDouble(s);
                if (value == 0) {
                    newCounts.put("1", newCounts.getOrDefault("1", 0L) + count);
                } else if (s.length() % 2 == 0) {
                    String first = s.substring(0, s.length() / 2).replaceFirst("^0+", "");
                    String second = s.substring(s.length() / 2).replaceFirst("^0+", "");
                    if(first.isEmpty()){
                        first = "0";
                    }
                    if(second.isEmpty()){
                        second = "0";
                    }
                    newCounts.put(first, newCounts.getOrDefault(first, 0L) + count);
                    newCounts.put(second, newCounts.getOrDefault(second, 0L) + count);
                } else {
                    String tempString = String.format("%.0f", value * 2024);
                    newCounts.put(tempString, newCounts.getOrDefault(tempString, 0L) + count);
                }
            }

            stoneCounts = newCounts;
        }

        for (long l: stoneCounts.values()) {
            result += l;
        }

        return result;
    }
}