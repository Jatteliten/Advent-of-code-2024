package solutions.dec13;

import java.util.List;

public class Solve13 {

    public long solve(List<String> input, boolean partOne){
        long result = 0;
        for(int i = 0; i < input.size(); i+=4){
            int aY = Integer.parseInt(input.get(i).split(",")[1].split("\\+")[1]);
            int aX = Integer.parseInt(input.get(i).split(",")[0].split("X\\+")[1]);
            int bY = Integer.parseInt(input.get(i+1).split(",")[1].split("\\+")[1]);
            int bX = Integer.parseInt(input.get(i+1).split(",")[0].split("X\\+")[1]);
            long pY = Integer.parseInt(input.get(i+2).split("Y=")[1]);
            long pX = Integer.parseInt(input.get(i+2).split(",")[0].split("X=")[1]);

            if(!partOne){
                pY += 10000000000000L;
                pX += 10000000000000L;
            }

            long aPresses = (bX * pY - bY * pX) / ((long) bX * aY - (long) bY * aX);
            long bPresses = (pY - aY * aPresses) / bY;

            if ((aPresses * aX + bPresses * bX) == pX && (aPresses * aY + bPresses * bY) == pY)
                result += 3 * aPresses + bPresses;
        }
        return result;
    }

}