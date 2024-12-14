package solutions.dec14;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solve14 {
    public int solveA(List<String> input, int height, int width){
        List<Robot> robots = initializeRobots(input);
        int middleY = height/2;
        int middleX = width/2;
        int[] quadrants = new int[]{0,0,0,0};

        for(int i = 0; i < 100; i++){
            for(Robot r: robots){
                r.moveRobot(height, width);
            }
        }

        for(Robot r: robots){
            if(r.y < middleY && r.x < middleX){
                quadrants[0]++;
            }
            if(r.y < middleY && r.x > middleX){
                quadrants[1]++;
            }
            if(r.y > middleY && r.x < middleX){
                quadrants[2]++;
            }
            if(r.y > middleY && r.x > middleX){
                quadrants[3]++;
            }
        }
        return quadrants[0] * quadrants[1] * quadrants[2] * quadrants[3];
    }

    public int solveB(List<String> input, int height, int width){
        List<Robot> robots = initializeRobots(input);

        Set<List<Integer>> coordinates = new HashSet<>();
        int counter = 0;
        while(coordinates.size() != robots.size()){
            counter++;
            coordinates = new HashSet<>();
            for(Robot r: robots){
                r.moveRobot(height, width);
                coordinates.add(List.of(r.y, r.x));
            }
        }

        return counter;
    }

    List<Robot> initializeRobots(List<String> input){
        List<Robot> robots = new ArrayList<>();
        for(String s: input){
            String[] instructions = s.split(" ");
            String[] start = instructions[0].split(",");
            String[] movement = instructions[1].split(",");
            int startY = Integer.parseInt(start[1]);
            int startX = Integer.parseInt(start[0].substring(2));
            int movementY = Integer.parseInt(movement[1]);
            int movementX = Integer.parseInt(movement[0].substring(2));

            robots.add(new Robot(startY, startX, movementY, movementX));
        }
        return robots;
    }

}
