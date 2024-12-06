package solutions.dec06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solve06 {
    private static final String D_UP = "up";
    private static final String D_DOWN = "down";
    private static final String D_RIGHT = "right";
    private static final String D_LEFT = "left";
    private static final String OBSTACLE = "#";
    private static final String WALKED = "X";
    private static final String NOT_WALKED = ".";
    private static final String STARTING_POSITION = "^";

    public int solveA(List<String> input){
        return createMap(input, 1, true);
    }

    public int solveB(List<String> input){
        return createMap(input, 0, false);
    }

    private int createMap(List<String> input, int counter, boolean partOne) {
        int currentY = 0;
        int currentX = 0;
        int startingY = 0;
        int startingX = 0;

        Map<List<Integer>, String> coordinates = new HashMap<>();
        for(int y = 0; y < input.size(); y++){
            String[] xCoords = input.get(y).split("");
            for(int x = 0; x < xCoords.length; x++){
                String currentChar = input.get(y).substring(x, x+1);
                coordinates.put(List.of(y, x), currentChar);
                if(currentChar.equals(STARTING_POSITION)){
                    currentY = y;
                    currentX = x;
                    startingY = y;
                    startingX = x;
                }
            }
        }

        traverseMap(input, currentY, currentX, coordinates);

        if(partOne){
            for(String s: coordinates.values()){
                if(s.equals(WALKED)){
                    counter++;
                }
            }
        }else{
            for(List<Integer> c: coordinates.keySet()){
                if(coordinates.get(c).equals(WALKED)){
                    int[] blocked = new int[]{c.get(0), c.get(1)};
                    if(checkLoop(coordinates, blocked, startingY, startingX, input)){
                        counter++;
                    }
                }
            }
        }

        return counter;
    }

    private void traverseMap(List<String> input, int currentY, int currentX, Map<List<Integer>, String> coordinates) {
        String nextPosition;
        String currentDirection = D_UP;
        while(currentY >= 0 && currentY < input.size() && currentX >= 0 && currentX < input.get(0).length()){
            switch (currentDirection) {
                case D_UP -> {
                    if (currentY != 0) {
                        nextPosition = coordinates.get(List.of(currentY - 1, currentX));
                        if (nextPosition.equals(OBSTACLE)) {
                            currentDirection = D_RIGHT;
                            currentY++;
                        } else if (nextPosition.equals(NOT_WALKED)) {
                            coordinates.put(List.of(currentY - 1, currentX), WALKED);
                        }
                    }
                    currentY--;
                }
                case D_RIGHT -> {
                    if (currentX != input.get(0).length() - 1) {
                        nextPosition = coordinates.get(List.of(currentY, currentX + 1));
                        if (nextPosition.equals(OBSTACLE)) {
                            currentDirection = D_DOWN;
                            currentX--;
                        } else if (nextPosition.equals(NOT_WALKED)) {
                            coordinates.put(List.of(currentY, currentX + 1), WALKED);
                        }
                    }
                    currentX++;
                }
                case D_DOWN -> {
                    if (currentY != input.size() - 1) {
                        nextPosition = coordinates.get(List.of(currentY + 1, currentX));
                        if (nextPosition.equals(OBSTACLE)) {
                            currentDirection = D_LEFT;
                            currentY--;
                        } else if (nextPosition.equals(NOT_WALKED)) {
                            coordinates.put(List.of(currentY + 1, currentX), WALKED);
                        }
                    }
                    currentY++;
                }
                case D_LEFT -> {
                    if (currentX != 0) {
                        nextPosition = coordinates.get(List.of(currentY, currentX - 1));
                        if (nextPosition.equals(OBSTACLE)) {
                            currentDirection = D_UP;
                            currentX++;
                        } else if (nextPosition.equals(NOT_WALKED)) {
                            coordinates.put(List.of(currentY, currentX - 1), WALKED);
                        }
                    } currentX--;
                }
            }
        }
    }

    private boolean checkLoop(Map<List<Integer>, String> coordinates, int[] blocked,
                              int currentY, int currentX, List<String> input){
        String currentDirection = D_UP;
        String nextPosition;
        Map<String, List<String>> walked = new HashMap<>();
        while(currentY >= 0 && currentY < input.size() && currentX >= 0 && currentX < input.get(0).length()){
            if(walked.containsKey(currentY + " " + currentX)){
                if(walked.get(currentY + " " + currentX).contains(currentDirection)){
                    return true;
                }
                ArrayList<String> temp = new ArrayList<>(walked.get(currentY + " " + currentX));
                temp.add(currentDirection);
                walked.put(currentY + " " + currentX, temp);
            }else{
                walked.put(currentY + " " + currentX, List.of(currentDirection));
            }

            switch (currentDirection) {
                case D_UP -> {
                    if (currentY != 0) {
                        nextPosition = coordinates.get(List.of(currentY - 1, currentX));
                        if (nextPosition.equals(OBSTACLE)
                                || (blocked[0] == currentY-1 && blocked[1] == currentX)) {
                            currentDirection = D_RIGHT;
                            currentY++;
                        }
                    }
                    currentY--;
                }
                case D_RIGHT -> {
                    if (currentX != input.get(0).length() - 1) {
                        nextPosition = coordinates.get(List.of(currentY, currentX + 1));
                        if (nextPosition.equals(OBSTACLE)
                                || (blocked[0] == currentY && blocked[1] == currentX+1)) {
                            currentDirection = D_DOWN;
                            currentX--;
                        }
                    }
                    currentX++;
                }
                case D_DOWN -> {
                    if (currentY != input.size() - 1) {
                        nextPosition = coordinates.get(List.of(currentY + 1, currentX));
                        if (nextPosition.equals(OBSTACLE)
                                || (blocked[0] == currentY+1 && blocked[1] == currentX)) {
                            currentDirection = D_LEFT;
                            currentY--;
                        }
                    }
                    currentY++;
                }
                case D_LEFT -> {
                    if (currentX != 0) {
                        nextPosition = coordinates.get(List.of(currentY, currentX - 1));
                        if (nextPosition.equals(OBSTACLE)
                                || (blocked[0] == currentY && blocked[1] == currentX-1)) {
                            currentDirection = D_UP;
                            currentX++;
                        }
                    }
                    currentX--;
                }
            }
        }
        return false;
    }
}