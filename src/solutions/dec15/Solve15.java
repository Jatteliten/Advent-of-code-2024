package solutions.dec15;

import java.util.ArrayList;
import java.util.List;

public class Solve15 {
    public int solveA(List<String> input){
        int result = 0;
        int reader = 0;
        String position = "";
        List<List<Character>> warehouse = new ArrayList<>();
        while(!input.get(reader).isEmpty()){
            List<Character> row = new ArrayList<>();
            for(int i = 0; i < input.get(reader).length(); i++){
                row.add(input.get(reader).charAt(i));
                if(position.isEmpty() && input.get(reader).charAt(i) == '@'){
                    position = reader + ":" + i;
                }
            }
            warehouse.add(row);
            reader++;
        }

        for(int i = reader; i < input.size(); i++){
            for(int j = 0; j < input.get(i).length(); j++){
                int y = Integer.parseInt(position.split(":")[0]);
                int x = Integer.parseInt(position.split(":")[1]);
                char instruction = input.get(i).charAt(j);
                boolean emptyFound = false;
                int counter = 1;
                if(instruction == '^'){
                    for(int k = y-1; k > 0; k--){
                        if(warehouse.get(k).get(x) == '.'){
                            emptyFound = true;
                            break;
                        }else if(warehouse.get(k).get(x) == '#'){
                            break;
                        }
                        counter++;
                    }
                    if(emptyFound){
                        warehouse.get(y-counter).set(x, 'O');
                        warehouse.get(y).set(x, '.');
                        warehouse.get(y-1).set(x, '@');
                        position = y-1 + ":" + x;
                    }
                }else if(instruction == 'v'){
                    for(int k = y+1; k < warehouse.size(); k++){
                        if(warehouse.get(k).get(x) == '.'){
                            emptyFound = true;
                            break;
                        }else if(warehouse.get(k).get(x) == '#'){
                            break;
                        }
                        counter++;
                    }
                    if(emptyFound){
                        warehouse.get(y+counter).set(x, 'O');
                        warehouse.get(y).set(x, '.');
                        warehouse.get(y+1).set(x, '@');
                        position = y+1 + ":" + x;
                    }
                }else if(instruction == '<'){
                    for(int k = x-1; k > 0; k--){
                        if(warehouse.get(y).get(k) == '.'){
                            emptyFound = true;
                            break;
                        }else if(warehouse.get(y).get(k) == '#'){
                            break;
                        }
                        counter++;
                    }
                    if(emptyFound){
                        warehouse.get(y).set(x-counter, 'O');
                        warehouse.get(y).set(x, '.');
                        warehouse.get(y).set(x-1, '@');
                        position = y + ":" + (x-1);
                    }
                }else if(instruction == '>'){
                    for(int k = x+1; k < warehouse.get(0).size(); k++){
                        if(warehouse.get(y).get(k) == '.'){
                            emptyFound = true;
                            break;
                        }else if(warehouse.get(y).get(k) == '#'){
                            break;
                        }
                        counter++;
                    }
                    if(emptyFound){
                        warehouse.get(y).set(x+counter, 'O');
                        warehouse.get(y).set(x, '.');
                        warehouse.get(y).set(x+1, '@');
                        position = y + ":" + (x+1);
                    }
                }
            }
        }
        for(int i = 0; i < warehouse.size(); i++){
            for(int j = 0; j < warehouse.get(0).size(); j++){
                if(warehouse.get(i).get(j) == 'O'){
                    result += (i*100) + j;
                }
            }
        }

        return result;
    }

