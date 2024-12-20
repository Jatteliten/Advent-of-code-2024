package solutions.dec20;

import java.util.ArrayList;
import java.util.List;

public class Path {
    public long points;
    public ArrayList<List<Integer>> visitedLocations;
    public Path(long points, ArrayList<List<Integer>> visitedLocations){
        this.points = points;
        this.visitedLocations = visitedLocations;
    }
}
