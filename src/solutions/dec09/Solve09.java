package solutions.dec09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solve09 {
    public long solveA(List<String> input) {
        long result = 0;
        String s = input.get(0);
        List<String> memory = new ArrayList<>();

        createMemory(s, memory);

        for (int i = memory.size() - 1; i > 0; i--) {
            if (!memory.get(i).equals(".")) {
                memory.set(memory.indexOf("."), memory.get(i));
                memory.set(i, ".");
            }
        }
        memory.removeAll(Collections.singleton("."));

        for (int i = 0; i < memory.size(); i++) {
            result += i * Long.parseLong(memory.get(i));
        }
        return result;
    }

    private void createMemory(String s, List<String> memory) {
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < Integer.parseInt(String.valueOf(s.charAt(i))); j++) {
                if (i % 2 == 0) {
                    memory.add(String.valueOf((i + 1) / 2));
                } else {
                    memory.add(".");
                }
            }
        }
    }

    public long solveB(List<String> input) {
        long result = 0;
        String s = input.get(0);
        List<String> memory = new ArrayList<>();
        List<Integer> indexesMoved = new ArrayList<>();

        createMemory(s, memory);

        int counter = 0;
        String current = memory.get(memory.size() - 1);

        for (int i = memory.size() - 1; i > 0; i--) {
            if (!memory.get(i).equals(".") && memory.get(i).equals(current)) {
                counter++;
            } else {
                for (int j = 0; j < i; j++) {
                    List<String> lengthCheck = new ArrayList<>();
                    for (int k = j; k < j + counter; k++) {
                        if (k >= memory.size()) {
                            break;
                        }
                        if (memory.get(k).equals(".")) {
                            lengthCheck.add(memory.get(k));
                        }
                    }
                    if (lengthCheck.size() == counter) {
                        while (counter > 0) {
                            if(!indexesMoved.contains(i + counter) && !memory.get(i + counter).equals(".")){
                                Collections.swap(memory, i + counter, j + counter - 1);
                                indexesMoved.add(j + counter - 1);
                            }
                            counter--;
                        }
                        while (memory.get(i).equals(".")) {
                            i--;
                        }
                        break;
                    }
                    lengthCheck.clear();

                }
                counter = 1;
            }
            current = memory.get(i);
        }

        for (int i = 0; i < memory.size(); i++) {
            if(!memory.get(i).equals(".")){
                result += i * Long.parseLong(memory.get(i));
            }
        }
        return result;
    }
}