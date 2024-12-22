package solutions.dec22;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solve22 {
    public long solveA(List<String> input){
        long result = 0;
        for(String s: input){
            long num = Long.parseLong(s);
            for(int i = 0; i < 2000; i++){
                num = calculateNextNumber(num);
            }
            result += num;
        }
        return result;
    }

    public long solveB(List<String> input) {
        Map<List<Long>, Long> priceToValues = new HashMap<>();

        for (String s : input) {
            long firstPrice = Long.parseLong(s);
            List<Long> priceChanges = new ArrayList<>();
            List<List<Long>> usedSequences = new ArrayList<>();
            long previousLastDigit = getLastDigit(firstPrice);

            for (int i = 0; i < 2000; i++) {
                firstPrice = calculateNextNumber(firstPrice);
                long lastDigit = getLastDigit(firstPrice);
                long priceChange = lastDigit - previousLastDigit;
                priceChanges.add(priceChange);

                if (priceChanges.size() == 4) {
                    List<Long> temp = new ArrayList<>(priceChanges);

                    if (!usedSequences.contains(temp)) {
                        priceToValues.put(temp, priceToValues.getOrDefault(temp, 0L) + lastDigit);
                        usedSequences.add(temp);
                    }
                    priceChanges.remove(0);
                }
                previousLastDigit = lastDigit;
            }
        }

        return Collections.max(priceToValues.values());
    }

    private long calculateNextNumber(long num){
        long newNum = num;
        newNum = (newNum ^ (newNum * 64)) % 16777216;
        newNum = (newNum ^ (newNum / 32)) % 16777216;
        newNum = (newNum ^ (newNum * 2048)) % 16777216;
        return newNum;
    }

    private long getLastDigit(long num){
        return num%10L;
    }
}