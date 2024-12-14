package solutions.dec13;

import java.util.List;

public class Solve13 {
    public int SolveA(List<String> input){
        int result = 0;
        for(int i = 0; i < input.size(); i+=4){
            int aY = Integer.parseInt(input.get(i).split(",")[1].split("\\+")[1]);
            int aX = Integer.parseInt(input.get(i).split(",")[0].split("X\\+")[1]);
            int bY = Integer.parseInt(input.get(i+1).split(",")[1].split("\\+")[1]);
            int bX = Integer.parseInt(input.get(i+1).split(",")[0].split("X\\+")[1]);
            int pY = Integer.parseInt(input.get(i+2).split("Y=")[1]);
            int pX = Integer.parseInt(input.get(i+2).split(",")[0].split("X=")[1]);

            for(int j = 1; j <= 100; j++) {
                pY -= bY;
                pX -= bX;
                if (pY % aY == 0 && pX % aX == 0 && pY/aY == pX/aX) {
                    result += j + (pY / aY * 3);
                    break;
                }
            }
        }
        return result;
    }

}