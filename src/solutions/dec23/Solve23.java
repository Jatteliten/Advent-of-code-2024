package solutions.dec23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solve23 {
    Map<String, ArrayList<String>> connections;
    List<List<String>> usedConnections;
    long result;
    public long solveA(List<String> input){
        initializeSolve(input);

        for(String firstComputer: connections.keySet()){
            if(firstComputer.startsWith("t")){
                List<String> firstConnections = connections.get(firstComputer);

                for(String secondComputer: firstConnections){
                    List<String> secondConnections = connections.get(secondComputer);

                    if(secondConnections.contains(firstComputer)){
                        for(String thirdComputer: secondConnections){
                            List<String> thirdConnections = connections.get(thirdComputer);

                            if(thirdConnections.contains(firstComputer) && thirdConnections.contains(secondComputer)){
                                ArrayList<String> thisConnection =
                                        new ArrayList<>(Arrays.asList(firstComputer, secondComputer, thirdComputer));
                                Collections.sort(thisConnection);

                                if(!usedConnections.contains(thisConnection)){
                                    result++;
                                    usedConnections.add(thisConnection);
                                }
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

    public String solveB(List<String> input){
        initializeSolve(input);

        List<String> largestLan = new ArrayList<>();
        for(String computer: connections.keySet()){
            List<String> computerConnections = new ArrayList<>(Collections.singletonList(computer));
            for(String computersInLan: connections.get(computer)){
                if(connections.get(computersInLan).containsAll(computerConnections)){
                    computerConnections.add(computersInLan);
                }
            }
            if(computerConnections.size() > largestLan.size()){
                largestLan = computerConnections;
            }
        }

        Collections.sort(largestLan);

        return String.join(",", largestLan);
    }

    private void initializeSolve(List<String> input) {
        connections = new HashMap<>();
        usedConnections = new ArrayList<>();
        result = 0;

        for(String s: input){
            String[] computers = s.split("-");
            addConnections(computers[0], computers[1]);
            addConnections(computers[1], computers[0]);
        }
    }

    private void addConnections(String computerOne, String computerTwo){
        if(connections.get(computerOne) == null){
            connections.put(computerOne, new ArrayList<>(Collections.singletonList(computerTwo)));
        }else if(!connections.get(computerOne).contains(computerTwo)){
            connections.get(computerOne).add(computerTwo);
        }
    }
}
