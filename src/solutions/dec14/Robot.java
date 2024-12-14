package solutions.dec14;

public class Robot {
    public int y;
    public int x;
    public int moveY;
    public int moveX;

    public Robot(int y, int x, int moveY, int moveX){
        this.y = y;
        this.x = x;
        this.moveY = moveY;
        this.moveX = moveX;
    }

    public void moveRobot(int height, int width){
        y += moveY;
        x += moveX;
        if(y >= height){
            y -= height;
        }else if(y < 0){
            y += height;
        }
        if(x >= width){
            x -= width;
        }else if(x < 0){
            x += width;
        }
    }
}
