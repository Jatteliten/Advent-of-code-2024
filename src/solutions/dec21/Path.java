package solutions.dec21;

import java.util.List;

public class Path {
    public int y;
    public int x;
    public String currentPath;
    List<String> visitedPaths;
    public Path(int y, int x, String currentPath, List<String> visitedPaths){
        this.y=y;
        this.x=x;
        this.currentPath=currentPath;
        this.visitedPaths = visitedPaths;
    }
}
