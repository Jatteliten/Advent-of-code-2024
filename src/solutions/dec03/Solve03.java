package solutions.dec03;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solve03 {

    public int solveA(List<String> input){
        int output = 0;
        for(String s: input){
            Pattern regex = Pattern.compile("mul\\(\\d+,\\d+\\)");
            Matcher matcher = regex.matcher(s);
            ArrayList<String> matches = new ArrayList<>();
            while(matcher.find()){
                matches.add(matcher.group());
            }
            for(String mul: matches){
                mul = mul.substring(mul.indexOf("(") + 1, mul.length()-1);
                String[] multiplication = mul.split(",");
                try{
                    output += (Integer.parseInt(multiplication[0]) * Integer.parseInt(multiplication[1]));
                }catch(NumberFormatException e){
                    //do nothing
                }
            }
        }
        return output;
    }

    public int solveB(List<String> input){
        StringBuilder newS = new StringBuilder();
        for(String s: input){
            newS.append(s);
        }
        return solveA(List.of(removeTextBetweenDontAndDo(newS.toString())));
    }

    public String removeTextBetweenDontAndDo(String s){
        while(s.contains("don't()")){
            int dontIndex = s.indexOf("don't()");
            int doIndex = s.indexOf("do()", dontIndex);

            if (doIndex != -1) {
                s = s.substring(0, dontIndex) + s.substring(doIndex + 4);
            } else {
                s = s.substring(0, dontIndex);
            }
        }
        return s;
    }
}
