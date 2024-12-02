package solutions.dec02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solve02 {
    private final static int SAFE_DIFFERENCE = 3;
    public int solveA(List<String> input){
        int output = 0;
        for(String s: input){
            List<Integer> report = Arrays.stream(s.split(" "))
                    .map(Integer::valueOf)
                    .toList();
            if(checkIfReportIsSafe(report, checkIfListIsDescending(report))){
                output++;
            }
        }
        return output;
    }

    public int solveB(List<String> input){
        int output = 0;
        for(String s: input) {
            List<Integer> report = Arrays.stream(s.split(" "))
                    .map(Integer::valueOf)
                    .toList();
            int unsafeIndex = checkUnsafeIndex(report, checkIfListIsDescending(report));
            if(unsafeIndex != Integer.MAX_VALUE) {
                List<Integer> newList = new ArrayList<>(report);
                newList.remove(unsafeIndex);
                if (checkIfReportIsSafe(newList, checkIfListIsDescending(report))) {
                    output++;
                }
            }else{
                output++;
            }
        }
        return output;
    }

    private boolean checkIfListIsDescending(List<Integer> report){
        return report.get(0) + report.get(1) + report.get(2) >
                (report.get(report.size()-1)) + report.get(report.size()-2) + report.get(report.size()-3);
    }

    private boolean checkIfDifferenceIsUnsafe(int diff){
        return diff < 1 || diff > SAFE_DIFFERENCE;
    }

    private boolean checkIfReportIsSafe(List<Integer> report, boolean descending) {
        for (int i = 1; i < report.size(); i++) {
            int diff;
            if(descending){
                diff = report.get(i-1) - report.get(i);
            }else{
                diff = report.get(i) - report.get(i-1);
            }
            if(checkIfDifferenceIsUnsafe(diff)){
                return false;
            }
        }
        return true;
    }

    private int checkUnsafeIndex(List<Integer> report, boolean descending){
        for(int i = 1; i < report.size(); i++){
            int diff;
            if(descending) {
                diff = report.get(i-1) - report.get(i);
            }else {
                diff = report.get(i) - report.get(i - 1);
            }
            if(checkIfDifferenceIsUnsafe(diff)){
                if(i == report.size() - 1){
                    return report.size() - 1;
                }else if(i == 1){
                    if(descending) {
                        diff = report.get(i) - report.get(i + 1);
                    }else {
                        diff = report.get(i + 1) - report.get(i);
                    }
                    if(checkIfDifferenceIsUnsafe(diff)){
                        return 1;
                    }else{
                        return 0;
                    }
                }else{
                    if(descending) {
                        diff = report.get(i-1) - report.get(i+1);
                    }else {
                        diff = report.get(i+1) - report.get(i-1);
                    }
                    if(checkIfDifferenceIsUnsafe(diff)){
                        return i-1;
                    }else{
                        return i;
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }
}