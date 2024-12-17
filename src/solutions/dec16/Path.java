package solutions.dec16;

import java.util.ArrayList;
import java.util.List;

public class Path {
    long points;
    ArrayList<List<Integer>> visitedLocations;
    String direction;
    public Path(long points, ArrayList<List<Integer>> visitedLocations, String direction){
        this.points = points;
        this.visitedLocations = visitedLocations;
        this.direction = direction;
    }
}
