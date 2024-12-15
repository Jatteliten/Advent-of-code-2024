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
                if(instruction == '^'){
                    int counter = 1;
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
                    int counter = 1;
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
                    int counter = 1;
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
                    int counter = 1;
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
}