    public int solveB(List<String> input) {
        int result = 0;
        int reader = 0;
        String position = "";
        List<List<Character>> warehouse = new ArrayList<>();
        while (!input.get(reader).isEmpty()) {
            List<Character> row = new ArrayList<>();
            for (int i = 0; i < input.get(reader).length(); i++) {
                if (position.isEmpty() && input.get(reader).charAt(i) == '@') {
                    position = reader + ":" + (i * 2);
                    row.add('@');
                    row.add('.');
                }else if(input.get(reader).charAt(i) == '#'){
                    row.add('#');
                    row.add('#');
                }else if(input.get(reader).charAt(i) == 'O'){
                    row.add('[');
                    row.add(']');
                }else{
                    row.add('.');
                    row.add('.');
                }
            }
            warehouse.add(row);
            reader++;
        }

        for (int i = reader; i < input.size(); i++) {
            for(char c: input.get(i).toCharArray()){
                List<String> boxes = new ArrayList<>();
                List<String> boxesToCheck = new ArrayList<>();
                String[] currentPosition = position.split(":");
                int currentY = Integer.parseInt(currentPosition[0]);
                int currentX = Integer.parseInt(currentPosition[1]);
                boolean allMovable = true;
                if(c == '^'){
                    if(warehouse.get(currentY - 1).get(currentX) == '.'){
                        warehouse.get(currentY - 1).set(currentX, '@');
                        warehouse.get(currentY).set(currentX, '.');
                        position = (currentY - 1) + ":" + currentX;
                    }else if(warehouse.get(currentY - 1).get(currentX) != '#'){
                        if(warehouse.get(currentY - 1).get(currentX) == '['){
                            boxesToCheck.add((currentY - 1) + ":" + currentX + "-" + (currentY - 1) + ":" + (currentX+1));
                        }else{
                            boxesToCheck.add((currentY - 1) + ":" + (currentX-1) + "-" + (currentY - 1) + ":" + (currentX+1));
                        }
                        while(!boxesToCheck.isEmpty()){
                            allMovable = true;
                            List<String> newBoxesToCheck = new ArrayList<>();
                            for(String box: boxesToCheck){
                                String[] boxBounds = box.split("-");
                                String[] leftSide = boxBounds[0].split(":");
                                int leftY = Integer.parseInt(leftSide[0]);
                                int leftX = Integer.parseInt(leftSide[1]);
                                boxes.add(box);
                                if(warehouse.get(leftY - 1).get(leftX) == '#' || warehouse.get(leftY - 1).get(leftX+1) == '#'){
                                    allMovable = false;
                                    newBoxesToCheck = new ArrayList<>();
                                    break;
                                }
                                if(warehouse.get(leftY - 1).get(leftX - 1) == '['){
                                    boxes.add((leftY - 1) + ":" + (leftX - 1) + "-" + (leftY - 1) + ":" + leftX);
                                    newBoxesToCheck.add((leftY - 1) + ":" + (leftX - 1) + "-" + (leftY - 1) + ":" + leftX);
                                    allMovable = false;
                                }
                                if(warehouse.get(leftY - 1).get(leftX) == '['){
                                    boxes.add((leftY - 1) + ":" + (leftX) + "-" + (leftY - 1) + ":" + (leftX + 1));
                                    newBoxesToCheck.add((leftY - 1) + ":" + (leftX) + "-" + (leftY - 1) + ":" + (leftX + 1));
                                    allMovable = false;
                                }
                                if(warehouse.get(leftY - 1).get(leftX + 1) == '['){
                                    boxes.add((leftY - 1) + ":" + (leftX + 1) + "-" + (leftY - 1) + ":" + (leftX + 2));
                                    newBoxesToCheck.add((leftY - 1) + ":" + (leftX + 1) + "-" + (leftY - 1) + ":" + (leftX + 2));
                                    allMovable = false;
                                }
                            }
                            boxesToCheck = newBoxesToCheck;
                        }
                    }else if(warehouse.get(currentY - 1).get(currentX) == '#'){
                        allMovable = false;
                    }
                    if(allMovable){
                        for(String box: boxes.reversed()){
                            String[] boxBounds = box.split("-");
                            String[] leftSide = boxBounds[0].split(":");
                            int leftY = Integer.parseInt(leftSide[0]);
                            int leftX = Integer.parseInt(leftSide[1]);
                            warehouse.get(leftY - 1).set(leftX, '[');
                            warehouse.get(leftY - 1).set(leftX + 1, ']');
                            warehouse.get(leftY).set(leftX, '.');
                            warehouse.get(leftY).set(leftX + 1, '.');
                        }
                        warehouse.get(currentY).set(currentX, '.');
                        position = (currentY - 1) + ":" + currentX;
                        warehouse.get(currentY - 1).set(currentX, '@');
                    }
                }else if(c == 'v'){
                    if(warehouse.get(currentY + 1).get(currentX) == '.'){
                        warehouse.get(currentY + 1).set(currentX, '@');
                        warehouse.get(currentY).set(currentX, '.');
                        position = (currentY + 1) + ":" + currentX;
                    }else if(warehouse.get(currentY + 1).get(currentX) != '#'){
                        if(warehouse.get(currentY + 1).get(currentX) == '['){
                            boxesToCheck.add((currentY + 1) + ":" + currentX + "-" + (currentY + 1) + ":" + (currentX+1));
                        }else{
                            boxesToCheck.add((currentY + 1) + ":" + (currentX-1) + "-" + (currentY + 1) + ":" + (currentX+1));
                        }
                        while(!boxesToCheck.isEmpty()){
                            allMovable = true;
                            List<String> newBoxesToCheck = new ArrayList<>();
                            for(String box: boxesToCheck){
                                String[] boxBounds = box.split("-");
                                String[] leftSide = boxBounds[0].split(":");
                                int leftY = Integer.parseInt(leftSide[0]);
                                int leftX = Integer.parseInt(leftSide[1]);
                                boxes.add(box);
                                if(warehouse.get(leftY + 1).get(leftX) == '#' || warehouse.get(leftY + 1).get(leftX+1) == '#'){
                                    allMovable = false;
                                    newBoxesToCheck = new ArrayList<>();
                                    break;
                                }
                                if(warehouse.get(leftY + 1).get(leftX - 1) == '['){
                                    boxes.add((leftY + 1) + ":" + (leftX - 1) + "-" + (leftY + 1) + ":" + leftX);
                                    newBoxesToCheck.add((leftY + 1) + ":" + (leftX - 1) + "-" + (leftY + 1) + ":" + leftX);
                                    allMovable = false;
                                }
                                if(warehouse.get(leftY + 1).get(leftX) == '['){
                                    boxes.add((leftY + 1) + ":" + (leftX) + "-" + (leftY + 1) + ":" + (leftX + 1));
                                    newBoxesToCheck.add((leftY + 1) + ":" + (leftX) + "-" + (leftY + 1) + ":" + (leftX + 1));
                                    allMovable = false;
                                }
                                if(warehouse.get(leftY + 1).get(leftX + 1) == '['){
                                    boxes.add((leftY + 1) + ":" + (leftX + 1) + "-" + (leftY + 1) + ":" + (leftX + 2));
                                    newBoxesToCheck.add((leftY + 1) + ":" + (leftX + 1) + "-" + (leftY + 1) + ":" + (leftX + 2));
                                    allMovable = false;
                                }
                            }
                            boxesToCheck = newBoxesToCheck;
                        }
                    }else if(warehouse.get(currentY + 1).get(currentX) == '#'){
                        allMovable = false;
                    }
                    if(allMovable){
                        for(String box: boxes.reversed()){
                            String[] boxBounds = box.split("-");
                            String[] leftSide = boxBounds[0].split(":");
                            int leftY = Integer.parseInt(leftSide[0]);
                            int leftX = Integer.parseInt(leftSide[1]);
                            warehouse.get(leftY + 1).set(leftX, '[');
                            warehouse.get(leftY + 1).set(leftX + 1, ']');
                            warehouse.get(leftY).set(leftX, '.');
                            warehouse.get(leftY).set(leftX + 1, '.');
                        }
                        warehouse.get(currentY).set(currentX, '.');
                        position = (currentY + 1) + ":" + currentX;
                        warehouse.get(currentY + 1).set(currentX, '@');
                    }
                }else if(c == '<'){
                    int counter = 2;
                    if(warehouse.get(currentY).get(currentX-1) == '.'){
                        warehouse.get(currentY).set(currentX - 1, '@');
                        warehouse.get(currentY).set(currentX, '.');
                        position = (currentY) + ":" + (currentX-1);
                        allMovable = false;
                    }else if(warehouse.get(currentY).get(currentX-1) == ']'){
                        while(warehouse.get(currentY).get(currentX-counter-1) != '.'){
                            counter+=2;
                            if(warehouse.get(currentY).get(currentX-counter) == '#'){
                                allMovable = false;
                                break;
                            }
                        }
                    }else if(warehouse.get(currentY).get(currentX - 1) == '#'){
                        allMovable = false;
                    }
                    if(allMovable){
                        for(int k = counter; k > 0; k-=2){
                            warehouse.get(currentY).set(currentX - k, ']');
                            warehouse.get(currentY).set(currentX - k - 1, '[');
                        }
                        warehouse.get(currentY).set(currentX - 1, '@');
                        warehouse.get(currentY).set(currentX, '.');
                        position = (currentY) + ":" + (currentX-1);
                    }
                }else if(c == '>'){
                    int counter = 2;
                    if(warehouse.get(currentY).get(currentX+1) == '.'){
                        warehouse.get(currentY).set(currentX+1, '@');
                        warehouse.get(currentY).set(currentX, '.');
                        position = (currentY) + ":" + (currentX+1);
                        allMovable = false;
                    }else if(warehouse.get(currentY).get(currentX+1) == '['){
                        while(warehouse.get(currentY).get(currentX+counter+1) != '.'){
                            counter+=2;
                            if(warehouse.get(currentY).get(currentX+counter) == '#'){
                                allMovable = false;
                                break;
                            }
                        }
                    }else if(warehouse.get(currentY).get(currentX+1) == '#'){
                        allMovable = false;
                    }
                    if(allMovable){
                        for(int k = 0; k <= counter; k+=2){
                            warehouse.get(currentY).set(currentX + k, '[');
                            warehouse.get(currentY).set(currentX + k + 1, ']');
                        }
                        warehouse.get(currentY).set(currentX + 1, '@');
                        warehouse.get(currentY).set(currentX, '.');
                        position = (currentY) + ":" + (currentX+1);
                    }
                }

            }
        }
        for(int i = 0; i < warehouse.size(); i++){
            for(int j = 0; j < warehouse.get(0).size(); j++){
                if(warehouse.get(i).get(j) == '['){
                    result += 100 * i + j;
                }
            }
        }

        return result;
    }
}
